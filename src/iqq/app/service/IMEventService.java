package iqq.app.service;

import iqq.app.core.IMService;
import iqq.app.event.IMEvent;
import iqq.app.event.IMEventListener;
import iqq.app.event.IMEventType;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 10:24 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface IMEventService extends IMService {
    /**
     * 广播一个事件，所有对这个感兴趣的监听器都会被调用
     *
     * @param event
     */
    public void broadcast(IMEvent event);

    /**
     * 注册自己感兴趣的事件到事件中心
     * 如果该模块被卸载或者禁用了,请务必取消注册
     *
     * @param intrestedEvents 感兴趣的事件ID，可以是多个
     * @param listener        监听器
     */
    public void register(IMEventType[] intrestedEvents, IMEventListener listener);
}
