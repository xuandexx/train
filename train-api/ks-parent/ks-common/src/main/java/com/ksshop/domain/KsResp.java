package com.ksshop.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KsResp<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> KsResp<T> fail(Integer code, String msg) {
        return new KsResp<>(code, msg, null);
    }

    public static <T> KsResp<T> success(T data) {
        return new KsResp<>(200, null, data);
    }

}
