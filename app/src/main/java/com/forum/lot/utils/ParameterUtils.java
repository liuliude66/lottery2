package com.forum.lot.utils;

/**
 * 统一参数配置
 */

public class ParameterUtils {

    //域名
    public final class DOMAIN {
        public static final String NORMAL_DOMAIN = "https://app.9877890.com"; //正式环境
        public static final String DEMO_DOMAIN = "https://sw.9877890.com";    //试玩环境
    }

    //各项网络域名的请求地址
    public final class URLS {
        //网络心跳的地址
        public static final String HEART_BEAT_URL = "/im/customer_service/customer_heartbeat.do";
        //登录接口URL
        public static final String LOGIN_URL = "/passport/login.do";
        //获取seesion id 接口
        public static final String SESSION_URL = "/passport/distribute_sessionid.do";

    }

    //本APP配置参数
    public final class GlobalConfig {
        //网络通告 或者 客服消息 的参数时间配置
        public static final int HeatBeatMsgAndNoticesInterval = 10 * 1000;
        public static final int HeatBeatMsgAndNoticesDelay = 10;
    }

    //各种code值
    public final class CODES {
        public static final String BRIDGE_CODE = "BRIDGE_CODE";
        public static final String MESSAGE_CODE = "MESSAGE_CODE";
        //获取session-id时候使用的中间数据传递值
        public static final int NO_JSON_CODE = 0x001000;
        public static final int OBTAIN_SESSION_FAILURE_CODE = 0x000002;
        public static final int OBTAIN_SESSION_SUCCESS_CODE = 0x000003;
        public static final int OBTAIN_SESSION_MESSAGE_CODE = 0x000004;
        //登录时候使用的中间数据传递值
        public static final int LOGIN_FAILURE_CODE = 0x000005;
        public static final int LOGIN_SUCCESS_CODE = 0x000006;
        public static final int LOGIN_MESSAGE_CODE = 0x000007;

    }
}
