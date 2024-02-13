package com.qfedu.fmmall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "响应的VO对象", description = "封装接口返回前端的数据")
public class ResultVO {
    //status code
    @ApiModelProperty(value = "状态码", dataType = "int")
    private int code;
    //hint
    @ApiModelProperty(value = "提示信息", dataType = "string")
    private String message;
    //data
    @ApiModelProperty(value = "携带数据", dataType = "object")
    private Object data;
    //token
    @ApiModelProperty(value = "token令牌", dataType = "string")
    private String token;
    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
