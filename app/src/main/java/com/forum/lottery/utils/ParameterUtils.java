package com.forum.lottery.utils;

/**
 * 统一参数配置
 */

public class ParameterUtils {

    //域名
    public final class DOMAIN {
        public static final String NORMAL_DOMAIN = "";//正式环境
        public static final String DEMO_DOMAIN = "";//试玩环境
    }

    //各项网络域名的请求地址
    public final class URLS {
        //网络心跳的地址
        public static final String HEART_BEAT_URL = "/im/customer_service/customer_heartbeat.do";
    }

    public final class GlobalConfig {
        //网络通告 或者 客服消息 的参数时间配置
        public static final int HeatBeatMsgAndNoticesInterval = 10 * 1000;
        public static final int HeatBeatMsgAndNoticesDelay = 10;
    }
}
