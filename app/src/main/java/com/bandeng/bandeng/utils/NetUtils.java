package com.bandeng.bandeng.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class NetUtils {
    /**
     * 将params拼接成一个请求地址
     *
     * @param parmas 含有请求参数的HashMap集合
     * @return 返回拼接好的参数字符串，如：index=0&age=25&name=zs
     */
    public static String getRequestParams(HashMap<String, String> parmas) {
        Set<String> keySet = parmas.keySet();    // Set是无序的，无法排序，所以转换为ArrayList
        ArrayList<String> keys = new ArrayList<String>(keySet);
//        Collections.sort(keys);               // 对集体排序

        // http://127.0.0.1:8080/home?index=0&age=25&name=zs
        // 把所有的参数拼接成index=0&age=47&name=zs
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append("&").append(key).append("=").append(parmas.get(key));
        }
        // 上面for循环走完得到这样的字符串：&index=0&age=25&name=zs
        sb.deleteCharAt(0);// 删除第0位置的符号：&，得到：index=0&age=25&name=zs
        return sb.toString();
    }

}
