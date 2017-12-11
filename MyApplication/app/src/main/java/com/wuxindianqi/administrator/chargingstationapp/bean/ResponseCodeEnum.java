package com.wuxindianqi.administrator.chargingstationapp.bean;

/**
 * The enum Response code enum.
 *
 * @author huangs
 * @createtime 2017-10-10
 * @description 服务器响应编码枚举类
 */
public enum ResponseCodeEnum {

    /**
     * 响应成功.
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 响应失败.
     */
    ERROR(1, "ERROR"),
    /**
     * 非法参数.
     */
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    /**
     * 需要登录状态.
     */
    NEED_LOGIN(10, "NEED_LOGIN"),
    /**
     * token超时（token会在4小时后超时）.
     */
    TOKEN_OUT_OF_DATE(1300, "TOKEN_OUT_OF_DATE"),
    /**
     * 请求的资源不存在.
     */
    RESOURCE_NOT_FOUND(1400, "RESOURCE_NOT_FOUND"),
    /**
     * 参数错误（参数可能为空、零、NULL、或不符合约定规则）.
     */
    ARGUMENT_ERROR(1401, "ARGUMENT_ERROR"),
    /**
     * 图片文件不存在.
     */
    IMAGE_NOT_FOUND(1402, "IMAGE_NOT_FOUND"),
    /**
     * 无可用充电枪.
     */
    NO_CHARGER_GUN_AVAILABLE(1403, "NO_CHARGER_GUN_AVAILABLE"),
    /**
     * 前置条件不符.
     */
    PRECONDITION_ERROR(1405, "PRECONDITION_ERROR"),
    /**
     * 验证码过期.
     */
    VERIFY_CODE_OUT_OF_DATE(1406, "VERIFY_CODE_OUT_OF_DATE"),
    /**
     * 验证码出错.
     */
    VERIFY_CODE_ERROR(1407, "VERIFY_CODE_ERROR"),
    /**
     * 充电桩未插枪.
     */
    CHARGER_GUN_NOT_PULLED_OUT(1408, "CHARGER_GUN_NOT_PULLED_OUT"),
    /**
     * 数据唯一性出错.
     */
    DATA_UNIQUENESS_ERROR(1409, "DATA_UNIQUENESS_ERROR"),
    /**
     * 距离上次发送验证码短信不超过1分钟.
     */
    VERIFY_CODE_TOO_FREQUENTLY(1410, "VERIFY_CODE_TOO_FREQUENTLY"),
    /**
     * 密码错误.
     */
    PASSWORD_ERROR(1411, "PASSWORD_ERROR"),
    /**
     * 图片存储异常.
     */
    IMAGE_STORAGE_ERROR(1412, "IMAGE_STORAGE_ERROR"),
    /**
     * 电站可收藏数已达到最大.
     */
    STATION_COLLECTION_OUT_OF_LIMIT(1413, "STATION_COLLECTION_OUT_OF_LIMIT"),
    /**
     * 充值请求失败.
     */
    RECHARGE_REQUEST_FAIL(1414, "RECHARGE_REQUEST_FAIL"),
    /**
     * 充值请求参数错误.
     */
    RECHARGE_ARGUMENT_ERROR(1415, "RECHARGE_ARGUMENT_ERROR"),
    /**
     * 充值返回数据签名校验失败.
     */
    RECHARGE_CALLBACK_ERROR(1416, "RECHARGE_CALLBACK_ERROR"),
    /**
     * 充值失败，余额不足.
     */
    RECHARGE_INSUFFICIENT_BALANCE(1417, "RECHARGE_INSUFFICIENT_BALANCE"),
    /**
     * 充值失败，其他错误.
     */
    RECHARGE_OTHER_ERROR(1418, "RECHARGE_OTHER_ERROR"),
    /**
     * TCP通讯错误.
     */
    TCP_COMMUNICATION_ERROR(1419, "TCP_COMMUNICATION_ERROR"),
    /**
     * 操作失败.
     * 该错误一般用于主要业务流程，例如：预约、开始充电、停止充电等等
     */
    OPERATION_ERROR(1420, "OPERATION_ERROR"),
    /**
     * 电站已经收藏过.
     */
    STATION_ALREADY_COLLECTED(1421, "STATION_ALREADY_COLLECTED"),
    /**
     * SN或二维码错误.
     */
    QR_CODE_SERIAL_NUMBER_ERROR(1422, "QR_CODE_SERIAL_NUMBER_ERROR"),
    /**
     * 充电桩离线.
     */
    CHARGER_OFF_LINE(1423, "CHARGER_OFF_LINE"),
    /**
     * 充电桩类型错误.
     */
    CHARGER_TYPE_ERROR(1424, "CHARGER_TYPE_ERROR"),
    /**
     * 客户端类型错误.
     */
    CLIENT_TYPE_ERROR(1425, "CLIENT_TYPE_ERROR"),
    /**
     * 公众号获取openid、ticket失败.
     */
    WECHAT_MEDIA_PLATFORM_ERROR(1426, "WECHAT_MEDIA_PLATFORM_ERROR"),
    /**
     * 充电桩当前为非空闲状态.
     */
    CHARGER_NOT_IDLE_STATE(1427, "CHARGER_NOT_IDLE_STATE"),
    /**
     * openid为空时，trade_type参数只能为JSAPI.
     */
    TRADE_TYPE_ERROR(1428, "TRADE_TYPE_ERROR"),
    /**
     * 充电枪类型错误，暂不支持多枪类型的操作.
     */
    CHARGER_GUN_TYPE_ERROR(1429, "CHARGER_GUN_TYPE_ERROR"),
    /**
     * 充电桩锁定超时.
     */
    CHARGER_LOCKED_TIME_OUT(1430, "CHARGER_LOCKED_TIME_OUT"),
    /**
     * 检测到该电站电费或停车费模板id为空.
     */
    STATION_FEE_TEMPLATE_ID_EMPTY(1431, "STATION_FEE_TEMPLATE_ID_EMPTY"),
    /**
     * 当前电站处于不可用状态.
     */
    STATION_NOT_AVAILABLE(1432, "STATION_NOT_AVAILABLE"),
    /**
     * 充电桩已被他人使用.
     */
    CHARGER_ALREADY_IN_USE(1433, "CHARGER_ALREADY_IN_USE"),
    /**
     * 充电桩繁忙.
     * 用户在操作充电桩屏幕，同时又通过APP发送指令
     */
    CHARGER_BUSY(1434, "CHARGER_BUSY"),
    /**
     * 充电桩未上线.
     * 充电桩从未连接至SP，关联的gate、station均为空
     */
    CHARGER_NOT_REGISTERED(1435, "CHARGER_NOT_REGISTERED"),
    /**
     * 未找到对应订单.
     */
    ORDER_NOT_FOUND(1436, "ORDER_NOT_FOUND"),
    /**
     * 平台商不匹配.
     */
    PLATFORM_NO_MATCH(1437, "PLATFORM_NO_MATCH"),
    /**
     * 用户在其他电站有同时建立多个订单的权限而在本站没用，且已有未关闭的订单存在.
     */
    USER_ORDER_AUTHORITY_ERROR(1438, "USER_ORDER_AUTHORITY_ERROR"),
    /**
     * 充电服务已结束.
     * 直流桩独有
     */
    CHARGE_SERVICE_ENDED(1439, "CHARGE_SERVICE_ENDED"),
    /**
     * 充电桩不可预约.
     */
    CHARGER_NOT_BOOKABLE(1440, "CHARGER_NOT_BOOKABLE"),
    /**
     * 电站状态错误，不可预约.
     */
    STATION_STATUS_ERROR(1441, "STATION_STATUS_ERROR"),
    /**
     * 存在未支付的订单.
     */
    ORDER_NOT_PAID(1442, "ORDER_NOT_PAID"),
    /**
     * 支付结果查询失败.
     */
    PAYMENT_INQUIRY_FAIL(1443, "PAYMENT_INQUIRY_FAIL"),
    /**
     * 支付失败.
     */
    PAYMENT_FAIL(1444, "PAYMENT_FAIL"),
    /**
     * 发送短信异常.
     */
    MESSAGE_SEND_ERROR(1501, "MESSAGE_SEND_ERROR"),
    /**
     * 命令发送前校验失败，取消发送.
     */
    CHECK_BEFORE_COMMAND_SEND_FAIL(1504, "CHECK_BEFORE_COMMAND_SEND_FAIL"),
    /**
     * 服务器控制充电桩失败.
     */
    CHARGER_CONTROL_ERROR(1505, "CHARGER_CONTROL_ERROR"),
    /**
     * 用户余额不足，不支持预约或者扫码.
     */
    USER_INSUFFICIENT_BALANCE(1506, "USER_INSUFFICIENT_BALANCE"),
    /**
     * 不支持随机预约.
     */
    RANDOM_BOOK_NOT_SUPPORT(1507, "RANDOM_BOOK_NOT_SUPPORT"),
    /**
     * 电桩、充电桩不支持预约.
     */
    BOOK_NOT_SUPPORT(1508, "BOOK_NOT_SUPPORT"),
    /**
     * 充电桩不在工作中.
     */
    CHARGER_NOT_IN_WORK(1509, "CHARGER_NOT_IN_WORK"),
    /**
     * 服务器错误.
     */
    SERVER_ERROR(1600, "SERVER_ERROR"),
    /**
     * 对接平台不存在.
     */
    DOCKING_PLATFORM_NOT_EXIST(2001, "DOCKING_PLATFORM_NOT_EXIST"),
    /**
     * 对接业务处理失败.
     */
    DOCKING_BUSINESS_PROCESS_FAIL(2002, "DOCKING_BUSINESS_PROCESS_FAIL"),
    /**
     * 充电桩不存在.
     */
    CHARGER_NOT_EXIST(2003, "CHARGER_NOT_EXIST"),
    /**
     * 对接业务费率、电费类型设置错误.
     */
    DOCKING_FEE_RATE_AND_TYPE_ERROR(2004, "DOCKING_FEE_RATE_AND_TYPE_ERROR"),
    /**
     * 对接业务设置费率失败.
     */
    DOCKING_FEE_RATE_SET_FAIL(2405, "DOCKING_FEE_RATE_SET_FAIL"),
    /**
     * 对接业务添加网关失败--网关已存在.
     */
    GATEWAY_ADD_ERROR(2006, "GATEWAY_ADD_ERROR"),
    /**
     * 对接业务删除网关失败--网关不存在.
     */
    GATEWAY_DELETE_ERROR(2007, "GATEWAY_DELETE_ERROR");

    /**
     * 服务器响应编码.
     */
    private final int code;

    /**
     * 响应编码对应的描述字符串.
     */
    private final String desc;

    /**
     * 构造器.
     *
     * @param code 服务器响应编码
     * @param desc 编码对应状态描述
     */
    ResponseCodeEnum(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Gets code.
     *
     * @return the code
     */

    public int getCode() {
        return code;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */

    public String getDesc() {
        return desc;
    }
}
