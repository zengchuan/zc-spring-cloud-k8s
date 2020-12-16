package com.zengc.core.exception;

/**
 * @author mengqch
 * @ClassName: HMSException
 * @Description: 自定义异常类
 */
public class ZCException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Throwable cause;
    private int errcode;
    private String message;
    public static final String UNKOWN_EXCEPTION = "未知异常";
    public static final Integer UNKOWN_EXCEPTION_CODE = 1;


    public ZCException(Exception e) {
        super(e.getMessage());
    }

    public ZCException(int errcode, String message) {
        super(message);
        this.message = message;
        this.errcode = errcode;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
    public ZCException(int errcode, String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
        this.message = message;
        this.errcode = errcode;
    }

    public ZCException(String message) {
        super(message);
        this.message = message;
    }

    public int getErrcode() {
        return errcode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
