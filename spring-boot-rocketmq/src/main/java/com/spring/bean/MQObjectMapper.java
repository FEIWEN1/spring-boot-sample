package com.spring.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 获取ObjectMapper单例类
 *
 * @author shichao
 * @date 2018/7/6 下午4:26
 */
public class MQObjectMapper {

    private MQObjectMapper() {
    }

    private static class MQObjectMapperHolder {
        private final static ObjectMapper MAPPER = new ObjectMapper();
    }

    public static ObjectMapper getInstance() {
        return MQObjectMapperHolder.MAPPER;
    }

}
