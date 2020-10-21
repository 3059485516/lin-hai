package com.ya02wmsj_cecoe.linhaimodule;

import android.os.Environment;
import java.io.File;

public class Constant {
    //新时代文明实践中心和 文化礼堂测试环境地址
    /*private static final String BASE_URL_RELEASE= "http://47.99.86.101:8030/";
    public static final String BASE_URL_LT = "http://47.99.86.101:8040/";*/

    //新时代文明实践中心和 文化礼堂正式环境地址
    private static final String BASE_URL_RELEASE = "https://lhwmsj.hzyltx.com:8443/";
    public static final String BASE_URL_LT = "https://lhwhlt.hzyltx.com:8443/";

    private static final String BASE_URL_INTERFACE_RELEASE = BASE_URL_RELEASE + "ya02wmsj_cecoe/api/";
    public static final String BASE_URL_LT_INTERFACE = BASE_URL_LT + "ya02lhwhlt_wdhaw/api/";    //文化礼堂接口请求地址

    public static String getBaseUrl() {
        return BASE_URL_RELEASE;
    }

    public static String getBaseInterfaceUrl() {
        return BASE_URL_INTERFACE_RELEASE;
    }

    public static final String getTMBasePackageName() {
        return "com.tenma.ventures";
    }

    /*临海栏目编码*/

    // 首页相关-----------------------------------------
    public static final int MAIN = 8;                   //首页
    public static final int MAIN_IDEOLOGY = 14;         //思想引导
    public static final int MAIN_CULTURE = 15;          //文化引导
    public static final int MAIN_WORTH = 16;            //价值引导

    // 六大平台-----------------------------------------
    public static final int PLATFORM = 9;               //六大平台
    public static final int PLATFORM_THEORY = 11;       //理论宣传
    public static final int PLATFORM_EDUC = 12;         //教育服务
    public static final int PLATFORM_CULTURE = 17;      //文化服务
    public static final int PLATFORM_TECHNOLOGY = 18;   //科技与科普服务
    public static final int PLATFORM_SPORT = 19;        //健身体育服务
    public static final int PLATFORM_NET = 20;          //网络惠民服务

    // 网络社区-----------------------------------------
    public static final int COMMUNITY = 13;             //网络社区
    public static final int COMMUNITY_COUNTY = 21;      //全市
    public static final int COMMUNITY_TOWN = 22;        //街镇
    public static final int COMMUNITY_VILLAGE = 23;     //村社

    //默认区域编码
    public static final String DEFAULT_REGION_CODE = "331082000000";

    /**********************新版本用以下请求码***************************************************/
    public static final int MAIN_NEW = 57;                          //首页
    public static final int VOLUNTEER_NEW = 25;                   //志愿服务
    public static final int CULTURE_NEW = 17;                   //文化礼堂

    //Activity传参用
    public static final String KEY_BEAN = "bean";
    public static final String KEY_BEAN_2 = "bean2";
    public static final String KEY_STRING_1 = "String1";
    public static final String KEY_STRING_2 = "String2";
    public static final String KEY_STRING_3 = "String3";
    public static final String KEY_STRING_4 = "String4";
    public static final String KEY_INT_1 = "int1";
    public static final String KEY_BOOLEAN_1 = "boolean1";
    public static final String KEY_BOOLEAN_2 = "boolean2";


    //设置WebView图片的宽度
    public static final String HTML_START_WITH_CLICK = "<html><head><style type=\"text/css\"> img{width:100%;height:auto} body{line-height:1.7;color:#585858}</style> <script type=\"text/javascript\"> "
            + "function imageOnclick(){ var objs = document.getElementsByTagName(\"img\");var array=new Array(); for(var j=0;j<objs.length;j++){ " + "array[j]=objs[j].src;} for(var i=0;i<objs.length;i++){ objs[i].i=i;objs[i].onclick=function(){window.imagelistener.openImage(this.src,array,this.i);" + "}}} "
            + "function urlOnclick(){ var objs = document.getElementsByTagName(\"a\");  for(var i=0;i<objs.length;i++){ objs[i].onclick=function(){window.imagelistener.openBrowse(this.href);}}}"
            + "window.onload = function(){ imageOnclick();urlOnclick();}</script></head><body>";
    public static final String HTML_END = "</body></html>";


    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_fILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "xlgc" + File.separator + "file" + File.separator;

}
