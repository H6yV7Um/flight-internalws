package com.ctrip.ibu.flight.internalws.common.components;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Checker类
 * Create by kyxie on 2018/4/11 12:32
 */
public final class Checker {

    private Checker() throws IllegalAccessException {
        throw new IllegalAccessException("Static class init forbidden.");
    }

    public static void isNotEmpty(String argument, String argumentName) throws IllegalArgumentException {
        if (StringUtils.isBlank(argument))
            throw new IllegalArgumentException(argumentName + " cannot be blank");
    }

    public static <T> void isNotEmpty(Collection<T> argument, String argumentName) throws IllegalArgumentException {
        if (null == argument || argument.size() == 0)
            throw new IllegalArgumentException("Collection cannot be empty." + argumentName);
    }

    public static void isNotNull(Object argument, String argumentName) throws IllegalArgumentException {
        if (null == argument)
            throw new IllegalArgumentException(argumentName + " cannot be null");
    }

    public static void idNotNegative(int argument, String argumentName) throws IllegalArgumentException {
        if (argument < 0)
            throw new IllegalArgumentException(argumentName + " cannot be less zero");
    }

    public static void isNotNegativeOrZero(int argument, String argumentName) throws IllegalArgumentException {
        if (argument <= 0)
            throw new IllegalArgumentException(argumentName + " cannot be less or equal zero");
    }

    public static void idNotNegative(long argument, String argumentName) throws IllegalArgumentException {
        if (argument < 0)
            throw new IllegalArgumentException(argumentName + " cannot be less zero");
    }

    public static void isNotNegativeOrZero(long argument, String argumentName) throws IllegalArgumentException {
        if (argument <= 0)
            throw new IllegalArgumentException(argumentName + " cannot be less or equal zero");
    }

    public static void idNotNegative(float argument, String argumentName) throws IllegalArgumentException {
        if (argument < 0)
            throw new IllegalArgumentException(argumentName + " cannot be less zero");
    }

    public static void isNotNegativeOrZero(float argument, String argumentName) throws IllegalArgumentException {
        if (argument <= 0)
            throw new IllegalArgumentException(argumentName + " cannot be less or equal zero");
    }

    /**
     * 检查是否满足条件，满足则执行Action
     * @param condition 条件
     * @param action 满足条件需要执行的操作
     * */
    public static void checkWithAction(boolean condition,@NotNull Action action){
        if (condition){
            action.execute();
        }
    }

    /**
     * 检查是否满足条件，满足则执行Action
     * @param condition 条件
     * @param conditionMeetAction 满足条件需要执行的操作
     * @param conditionNotMeetAction 条件不满足需要执行的操作
     * */
    public static void checkWithAction(boolean condition,@NotNull Action conditionMeetAction,@NotNull Action conditionNotMeetAction){
        if (condition){
            conditionMeetAction.execute();
        } else {
            conditionNotMeetAction.execute();
        }
    }

    /**
     * 检查是否满足条件,满足condition则抛出异常
     * @param condition 是否满足的条件
     * @param t 自定义异常
     * @throws T 满足condition抛出的异常
     * */
    public static <T extends Throwable> void checkWithThrowable(boolean condition,@NotNull T t) throws T{
        if (condition){
            throw t;
        }
    }

    /**
     * 检查是否满足条件,满足condition则先执行回调方法，再抛出异常
     * @param condition 是否满足的条件
     * @param t 异常
     * @param action 回调函数
     * @throws T 满足condition抛出的异常
     * */
    public static <T extends Throwable> void checkWithThrowable(boolean condition, T t, @NotNull Action action) throws T{
        if (condition){
            action.execute();
            throw t;
        }
    }

}
