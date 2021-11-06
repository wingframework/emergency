package com.example.saas.common.sevices;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.alibaba.fastjson.JSONObject;
import com.example.saas.common.dtos.query.PageParam;
import com.example.saas.common.dtos.query.Paging;
import com.example.saas.common.dtos.query.QueryAttribute;
import com.example.saas.common.dtos.query.QueryCondition;
import com.example.saas.common.dtos.query.QueryParam;
import com.example.saas.common.dtos.query.SummaryParam;
import com.example.saas.common.entitys.queryTemplate.MetaField;
import com.example.saas.common.entitys.queryTemplate.MetaObject;
import com.example.saas.common.sevices.jpa.MetaFieldJPA;
import com.example.saas.common.sevices.jpa.MetaObjectJPA;
import com.example.saas.common.utils.DateUtil;

import java.math.BigInteger;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SqlEntityService {
    @Value("${spring.jpa.database}")
    private String dbType;
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    private Session session;
    @Autowired
    private MetaFieldJPA metaFieldJPA;
    @Autowired
    private MetaObjectJPA metaObjectJPA;
    // @Autowired
    // private ParamsJPA paramsJPA;

    // sql查询，返给前端元数据，包括字段名，字段类型
    public List<JSONObject> getFieldInfoBySql(String sql) {
        session = entityManager.unwrap(org.hibernate.Session.class);
        ResultSet resultSet = session.doReturningWork(new ReturningWork<ResultSet>() {
            @Override
            public ResultSet execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            }
        });
        JSONObject object = null;
        List<JSONObject> list = new ArrayList<>();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                object = new JSONObject();
                object.put("field", resultSetMetaData.getColumnName(i));
                object.put("type", resultSetMetaData.getColumnTypeName(i));
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getJdbcTypeByJava(String typeName) {
        String jdbcType = "unknown type";
        switch (typeName) {
        case "java.lang.String":
            jdbcType = "VARCHAR";
            break;
        case "java.math.BigDecimal":
            jdbcType = "DECIMAL";
            break;
        case "java.lang.Boolean":
        case "boolean":
            jdbcType = "BIT";
            break;
        case "java.lang.Double":
        case "double":
            jdbcType = "DOUBLE";
            break;
        case "java.lang.Integer":
        case "int":
        case "java.lang.Long":
        case "long":
            jdbcType = "INTEGER";
            break;
        case "java.sql.Date":
            jdbcType = "DATE";
            break;
        case "java.lang.Object":
            jdbcType = "JAVA_OBJECT";
            break;
        case "byte[]":
            jdbcType = "BLOB";
            break;
        case "java.lang.Float":
        case "float":
            jdbcType = "FLOAT";
            break;
        case "java.sql.Time":
            jdbcType = "TIME";
            break;
        case "java.sql.Timestamp":
            jdbcType = "TIMESTAMP";
            break;
        case "java.lang.Byte":
        case "byte":
        case "short":
            jdbcType = "TIMESTAMP";
            break;
        default:
            jdbcType = "unknown type";
            break;
        }
        return jdbcType;
    }

    // sql查询，返给前端元数据，包括字段名，字段类型
    public List<JSONObject> getMetaFieldInfo(String entityCode) {
        String sql = metaObjectJPA.findByObjectCode(entityCode).getQuerySql();
        sql = sql.replaceAll(":[_a-zA-Z\\d.]+", "NULL");
        sql = sql.replaceAll("@@[_a-zA-Z\\d.]+", "NULL");
        return getFieldInfoBySql(sql);
    }

    // 根据查询定义的code和查询条件列表返回组成的where条件
    private String getConditionsSQL(String entityCode, List<QueryCondition> conditions) {
        List<MetaField> fields = metaFieldJPA.findByObjectCode(entityCode);
        Map<String, MetaField> fieldMap = fields.stream()
                .collect(Collectors.toMap(MetaField::getFieldName, a -> a, (k1, k2) -> k1));
        String conditionSql = "";
        if (conditions == null) {
            return "";
        }
        for (QueryCondition item : conditions) {
            String fieldName = item.getField();
            String fieldType = fieldMap.get(item.getField()).getFieldType();
            String fieldValue = String.valueOf(item.getValue());
            String compare = item.getCompare().toUpperCase();
            if (compare.equals("LIKE")) {
                fieldValue = "'%" + getFieldValueString(fieldType, fieldValue) + "%'"; // 字符串就包个 '' 如 name like '张三%'
            } else if (compare.equals("IN")) {
                String[] strings = fieldValue.split(",");
                for (String s : strings) {
                    fieldValue = fieldValue + getFieldValueString(fieldType, s) + ",";
                }
                fieldValue = "(" + fieldValue.substring(0, fieldValue.length() - 1) + ")";
            } else if (compare.contains("NULL")) {// 表示是求 name is null 或是 name not is null
                fieldValue = "";
            } else {
                fieldValue = getFieldValueString(fieldType, fieldValue);
            }
            if (conditions.indexOf(item) < conditions.size() - 1) {
                conditionSql = conditionSql + fieldName + " " + compare + " " + fieldValue + " " + item.getAndOr()
                        + " ";
            } else {
                conditionSql = conditionSql + fieldName + " " + compare + " " + fieldValue;
            }
        }
        return conditionSql;
    }

    // 根据查询定义的code和查询条件列表返回不分页的sql语句
    public String getAllRecordsSQL(String entityCode, QueryParam queryParameter) {
        MetaObject metaObject = metaObjectJPA.findByObjectCode(entityCode);
        List<QueryCondition> conditions = queryParameter.getQueryConditions();
        String filter = metaObject.getFilter();
        String userWhere = "";
        if (filter != null && filter.length() != 0) {
            userWhere = " AND " + filter;
        }
        String conditionSql = getConditionsSQL(entityCode, conditions);
        String whereSQL = conditionSql + userWhere;
        if (!whereSQL.isEmpty()) {
            whereSQL = " AND (" + whereSQL + ")";
        }
        String sql = metaObject.getQuerySql();
        List<QueryAttribute> attrs = queryParameter.getQueryAttributes();
        if (attrs != null) {
            for (QueryAttribute attr : attrs) {
                if (attr.getType().toUpperCase().contains("CHAR")) {
                    sql = sql.replace("@" + attr.getKey(), "'" + attr.getValue() + "'");
                } else if (attr.getType().toUpperCase().contains("DATE")) {
                    if (attr.getValue().toUpperCase().contains("T")) {
                        sql = sql.replace("@" + attr.getKey(), "'" + DateUtil.parseStrToStr(attr.getValue()) + "'");
                    } else {
                        sql = sql.replace("@" + attr.getKey(), "'" + attr.getValue() + "'");
                    }
                } else {
                    sql = sql.replace("@" + attr.getKey(), attr.getValue());
                }
            }
        }
        ;
        sql = String.format("SELECT * FROM (%s) T WHERE 1=1 %s", sql, whereSQL);
        return sql;
    }

    // 根据SQL取得数据的记录数
    public Integer getRecordCount(String sql) {
        return session.createQuery(sql).getResultList().size();
    }

    // 根据查询定义的code和查询条件列表返回不分页的数据的记录数
    public long getRecordCount(String entityCode, QueryParam queryParameter) {
        return ((BigInteger) getSummaryValues(entityCode, queryParameter)[0]).intValue();
    }

    public Object[] getSummaryValues(String entityCode, QueryParam queryParameter) {
        String sql = "select count(*) as _record_count #summary from ( #table ) as T";
        List<SummaryParam> summarys = queryParameter.getSummaryParam();
        if (summarys != null) {
            StringBuffer fieldBuffer = new StringBuffer();
            for (int i = 0; i < summarys.size(); i++) {
                SummaryParam sp = summarys.get(i);
                fieldBuffer.append("," + sp.getSumType() + "(" + sp.getFieldName() + ") as " + sp.getSumName());
            }
            sql = sql.replace("#table", getAllRecordsSQL(entityCode, queryParameter)).replace("#summary", fieldBuffer);
            sql = sql.replace("#rowNumSymbol", "\\:=");
        } else {
            sql = sql.replace("#table", getAllRecordsSQL(entityCode, queryParameter)).replace("#summary", "");
            sql = sql.replace("#rowNumSymbol", "\\:=");
        }
        Query query = entityManager.createNativeQuery(sql);
        Object object = query.getSingleResult();
        if (object.getClass().isArray()) {
            return (Object[]) object;
        } else {
            Object[] objects = new Object[1];
            objects[0] = object;
            return objects;
        }
    }

    public Object getSummaryValuesSql(String sql) {
        Query query = entityManager.createNativeQuery(sql);
        Object object = query.getSingleResult();
        return object;
    }

    // 根据查询定义的code和查询参数返回分页的sql语句
    public String getPageQuerySQL(String entityCode, QueryParam queryParameter) {
        PageParam page = queryParameter.getPageParam();
        List<QueryCondition> queryConditions = queryParameter.getQueryConditions();
        String sql = getAllRecordsSQL(entityCode, queryParameter);
        String sortField = page.getSortField();
        if (sortField.isEmpty()) {
            sortField = metaObjectJPA.findByObjectCode(entityCode).getPkKey();
        }
        if (page.getPageIndex() >= 0) {
            String sqlFmt = "";
            if ("SQLSERVER".equals(dbType.toUpperCase())) {
                sqlFmt = "select top #pagesize * from (select row_number() over(order by #id #sortType) as rownumber,* from #table) A where rownumber >(#pagesize*(#pageindex-1))";
            }
            if ("ORACLE".equals(dbType.toUpperCase())) {
                sqlFmt = "select top #pagesize * from (select row_number() over(order by #id #sortType) as rownumber,* from #table) A where rownumber >(#pagesize*(#pageindex-1))";
            }
            if ("MYSQL".equals(dbType.toUpperCase())) {
                sqlFmt = "select  * from #table order by T.#id #sortType limit #recordstart,#pagesize";
            }
            sql = "(" + sql + ") AS T";
            Integer start = page.getPageIndex() * page.getPageSize();
            sqlFmt = sqlFmt.replace("#table", sql).replace("#id", sortField)
                    .replace("#sortType", page.getSortByAsc() ? "ASC" : "DESC").replace("#rowNumSymbol", ":=")
                    .replace("#pageindex", String.valueOf(page.getPageIndex()))
                    .replace("#recordstart", String.valueOf(start))
                    .replace("#pagesize", String.valueOf(page.getPageSize()));
            return sqlFmt;
        } else {
            return sql;
        }
    }

    private String getFieldValueString(String fieldType, String fieldValue) {
        String s;
        if (fieldType.toUpperCase().contains("CHAR")) {
            s = "'" + fieldValue + "'"; // 字符串
        } else if (fieldType.toUpperCase().contains("DATE")) {
            if (fieldValue.toUpperCase().contains("T")) {
                s = "'" + DateUtil.parseStrToStr(fieldValue) + "'"; // 字符串
            } else {
                s = "'" + fieldValue + "'";
            }
        } else {
            s = fieldValue;
        }
        return s;
    }

    // 根据查询定义的code和JSON对象数据生成更新语句
    private String getCRUDSql(String entityCode, JSONObject data, String CRUD) {
        MetaObject metaObject = metaObjectJPA.findByObjectCode(entityCode);
        List<MetaField> fields = metaFieldJPA.findByObjectCode(entityCode);
        Map<String, MetaField> fieldMap = fields.stream()
                .collect(Collectors.toMap(MetaField::getFieldName, a -> a, (k1, k2) -> k1));
        Iterator<String> it = data.keySet().iterator();
        String keyvalue = null;
        String sql = null;
        if ("UPDATE".equals(CRUD.toUpperCase())) {
            StringBuffer fieldBuffer = new StringBuffer();
            String keyName = metaObject.getPkKey();
            String fieldType = fieldMap.get(keyName).getFieldType();
            keyvalue = getFieldValueString(fieldType, data.getString(keyName));
            while (it.hasNext()) {
                String key = it.next();
                if (fieldMap.get(key) != null) {
                    fieldType = fieldMap.get(key).getFieldType();
                    if (fieldMap.get(key) != null && fieldMap.get(key).getIsUpdate()
                            && !metaObject.getPkKey().equals(key)) {
                        String fieldValue = getFieldValueString(fieldType, data.getString(keyName));
                        fieldBuffer.append(key).append("=").append(fieldValue).append(",");
                    }
                }
            }
            fieldBuffer.deleteCharAt(fieldBuffer.length() - 1);
            sql = "UPDATE #tablename SET #field_values WHERE #keyfield=#value";
            sql = sql.replace("#tablename", metaObject.getTableName()).replace(" #field_values", fieldBuffer.toString())
                    .replace("#keyfield", metaObject.getPkKey()).replace("#value", keyvalue);
        }
        ;
        if ("INSERT".equals(CRUD.toUpperCase())) {
            StringBuffer fieldBuffer = new StringBuffer();
            StringBuffer valueBuffer = new StringBuffer();
            while (it.hasNext()) {
                String key = it.next();
                if (fieldMap.get(key) != null) {
                    String fieldType = fieldMap.get(key).getFieldType();
                    if (fieldMap.get(key) != null && fieldMap.get(key).getIsUpdate()) {
                        fieldBuffer.append(key).append(",");
                        String fieldValue = getFieldValueString(fieldType, data.getString(key));
                        valueBuffer.append(fieldValue).append(",");
                    }
                }
            }
            fieldBuffer.deleteCharAt(fieldBuffer.length() - 1);
            valueBuffer.deleteCharAt(fieldBuffer.length() - 1);
            sql = "INSERT #tablename(#fields) values(#values)";
            sql = sql.replace("#tablename", metaObject.getTableName()).replace(" #fields", fieldBuffer.toString())
                    .replace("#values", valueBuffer.toString());

        }
        if ("DELETE".equals(CRUD.toUpperCase())) {
            String keyName = metaObject.getPkKey();
            String fieldType = fieldMap.get(keyName).getFieldType();
            keyvalue = getFieldValueString(fieldType, data.getString(keyName));
            sql = "DELETE FROM #tablename WHERE #field=#value";
            sql = sql.replace("#tablename", metaObject.getTableName()).replace(" #field", keyName).replace("#value",
                    keyvalue);

        }
        return sql;
    }

    // 根据查询定义的code和JSON对象数据对数据进行更新
    @Transactional
    public Integer updateSqlEntity(String entityCode, JSONObject data) {
        session = entityManager.unwrap(org.hibernate.Session.class);
        String sql = getCRUDSql(entityCode, data, "UPDATE");
        Query query = session.createNativeQuery(sql);
        return query.executeUpdate();
    }

    @Transactional
    public Integer insertSqlEntity(String entityCode, JSONObject data) {
        session = entityManager.unwrap(org.hibernate.Session.class);
        String sql = getCRUDSql(entityCode, data, "INSERT");
        Query query = session.createNativeQuery(sql);
        return query.executeUpdate();
    }

    @Transactional
    public Integer deleteSqlEntity(String entityCode, JSONObject data) {
        session = entityManager.unwrap(org.hibernate.Session.class);
        String sql = getCRUDSql(entityCode, data, "DELETE");
        Query query = session.createNativeQuery(sql);
        return query.executeUpdate();
    }

    // 根据查询定义的code和查询参数返回数据对象
    public Paging<JSONObject> queryPageEntity(String entityCode, QueryParam queryParameter) {
        Paging paging = new Paging<>();
        // 取得汇总及记录数数据
        Object[] object = getSummaryValues(entityCode, queryParameter);
        List<SummaryParam> list = queryParameter.getSummaryParam();
        for (int i = 1; i < object.length; i++) {
            list.get(i - 1).setSumValue(object[i].toString());
        }
        paging.setSummaryParam(list);
        long recordCount = ((BigInteger) object[0]).intValue();
        paging.setCount((int) recordCount);
        // 取得分页数据，并打包成JSONObject
        String sql = getPageQuerySQL(entityCode, queryParameter);
        session = entityManager.unwrap(org.hibernate.Session.class);
        ResultSet resultSet = session.doReturningWork(new ReturningWork<ResultSet>() {
            @Override
            public ResultSet execute(Connection connection) throws SQLException {
                sql.replaceAll("\\\\", "");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            }
        });

        ArrayList<JSONObject> entityList = new ArrayList<>();
        try {
            if (!resultSet.next()) {
                paging.setRows(null);
                return paging;
            }
            ResultSetMetaData metaData = resultSet.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            resultSet.first();
            while (!resultSet.isAfterLast()) {
                JSONObject entity = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    switch (metaData.getColumnType(i)) {
                    case Types.BIGINT:
                        entity.put(metaData.getColumnName(i), resultSet.getInt(i));
                        break;
                    case Types.DATE:
                        entity.put(metaData.getColumnName(i), resultSet.getDate(i));
                        break;
                    case Types.DOUBLE:
                        entity.put(metaData.getColumnName(i), resultSet.getDouble(i));
                        break;
                    case Types.FLOAT:
                        entity.put(metaData.getColumnName(i), resultSet.getFloat(i));
                        break;
                    case Types.INTEGER:
                        entity.put(metaData.getColumnName(i), resultSet.getInt(i));
                        break;
                    case Types.SMALLINT:
                        entity.put(metaData.getColumnName(i), resultSet.getInt(i));
                        break;
                    case Types.TIME:
                        entity.put(metaData.getColumnName(i), resultSet.getTime(i));
                        break;
                    case Types.TIMESTAMP:
                        Timestamp time = resultSet.getTimestamp(i);
                        if (time != null) {
                            entity.put(metaData.getColumnName(i), new Date(time.getTime()).toGMTString());
                        } else {
                            entity.put(metaData.getColumnName(i), null);
                        }
                        break;
                    case Types.TINYINT:
                        entity.put(metaData.getColumnName(i), resultSet.getInt(i));
                        break;
                    case Types.VARCHAR:
                        entity.put(metaData.getColumnName(i), resultSet.getString(i));
                        break;
                    case Types.CHAR:
                        entity.put(metaData.getColumnName(i), resultSet.getString(i));
                        break;
                    case Types.BOOLEAN:
                        entity.put(metaData.getColumnName(i), resultSet.getBoolean(i));
                        break;
                    case Types.DECIMAL:
                        entity.put(metaData.getColumnName(i), resultSet.getBigDecimal(i));
                        break;
                    case Types.NUMERIC:
                        entity.put(metaData.getColumnName(i), resultSet.getBigDecimal(i));
                        break;
                    case Types.NVARCHAR:
                        entity.put(metaData.getColumnName(i), resultSet.getString(i));
                        break;
                    default:
                        entity.put(metaData.getColumnName(i), resultSet.getObject(i));
                        break;
                    }
                }
                entityList.add(entity);
                resultSet.next();
            }
            paging.setRows(entityList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
    }

    public void mainTest() {
        String sql = "select @rowno \\:= @rowno+1 as rowNum,ono.* from orderno ono,(SELECT @rowno \\:= 0) b";
        Query query = entityManager.createNativeQuery(sql);
        Object object = query.getResultList();
        System.out.println(object);
    }
}
