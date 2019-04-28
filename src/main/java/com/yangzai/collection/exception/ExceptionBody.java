package com.yangzai.collection.exception;

import lombok.Data;

/**
 * @author guozhiyang_vendor
 */
@Data
public class ExceptionBody {
    private String path;
    private String message;
    private String method;
    public ExceptionBody(){}

    public ExceptionBody(String uri, String message, String method) {
        this.path = uri;
        this.message=message;
        this.method =method;
    }

}
