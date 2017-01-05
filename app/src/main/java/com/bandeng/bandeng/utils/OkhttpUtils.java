package com.bandeng.bandeng.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private static OkhttpUtils instance;
    private OkHttpClient sOkHttpClient;

    private OkhttpUtils() {
        sOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 根据官网说明，OkHttpClient最好是单例模式
     *
     * @return
     */
    public static OkhttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkhttpUtils.class) {
                if (instance == null) {
                    instance = new OkhttpUtils();
                }
            }
        }
        return instance;
    }

    /**
     * okhttp进行get请求
     *
     * @param url 请求地址
     * @param map 请求参数，没有为null
     * @throws IOException
     */
    public String getRequest(String url, HashMap<String, String> map) throws IOException {
        String params = getRequestParams(map);
        url = map == null ? url : url + "?" + params;

        Request.Builder builder = new Request.Builder();
        final Request request = builder.get().url(url).build();
        Call call = sOkHttpClient.newCall(request);

        Response response = call.execute();

        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response.code());
        }
    }

    /**
     * post请求
     *
     * @param url
     * @param hashMap
     * @return
     */
    public String postRequest(String url, HashMap<String, String> hashMap) throws IOException {

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        formBodyBuilder = getPostRequestParams(formBodyBuilder, hashMap);

        Request.Builder builder = new Request.Builder();
        final Request request = builder.url(url).post(formBodyBuilder.build()).build();

        Call call = sOkHttpClient.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response.code());
        }
    }


    /**
     * 将params拼接成一个请求地址
     *
     * @param parmas 含有请求参数的HashMap集合
     * @return 返回拼接好的参数字符串，如：index=0&age=25&name=zs
     */
    public String getRequestParams(HashMap<String, String> parmas) {
        if (parmas != null && parmas.size() > 0) {
            Set<String> keySet = parmas.keySet();    // Set是无序的，无法排序，所以转换为ArrayList
            ArrayList<String> keys = new ArrayList<String>(keySet);
//        Collections.sort(keys);               // 对集体排序

            // http://127.0.0.1:8080/home?index=0&age=25&name=zs
            // 把所有的参数拼接成index=0&age=47&name=zs
            StringBuilder sb = new StringBuilder();
            for (String key : keys) {
                try {
                    sb.append("&").append(key).append("=").append(URLEncoder.encode(parmas.get(key), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            // 上面for循环走完得到这样的字符串：&index=0&age=25&name=zs
            if (sb.length() > 0)
                sb.deleteCharAt(0);// 删除第0位置的符号：&，得到：index=0&age=25&name=zs
            return sb.toString();
        }
        return "";
    }


    /**
     * 将params拼接成一个请求地址
     *
     * @param parmas 含有请求参数的HashMap集合
     * @return 返回拼接好的参数字符串，如：index=0&age=25&name=zs
     */
    public FormBody.Builder getPostRequestParams(FormBody.Builder builder, HashMap<String, String> parmas) {
        if (parmas != null && parmas.size() > 0) {
            Set<String> keySet = parmas.keySet();    // Set是无序的，无法排序，所以转换为ArrayList
            ArrayList<String> keys = new ArrayList<String>(keySet);

            for (String key : keys) {
                builder.add(key, parmas.get(key));
            }
            return builder;
        }
        return null;
    }
}
