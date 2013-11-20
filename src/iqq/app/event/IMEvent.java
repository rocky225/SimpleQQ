package iqq.app.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 10:56 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMEvent implements Serializable {

    /**
     * 事件数据MAP，也可以直接把事件数据以KEY的形式放入MAP中，处理事件时按KEY读取出来，编程时要确保KEY一致
     */
    private Map<String, Object> data;
    /**
     * 事件ID，定义在IMEventID
     * */
    private IMEventType eventType;
    /**
     * 事件的对象，通常可以作为事件附加数据
     * */
    private Object target;
    /**
     * 是否停止冒泡，一个事件可能有多个事件监听器监听,分发器会按注册的先后顺序逐个的调用，如果设置为取消冒泡，后面的事件监听器不再调用
     * */
    private boolean cancelBubble;
    /**
     * 内部事件ID，递增的
     * */
    private long eventId;
    /**
     * 事件ID计数器
     * */
    private static final AtomicInteger nextEventId = new AtomicInteger(0);
    /**
     * 关联事件，一个事件可能通常和其他事件相关联，比如 加好友结果事件必然和加好友请求事件联系在一起
     * */
    private IMEvent relatedEvent;

    /***
     * 通过一个事件类型和事件对象来构造事件
     * @param eventType
     * @param target
     */
    public IMEvent(IMEventType eventType, Object target) {
        this(eventType, target, null);
    }

    public IMEvent(IMEventType eventType) {
        this(eventType, null, null);
    }

    /**
     *  通过一个事件类型来构造
     * @param eventType
     */
    public IMEvent(IMEventType eventType, Object target, IMEvent related){
        this.eventType = eventType;
        this.target = target;
        this.data = new HashMap<String, Object>();
        this.cancelBubble = false;
        this.eventId = nextEventId.incrementAndGet();
        this.relatedEvent = related;
    }

    /**
     * 获取Enent Type
     * @return the eventType
     */
    public IMEventType getType() {
        return eventType;
    }

    public boolean isCancelBubble() {
        return cancelBubble;
    }
}
