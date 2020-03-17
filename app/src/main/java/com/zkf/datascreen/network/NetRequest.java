package com.zkf.datascreen.network;

import android.text.TextUtils;
import android.util.Log;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;

public class NetRequest {

    public static void request(String url, final int tag, Map<String, String> paramMap, final NetRequestResultListener listener) {


//
//        Map<String, String> map = new HashMap<>();
////        map.put("Accept", "*/*");
//        map.put("Content-Type","application/json");

        OkHttpUtils
                .post()
                .url(url)
                .params(paramMap)

                .addHeader("Content-Type", "application/json")

                .build()
                .execute(new StringCallback() {


                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d("zkf","-e:" +e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
	                    if (TextUtils.isEmpty(response)) {
	                        Log.d("zkf","-2:" );

                            listener.requestFailure(tag, -2, "");
                            return;
                        }else {
                            Log.d("zkf","-response:"+response );

                            listener.requestSuccess(tag, response);

	                    }

//                        BaseBean baseBean = JsonUtils.deserialize(response, BaseBean.class);
//                        if (baseBean.getError() == 200) {
//                            if (baseBean.getInfo() == null) {
//                                listener.requestSuccess(tag, "");
//                            } else {
//                                listener.requestSuccess(tag, baseBean.getInfo());
//                            }
//
//                        } else {
//                            listener.requestFailure(tag, baseBean.getError(), baseBean.getMsg());
//                        }
                    }
                });
    }









}
