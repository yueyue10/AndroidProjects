package com.passion.zyj.knowall.app;


import android.os.Environment;

import java.io.File;


/**
 * @author quchao
 * @date 2017/11/27
 */

public class Constants {

    /**
     * APP缓存信息
     */
    //用户信息
    public static String userIcon;//用户头像
    //景区信息
    public static int scenicId = 2;//景区id
    public static boolean isInJq = false;//是否在景区
    public static String scenicName = "涠洲岛";//景区名称
    //设备信息
    public static String DEVICE_ID;//设备标识
    public static boolean isLatestVersion = true;//是否是最新版本

    public static String TOKEN = "Token";//设备标识

    /**
     * 地图相关配置
     */
    //中心点
    public static double center_lat = 21.048132;
    public static double center_lon = 109.122671;
    //当前定位位置
    public static double mLat = 21.048132;
    public static double mLon = 109.122671;
    //地图级别
    public static int map_level = 15;
    public static int max_level = 20;
    public static int min_level = 13;
    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    //界面逻辑标识
    public static String CREATE_ENTRY = ""; //创建行程的入口
    public static String LOGIN_ENTRY = ""; //跳转登录入口

    public static final String MY_SHARED_PREFERENCE = "my_shared_preference";

    static final String BUGLY_ID = "1c46e69634";

    /**
     * url
     */
    public static final String COOKIE = "Cookie";

    public static String DOWN_FileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/Pandora/";//文件下载地址

    /**
     * Path
     */
    public static final String PATH_DATA = MyApp.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    /**
     * Avoid double click time area
     */

    public static final long DOUBLE_INTERVAL_TIME = 2000;

    public static final String VIDEO_URL = "url";
    public static final String TITLE_NAME = "title";
    public static final String PIC_URL = "pic_url";

    public static final String Bean = "bean";
    public static final String Object = "Object";
    public static final String List = "list";
    public static final int Limit = 50;
    /**
     * Shared Preference key
     */

    public static final String AUTO_CACHE_STATE = "auto_cache_state";

    public static final String NO_IMAGE_STATE = "no_image_state";

    public static final String NIGHT_MODE_STATE = "night_mode_state";

    public static final String SCENIC_INFO = "scenic_info";

    public static final String SEARCH_RES_HISTORY = "search_res_history";

    public static final String SEARCH_PLAY_WAY_HISTORY = "search_play_way_history";


    /**
     * 微信支付业务：入参app_id
     */
    public static final String APP_ID = "wxd928befdd8a389ca";

    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2014100900013222";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "2088501624560335";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "20141225xxxx";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC98aoOKirBjPTKN4vLTi8vpYwWTLG6WJh701BnCl3C5gT3lQQGeXf+ZphXBwD9e3fRyJai9cpxiis7eUbonrqrmUoIvbyiAudoYpqnXVKTcaO/rSisr9aa7IOfMqFHEOS61nIlceJja2txCJfUNanS89aghMcvqHLcJ5m0YKN4cimbUUYPELMVI1PsCJyJUNKFiYorQq1/TWWqbvaKRaDRk8NHJgHsKtZFlyhbnm73NsP69NOdKvjQvTlLfpFZE+l8I61sy8Cz4V2E67bwJ2r9/G4f0FVt+F/U4YVegax4w8+w5Y55veazDJrm2gJ4abNk0EETZwyBLPgsbFR2/y3hAgMBAAECggEAe9iVQ7UUuaxZc3wyJvYsaAmtxGBvRYw8qAgJFZY5ujlWJcPAoyQSLAri62OCrsQRRPRf25MdU1h+hcG2jTfpiLdjAT4NPylbjsE0C0oa7E4dMX4K1kW0TMFHtMZDR93o9TWbqXSO4roIjOPIczImL4iTeYf5g8Z2VbtwSZ71FzNgmuAKLy7eobLfewXo1daqBe0ZDGk75RkQ2NR6cJmbLLMbBlTqAbQyBA8KIdue3gR5rruecgsdoMEEBnBPTrZ07U26l1+m3k2lHjE3EsztsQBMDpUwdFKuq4kk7gRBXolj8zqYoKRiOVWy88bvPz8Vx8Vo6XGa0X0S/yBnM5t3kQKBgQDopuLxmxS/3MWcJsIKRYLKUlnWe/So0HbFsV6ij/So/8rfcWkzh9k+qnnSBHtvkQFsnv0+GLd1Hy8Wad9vCyFDessvhuzQRY2Imk9FotaTkYbrchrz0reJUYY6nUklyCTxEAz2UxyLLEIQO0JAhXH8S18YNuh6hVEphmKT2NxpbQKBgQDRAY8vGqJzvznIcCdXXoJx4Ic65dnPRwoEB1abQHnpYqmf8VH/Styk7By1Ap+luP7X3T6QX4gSR4RqC5krJBoz9nAsucgo+t9aUXrhLV83hOoKWD7znX/C1+0b/osRPoexlBmaaf+n+RNIQ3hxU9uIl6WHT01daBL4GVBanxchxQKBgES/e/R1JS6E6If6FADBBaMPrqhovKVd5JsKjLJw45VE8QgSFUo67IFOEu1ykZ8oNEmKub6twxiC/IEdC/9eRJgSIxSKRFRPGUGyh5ZGRi4ZJMtSTpCaRc34HzgW3lShzfjGC26GpLqje2oceLlkNYieJR2crBn4Z0FkCqExxgAJAoGBAMdW/2NjudE/bzMWlM8lmrBV/2RTWPvyu0DAZv/H7P6FVVbw6M3ebrb1YyPZDr8WxCjKISO9maAlictCqKGW2074GmDuCFPdgi04TUR667eeE0IujEv5yaLiIolyqtyVkQHzSMAXnPht/NANWdBstJOAXyXAov8VhhIOwq7L0VopAoGAeZBY2TXgCvvD/7sDQ8hQjxEI0IVORT35bGoNtSce2QDa9lG5cwXOn775TcAhCiWAmLexRKo+1Q/aYVAYTTB6ImEvE5DVb+3vz7a+1Wgw8yz+pijwabVRT0oggBYRWFyNRVjIrrwg3Tg5Me+P65/p64ztOhDotbQl6mtF0CshC44=";
    public static final String RSA_PRIVATE = "";

}
