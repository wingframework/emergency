/**
 * Kitty API Doc
 * This is a restful api document of Kitty.
 *
 * OpenAPI spec version: 1.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { OrderBy } from './orderBy';
import { PageParam } from './pageParam';
import { QueryAttribute } from './queryAttribute';
import { QueryCondition } from './queryCondition';
import { SummaryParam } from './summaryParam';

export interface QueryParam { 
    orderList?: Array<OrderBy>;
    pageParam?: PageParam;
    queryAttributes?: Array<QueryAttribute>;
    queryConditions?: Array<QueryCondition>;
    summaryParam?: Array<SummaryParam>;
}