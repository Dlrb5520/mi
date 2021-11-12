package com.mi.excepition;

/**
 * @ClassName ValidateCodeException
 * @Author yangli
 * @Date 2021/8/19 15:50
 * @Description: 验证码异常类
 */
public class ValidateCodeException extends Exception{

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}
