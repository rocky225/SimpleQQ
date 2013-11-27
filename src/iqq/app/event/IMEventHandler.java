package iqq.app.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 可以使用这个注解来简化事件的分发
 * 加在方法上，参数为IM事件类型
 * 如 @IMEventHandler(IMEventType.LOGIN_PROGRESS)
 *
 * @author solosky <solosky772@qq.com>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IMEventHandler {
    /**标明这个方法处理的事件类型*/
    IMEventType value();
}
