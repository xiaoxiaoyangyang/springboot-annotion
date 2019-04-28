package com.yangzai.collection.vo;

import com.yangzai.collection.constant.ErrorMessage;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/4/26 14:12
 * @Version 1.0
 */
@Data
public class UserBody {
    private Integer id;
    @NotNull(message = ErrorMessage.PARAM_NULL_ERROR)
    private String name;
    @NotBlank(message = ErrorMessage.PARAM_NULL_ERROR)
    private String password;
}
