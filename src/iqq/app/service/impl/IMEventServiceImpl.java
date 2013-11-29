package iqq.app.service.impl;

import iqq.app.core.IMService;
import iqq.app.event.IMEvent;
import iqq.app.event.IMEventListener;
import iqq.app.event.IMEventType;
import iqq.app.service.IMEventService;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AbstractServiceImpl的实现类,作为一个广播
 * <br/>  <br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 3:17 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMEventServiceImpl extends AbstractServiceImpl implements IMEventService, IMService {

    private Map<IMEventType, List<IMEventListener>> lookup;

    public IMEventServiceImpl() {
        lookup = new HashMap<IMEventType, List<IMEventListener>>();
    }

    @Override
    public void broadcast(final IMEvent event) {
        // 如果正在调用的线程是当前 AWT EventQueue 的指派线程，则返回 true。
        if (EventQueue.isDispatchThread()) {
            doBroadcast(event);
        } else {
            // 导致 runnable 的 run 方法在 the system EventQueue 的指派线程中被调用。
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    doBroadcast(event);
                }
            });
        }
    }

    @Override
    public void register(IMEventType[] intrestedEvents, IMEventListener listener) {
        for (IMEventType type : intrestedEvents) {
            List<IMEventListener> list = lookup.get(type);
            if (list == null) {
                list = new ArrayList<IMEventListener>();
                lookup.put(type, list);
            }
            list.add(listener);
        }
    }

    private void doBroadcast(IMEvent event) {
        // 通过Type获取对应的Listener
        java.util.List<IMEventListener> list = lookup.get(event.getType());
        if (list != null && list.size() > 0) {
            list = new ArrayList<IMEventListener>(list);
            // 循环调用所有的Listener
            for (IMEventListener listener : list) {
                try {
                    // 调用其onIMEvent方法
                    listener.onIMEvent(event);
                    if (event.isCancelBubble()) {
                        return;
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
