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
import { MetaObject } from './metaObject';

export interface MetaField { 
    alias?: string;
    config?: string;
    displayWidth?: number;
    fieldName?: string;
    fieldType?: string;
    id?: number;
    isQuery?: boolean;
    isShow?: boolean;
    isUpdate?: boolean;
    metaObject?: MetaObject;
    objectCode?: string;
    placeholder?: string;
    presetValue?: string;
    recno?: number;
}