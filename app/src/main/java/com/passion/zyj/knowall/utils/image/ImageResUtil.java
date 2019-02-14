package com.passion.zyj.knowall.utils.image;


import com.passion.zyj.knowall.R;

/**
 * 好吧，让你找到了，这是假的数据源
 * <p>
 * Created by sunfusheng on 16/4/22.
 */
public class ImageResUtil {

    public static int getImgRes(String wid) {
        int resId = 0;
        switch (wid) {
            case "00"://晴
                resId = R.mipmap.sunny_day;
                break;
            case "01"://多云
                resId = R.mipmap.partly_cloudy;
                break;
            case "02"://阴
                resId = R.mipmap.cloudy_day;
                break;
            case "03"://阵雨
                resId = R.mipmap.shower;
                break;
            case "04"://雷阵雨
                resId = R.mipmap.thunder_shower;
                break;
            case "05"://雷阵雨伴有冰雹
                resId = R.mipmap.thunder_shower_by_hail;
                break;
            case "06"://雨夹雪
                resId = R.mipmap.sleet;
                break;
            case "07"://小雨
                resId = R.mipmap.lt_rain;
                break;
            case "08"://中雨
                resId = R.mipmap.md_rain;
                break;
            case "09"://大雨
                resId = R.mipmap.hv_rain;
                break;
            case "10"://暴雨
                resId = R.mipmap.rainstorm;
                break;
            case "11"://大暴雨
                resId = R.mipmap.hv_rain_storm;
                break;
            case "12"://特大暴雨
                resId = R.mipmap.ex_hv_rain_storm;
                break;
            case "13"://阵雪
                resId = R.mipmap.snow_shower;
                break;
            case "14"://小雪
                resId = R.mipmap.lt_snow;
                break;
            case "15"://中雪
                resId = R.mipmap.md_snow;
                break;
            case "16"://大雪
                resId = R.mipmap.hv_snow;
                break;
            case "17"://暴雪
                resId = R.mipmap.blizzard;
                break;
            case "18"://雾
                resId = R.mipmap.fog;
                break;
            case "19"://冻雨
                resId = R.mipmap.frozen_rain;
                break;
            case "20"://沙尘暴
                resId = R.mipmap.sandstorm;
                break;
            case "21"://小雨-中雨
                resId = R.mipmap.lt_to_md_rain;
                break;
            case "22"://中雨-大雨
                resId = R.mipmap.md_to_hv_rain;
                break;
            case "23"://大雨-暴雨
                resId = R.mipmap.hv_to_storm_rain;
                break;
            case "24"://暴雨-大暴雨
                resId = R.mipmap.rainstorm_to_hv_rainstorm;
                break;
            case "25"://大暴雨-特大暴雨
                resId = R.mipmap.hv_to_exhv_storm_rain;
                break;
            case "26"://小雪-中雪
                resId = R.mipmap.lt_to_md_snow;
                break;
            case "27"://中雪-大雪
                resId = R.mipmap.md_to_hv_snow;
                break;
            case "28"://大雪-暴雪
                resId = R.mipmap.hv_to_storm_snow;
                break;
            case "29"://浮尘
                resId = R.mipmap.floating_dust;
                break;
            case "30"://扬沙
                resId = R.mipmap.blowing_sand;
                break;
            case "31"://强沙尘暴
                resId = R.mipmap.strong_sandstorm;
                break;
            case "53"://霾
                resId = R.mipmap.haze;
                break;
        }
        return resId;
    }

}
