package com.mi.excepition;

/**
 * @ClassName MiExcepition
 * @Author yangli
 * @Date 2021/7/14 21:49
 * @Description:
 */
public class MiException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public MiException(String message){
        super(message);
    }
}
