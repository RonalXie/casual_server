package com.ronalxie.casual_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 *
 * @author bing  @create 2021/1/13-下午8:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object record;

    /**
     * 成功返回结果 不带对象
     *
     * @param message
     * @return
     */
    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }


    /**
     * 成功返回结果 带对象
     *
     * @param message
     * @param record
     * @return
     */
    public static RespBean success(String message, Object record) {
        return new RespBean(200, message, record);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @return
     */
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @param record
     * @return
     */
    public static RespBean error(String message, Object record) {
        return new RespBean(500, message, record);
    }

}