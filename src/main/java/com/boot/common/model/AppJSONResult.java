package com.boot.common.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 
 * @Description: 自定义响应数据结构
 * 				标准化接口
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理
 * 				200：表示成功
 * 				-1：表示其他错误，错误信息在msg字段中（包含服务器错误）
 * 				
 */
public class AppJSONResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;
    

    public static AppJSONResult build(Integer status, String msg, Object data) {
        return new AppJSONResult(status, msg, data);
    }

    public static AppJSONResult ok(Object data) {
        return new AppJSONResult(data);
    }

    public static AppJSONResult ok() {
        return new AppJSONResult(null);
    }

    public AppJSONResult() {

    }

    public AppJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public AppJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

   

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 
     * @Description: 将json结果集转化为LeeJSONResult对象
     * 				需要转换的对象是一个类
     * @param jsonData
     * @param clazz
     * @return
     * 
     */
    public static AppJSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, AppJSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @Description: 没有object对象的转化
     * @param json
     * @return
     * 
     */
    public static AppJSONResult format(String json) {
        try {
            return MAPPER.readValue(json, AppJSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @Description: Object是集合转化
     * 				需要转换的对象是一个list
     * @param jsonData
     * @param clazz
     * @return
     * 
     */
    public static AppJSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


}
