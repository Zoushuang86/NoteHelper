package com.ustc.latte.net;

/**
 * Created by DELL on 2018/3/3.
 */

public enum HttpMethod {
    GET,
    POST_RAW,           //无Token
    POST_TOKEN,         //有Token，无参数，无body
    POST_TOKEN_PARAMS,  //有Token，有参数，无body
    POST_TOKEN_RAW,     //有Token，无参数，有body
    POST_TOKEN_PARAMS_RAW,     //有Token，有参数，有body
    PUT,
    PUT_RAW,
    DELETE,
    UPLOAD
}
