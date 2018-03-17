package com.ustc.latte.net;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.ustc.latte.net.callback.IError;
import com.ustc.latte.net.callback.IFailure;
import com.ustc.latte.net.callback.IRequest;
import com.ustc.latte.net.callback.ISuccess;
import com.ustc.latte.net.callback.RequestCallbacks;
import com.ustc.latte.net.download.DownloadHandler;
import com.ustc.latte.ui.loader.LatteLoader;
import com.ustc.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by DELL on 2018/3/3.
 */

public class RestClient {

    private final String URL;
    private final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    //上传
    private final File FILE;
    //下载
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    //token
    private final String TOKEN;
    private final Map<String,String> TokenMap = new HashMap<>();

    public RestClient(String url,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle,
                      String token) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
        this.TOKEN = token;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if(REQUEST!=null){
            REQUEST.onRequstStart();
        }

        if (LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case POST_TOKEN:
                call = service.postToken(URL, TokenMap);
                break;
            case POST_TOKEN_PARAMS:
                call = service.postTokenParams(URL, TokenMap,PARAMS);
                break;
            case POST_TOKEN_RAW:
                call = service.postTokenRaw(URL, TokenMap,BODY);
                break;
            case POST_TOKEN_PARAMS_RAW:
                call = service.postTokenParamsRaw(URL, TokenMap,PARAMS,BODY);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if(call!=null){
            //execute()在主线程执行，enqueue()在后台执行
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        if (TextUtils.isEmpty(TOKEN)) {
            request(HttpMethod.POST_RAW);
        } else {
            TokenMap.put("token",TOKEN);
            if (PARAMS.isEmpty()) {
                if(BODY == null){
                    request(HttpMethod.POST_TOKEN);
                }else{
                    request(HttpMethod.POST_TOKEN_RAW);
                }
            }else {
                if(BODY == null){
                    request(HttpMethod.POST_TOKEN_PARAMS);
                }else{
                    request(HttpMethod.POST_TOKEN_PARAMS_RAW);
                }
            }
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }
}
