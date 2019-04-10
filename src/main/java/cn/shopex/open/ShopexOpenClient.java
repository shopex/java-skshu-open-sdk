package cn.shopex.open;

import cn.shopex.open.util.SignTools;
import cn.shopex.open.util.URLParser;
import cn.shopex.open.util.WebUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;


import org.apache.log4j.Logger;

/**
 * <p>ShopexOpenClient 客户端</p>
 */
public class ShopexOpenClient {

    private static Logger logger = Logger.getLogger(ShopexOpenClient.class);

    private Map<String, String> commonSysParams;//api请求系统级参数
    private String urlStr;//API请求URL
    private String appkey;//应用appkey
    private String secret;//应用密钥

    private URLParser urlParser;

    public ShopexOpenClient(String url, String appkey, String secret) {
        this.urlStr = url;
        this.appkey = appkey;
        this.secret = secret;
        urlParser = new URLParser(urlStr);
        this.initSysParams();
    }


    /**
     * 初始化组装系统级参数
     */
    private void initSysParams() {
        commonSysParams = new HashMap<String, String>();
        commonSysParams.put("app_key", appkey);//app_key
        commonSysParams.put("sign_time", String.valueOf(new Date().getTime() / 1000));
        commonSysParams.put("timestamp", String.valueOf(new Date().getTime()));
    }

    /**
     * 执行api post请求
     *
     * @param appParams 应用级参数
     * @return
     * @throws IOException
     */
    public String doPost(Map<String, String> appParams) throws IOException {
        String postUrlStr = urlParser.getSiteWithAppendPath("");
        return WebUtils.doPost(postUrlStr, assembleParams(getHeaders(), appParams, Constant.METHOD_POST, new URL(postUrlStr).getPath()), getHeaders(), 5000, 5000);
    }


    /**
     * 执行api post请求
     *
     * @param sysParams 系统级参数
     * @param appParams 应用级参数
     * @return
     * @throws IOException
     */
    public String doPost(Map<String, String> sysParams, Map<String, String> appParams) throws IOException {
        String postUrlStr = urlParser.getSiteWithAppendPath("");
        if (urlParser.isHttps()) {
            return WebUtils.doPost(postUrlStr, assembleParams(sysParams, appParams), getHeaders(), 5000, 5000);
        } else {
            appParams.putAll(sysParams);
            return WebUtils.doPost(postUrlStr, assembleParams(getHeaders(), appParams, Constant.METHOD_POST, new URL(postUrlStr).getPath()), getHeaders(), 5000, 5000);
        }

    }


    /**
     * 设置HTTP header
     */
    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", "SkShuOpenSDK/Java");
        return headers;
    }

    /**
     * 组装所有请求参数
     */
    public Map<String, String> assembleParams(Map<String, String> sysParams, Map<String, String> appParams) {
        Map<String, String> allParams = new HashMap<String, String>();
        allParams.putAll(commonSysParams);
        allParams.putAll(sysParams);
        if (appParams != null && allParams.size() > 0) {
            allParams.putAll(appParams);
        }

        String sign = sign(allParams);
        allParams.put(Constant.SIGN, sign);
        return allParams;
    }


    /**
     * 组装所有请求参数
     */
    public Map<String, String> assembleParams(Map<String, String> headers, Map<String, String> appParams, String methodType, String urlPath) {
        Map<String, String> allParams = new HashMap<String, String>();
        allParams.putAll(commonSysParams);
        if (appParams != null && allParams.size() > 0) {
            allParams.putAll(appParams);
        }

        String sign = "";
        if (methodType.equals(Constant.METHOD_GET)) {
            sign = sign(headers, allParams, null, Constant.METHOD_GET, urlPath);
        } else if (methodType.equals(Constant.METHOD_POST)) {
            sign = sign(headers, null, allParams, Constant.METHOD_POST, urlPath);
        }
        allParams.put(Constant.SIGN, sign);
        return allParams;
    }


    /**
     * 执行签名 for https
     * https:// appsecret+"&"+timestamp+"&"+jsondata+"&"+appsecret
     *
     * @return
     */
    private String sign(Map<String, String> allParams) {
        //签名拼接字符串
        String mixAllParams = secret + Constant.SEPARATOR + allParams.get("timestamp") + Constant.SEPARATOR + allParams.get("jsondata") + Constant.SEPARATOR + secret;

        logger.debug("sing_str is " + mixAllParams);

        try {
            return SignTools.byte2hex(SignTools.encryptMD5(mixAllParams), true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 执行签名 for http
     *
     * @param headerParams http头信息
     * @param getParams    get参数
     * @param postParams   post参数
     * @param method       http请求方式
     * @param path         http请求path
     * @return
     */
    private String sign(Map<String, String> headerParams, Map<String, String> getParams, Map<String, String> postParams, String method, String path) {
        //header数据拼接字符串
        String mixHeaderParams = SignTools.mixHeaderParams(headerParams);
        //get数据拼接字符串
        String mixGetParams = SignTools.mixRequestParams(getParams);
        //post数据拼接字符串
        String mixPostParams = SignTools.mixRequestParams(postParams);
        //签名拼接字符串
        String mixAllParams = secret + Constant.SEPARATOR + method + Constant.SEPARATOR + urlencode(path) + Constant.SEPARATOR + urlencode(mixHeaderParams) + Constant.SEPARATOR + urlencode(mixGetParams)
                + Constant.SEPARATOR + urlencode(mixPostParams) + Constant.SEPARATOR + secret;

        logger.debug("sing_str is " + mixAllParams);

        //加密签名
        try {
            return SignTools.byte2hex(SignTools.encryptMD5(mixAllParams), true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String urlencode(String str) {
        try {
            return URLEncoder.encode(str, Constant.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
