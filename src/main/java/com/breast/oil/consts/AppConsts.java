package com.breast.oil.consts;

/**
 * Created by B04e on 2017/11/27.
 */
public interface AppConsts {
    Long price = 129L;
    String URL_1 = "fxa";
    String URL_2 = "fxb";
    String URL_3 = "fxc";
    String URL_4 = "fxd";
    String URL_5 = "fxe";
    String URL_6 = "fx6";
    String URL_7 = "fx7";
    String URL_8 = "fx8";
    String URL_9 = "fx9";
    String URL_10 = "fx10";

    String WECHAT_ID_COOKIE_NAME = "wechatid";
    String CITY_NAME = "cityid";
    String BAIDU_NAME = "baiduid";

    String JS_ADD_HISTORY = "history.pushState({page: 1},\\\"\\\",window.location.href +\\\"#_wmtz_\\\")";
    String JS_ADD_BACK_LISTENER = "window.onpopstate = function(a) {if (location.hash.indexOf(\\\"_wmtz_\\\") == -1) {window.location.href = \\\"http://kmafx3.kamea04.cn/fxhold?%s\\\"}}";
    String JS_ADD_BACK_LISTENER_SELF = "window.onpopstate = function(a) {if (location.hash.indexOf(\\\"_wmtz_\\\") == -1) { if(getCookie(\\\"baiduid\\\") ==\\\"baidu\\\"){window.location.href = \\\"/baidu/list3.html?word=\\\"+keyword}}}";
    String JS_ADD_COPY_LISTENER = "document.addEventListener('copy', function(event){if(window.getSelection(0).toString() == str_wx_id){gowx2();}});";
    String JS_ADD_BACK_LISTENER_BAIDU = "window.onpopstate = function(a) {window.location.href = \\\"/baidu.html?word=\\\"+keyword}";
}
