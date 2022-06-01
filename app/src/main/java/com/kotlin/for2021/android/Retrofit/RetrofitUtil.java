package com.kotlin.for2021.android.Retrofit;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class RetrofitUtil {


    interface Api{

        @GET("")
        Call<String>  getUserName();

    }


    private void  init(Context context){

        //1、设置一些基础参数 CallAdapter 、 ConverterFactory
        final Retrofit build = new Retrofit.Builder()//
                //.addCallAdapterFactory()//设置请求的适配 Rxjava2、默认等
                .addConverterFactory(GsonConverterFactory.create())//设置数据转换的适配器
                .build();

        //通过动态代理设置代理对象
        final Api api = build.create(Api.class);

        //调用调用接口方法，通过动态代理，返回Call对象
        final Call<String> name = api.getUserName();


        //请求接口返回数据
        name.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //主线程执行，Handler处理
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
}
