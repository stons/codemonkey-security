package vip.codemonkey.security.core.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wang zhengtao
 */
@Data
public class SimpleResponse implements Serializable {
    private String message;
    private Object data;
    private Integer code = 200;

    public SimpleResponse(){}

    public SimpleResponse(String message, Object data, Integer code) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public SimpleResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static SimpleResponse success(String message){
        return success(message,null);
    }

    public static SimpleResponse success(String message,Object data){
        return new SimpleResponse(message,data);
    }

    public static SimpleResponse error(String message,Integer code){
        return new SimpleResponse(message,null,code);
    }
}
