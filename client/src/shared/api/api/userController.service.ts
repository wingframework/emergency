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
 *//* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional } from '@angular/core';
import {
    HttpClient, HttpHeaders, HttpParams,
    HttpResponse, HttpEvent
} from '@angular/common/http';
import { CustomHttpUrlEncodingCodec } from '../encoder';

import { Observable } from 'rxjs';

import { Dto } from '../model/dto';
import { RegisterInputDto } from '../model/registerInputDto';
import { RtnLoginUserOutput } from '../model/rtnLoginUserOutput';
import { RtnRegisterUserOutput } from '../model/rtnRegisterUserOutput';
import { RtnUser } from '../model/rtnUser';
import { Rtnboolean } from '../model/rtnboolean';
import { UserForgetPasswordDto } from '../model/userForgetPasswordDto';

import { BASE_PATH, COLLECTION_FORMATS } from '../variables';
import { Configuration } from '../configuration';


@Injectable()
export class UserControllerService {

    protected basePath = '';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional() @Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * GetUserInfo
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getUserInfoUsingGET(observe?: 'body', reportProgress?: boolean): Observable<RtnUser>;
    public getUserInfoUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<RtnUser>>;
    public getUserInfoUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<RtnUser>>;
    public getUserInfoUsingGET(observe: any = 'body', reportProgress: boolean = false): Observable<any> {

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.request<RtnUser>('get', `${this.basePath}/api/user/GetUserInfo`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * RegisterUser
     * 
     * @param body registerInputDto
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public registerUserUsingPOST(body: RegisterInputDto, observe?: 'body', reportProgress?: boolean): Observable<RtnRegisterUserOutput>;
    public registerUserUsingPOST(body: RegisterInputDto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<RtnRegisterUserOutput>>;
    public registerUserUsingPOST(body: RegisterInputDto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<RtnRegisterUserOutput>>;
    public registerUserUsingPOST(body: RegisterInputDto, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling registerUserUsingPOST.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<RtnRegisterUserOutput>('post', `${this.basePath}/api/user/register`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * UserForgetPassword
     * 
     * @param body userForgetPasswordDto
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public userForgetPasswordUsingPOST(body: UserForgetPasswordDto, observe?: 'body', reportProgress?: boolean): Observable<Rtnboolean>;
    public userForgetPasswordUsingPOST(body: UserForgetPasswordDto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Rtnboolean>>;
    public userForgetPasswordUsingPOST(body: UserForgetPasswordDto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Rtnboolean>>;
    public userForgetPasswordUsingPOST(body: UserForgetPasswordDto, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling userForgetPasswordUsingPOST.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<Rtnboolean>('post', `${this.basePath}/api/user/ForgetPassword`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * 登录
     * 
     * @param body userLoginDto
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public userLoginUsingPOST(body: Dto, observe?: 'body', reportProgress?: boolean): Observable<RtnLoginUserOutput>;
    public userLoginUsingPOST(body: Dto, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<RtnLoginUserOutput>>;
    public userLoginUsingPOST(body: Dto, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<RtnLoginUserOutput>>;
    public userLoginUsingPOST(body: Dto, observe: any = 'body', reportProgress: boolean = false): Observable<any> {

        if (body === null || body === undefined) {
            throw new Error('Required parameter body was null or undefined when calling userLoginUsingPOST.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.request<RtnLoginUserOutput>('post', `${this.basePath}/api/user/login`,
            {
                body: body,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
