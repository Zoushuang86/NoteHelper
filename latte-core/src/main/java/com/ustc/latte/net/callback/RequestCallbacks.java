package com.ustc.latte.net.callback;

import android.os.Handler;

import com.ustc.latte.ui.loader.LatteLoader;
import com.ustc.latte.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2018/3/3.
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;
    //模仿网络延迟
    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS!=null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR!=null){
                ERROR.onError(response.code(),response.message());
            }
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE!=null){
            FAILURE.onFailure();
        }

        if(REQUEST!=null){
            REQUEST.onRequstEnd();
        }

        stopLoading();
    }

    private void stopLoading(){
        if(LOADER_STYLE!=null){
//            HANDLER.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    LatteLoader.stopLoading();
//                }
//            },2000);
            LatteLoader.stopLoading();

        }
    }
}
