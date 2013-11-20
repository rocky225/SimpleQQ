package iqq.app.service;

import iqq.app.core.IMService;
import iqq.app.event.IMEvent;

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
     * @param event
     */
    public void broadcast(IMEvent event);
}
