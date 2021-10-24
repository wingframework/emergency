package com.example.demo.commons.sevices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.commons.Dtos.Q;
import com.example.demo.commons.Dtos.query.Paging;
import com.example.demo.commons.Dtos.query.QueryCondition;
import com.example.demo.commons.Dtos.query.QueryParam;
import com.example.demo.commons.Dtos.query.Summary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 单表增删改查 */
// @Service()
// public class StqService {

// @PersistenceContext
// protected EntityManager entityManager;

// @Transactional
// public boolean save(Object entity) {
// boolean flag = false;
// try {
// this.entityManager.persist(this.entityManager.contains(entity) ? entity :
// this.entityManager.merge(entity));
// flag = true;
// } catch (Exception e) {
// System.out.println("---------------保存出错---------------");
// throw e;
// }
// return flag;
// }
// }
@Service
public class StqService {
    @PersistenceContext
    protected EntityManager entityManager;

    public Paging findPageEntity(QueryParam queryParam, String className) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        return findPageEntity(queryParam, clazz);
    }

    public Paging findPageEntity(QueryParam queryParam, Class claszz) {
        String name = claszz.getSimpleName();
        String jpql = "select u from " + name + " u " + this.getWhereSql(queryParam.getQueryConditions());
        String countJpql = "select count(u) from " + name + " u " + this.getWhereSql(queryParam.getQueryConditions());
        if (queryParam.getOrderList() != null) {
            jpql += "Order By ";
            for (int i = 0; i < queryParam.getOrderList().size(); i++) {
                jpql += queryParam.getOrderList().get(i).getFieldName() + " "
                        + queryParam.getOrderList().get(i).getSort();
            }
        }
        System.out.println(jpql);
        System.out.println(countJpql);
        Query jpqlQuery = this.entityManager.createQuery(jpql);
        Query countQuery = this.entityManager.createQuery(countJpql);
        jpqlQuery.setFirstResult(queryParam.getPageParam().getPageIndex() * queryParam.getPageParam().getPageSize());
        jpqlQuery.setMaxResults(queryParam.getPageParam().getPageSize());
        List rows = jpqlQuery.getResultList();
        List count = countQuery.getResultList();
        Paging paging = new Paging();
        paging.setRows(rows.toArray());
        paging.setCount((int) (long) count.get(0));
        return paging;
    }

    public String getWhereSql(List<QueryCondition> conditions) {
        System.out.println(conditions);
        String whereSql = conditions.size() > 0 ? "where " : "";
        for (int i = 0; i < conditions.size(); i++) {
            if (i == conditions.size() - 1) {
                conditions.get(i).setAndOr("");
            }
            whereSql += conditions.get(i).toWhereSql();
        }
        return whereSql;
    }

    @Transactional
    public boolean save(Object entity) {
        boolean flag = false;
        try {
            this.entityManager.persist(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
            flag = true;
        } catch (Exception e) {
            System.out.println("---------------保存出错---------------");
            throw e;
        }
        return flag;
    }

    @Transactional
    public Object update(Object entity) {
        Object object = this.entityManager.merge(entity);
        this.entityManager.flush();
        this.entityManager.clear();
        return object;
    }

    @Transactional
    public void delete(Object entity) {
        this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
        this.entityManager.flush();
        this.entityManager.clear();
    }

    @Transactional
    public void delete(String className, Integer id) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        this.entityManager.remove(this.entityManager.find(clazz, id));
        this.entityManager.flush();
        this.entityManager.clear();
    }

    @Transactional
    public void delete(Class clazz, Integer id) {
        this.entityManager.remove(this.entityManager.find(clazz, id));
        this.entityManager.flush();
        this.entityManager.clear();
    }

    public Object findById(String className, Integer id) throws ClassNotFoundException {
        Class clazz = Class.forName(className);
        return entityManager.find(clazz, id);
    }

    public Object findById(Class clazz, Integer id) {
        return this.entityManager.find(clazz, id);
    }

    public <T> T findOne(Q q, Class<T> clazz) {
        QueryParam queryparam = q.getQueryParam();
        String name = clazz.getSimpleName();
        System.out.println(name);
        String jpql = "select u from " + name + " u " + this.getWhereSql(queryparam.getQueryConditions());
        Query jpqlQuery = this.entityManager.createQuery(jpql);
        if (jpqlQuery.getResultList().size() > 0) {
            return (T) jpqlQuery.getResultList().get(0);
        } else {
            return null;
        }

    }

    public <T> T findOne(QueryParam queryparam, Class<T> clazz) {
        String name = clazz.getSimpleName();
        System.out.println(name);
        String jpql = "select u from " + name + " u " + this.getWhereSql(queryparam.getQueryConditions());
        Query jpqlQuery = this.entityManager.createQuery(jpql);
        if (jpqlQuery.getResultList().size() > 0) {
            return (T) jpqlQuery.getResultList().get(0);
        } else {
            return null;
        }

    }

    public <T> List<T> find(QueryParam queryParam, Class<T> clazz) {
        String name = clazz.getSimpleName();
        System.out.println(name);
        String jpql = "select u from " + name + " u " + this.getWhereSql(queryParam.getQueryConditions());
        Query jpqlQuery = this.entityManager.createQuery(jpql);
        return jpqlQuery.getResultList();
    }

    public List summary(List<Summary> summarys, Class clazz) {
        String str = "";
        for (int i = 0; i < summarys.size(); i++) {

            str += summarys.get(i).getMethod() + "(" + summarys.get(i).getFieldName() + ")";
            if (i != summarys.size() - 1) {
                str += ",";
            }
        }
        String summaryJpql = "select " + str + " from " + clazz.getSimpleName() + " u ";
        Query jpqlQuery = this.entityManager.createQuery(summaryJpql);
        return jpqlQuery.getResultList();
    }
}