package iqq.app.event;

import iqq.app.core.IMContext;
import iqq.app.core.IMException;
import iqq.app.core.IMService;
import iqq.app.service.IMEventService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/25/13<br/>
 * Time: 3:44 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMEventHandlerProxy implements IMEventListener {

    private IMContext imContext;
    private Object proxyObject;
    private Map<IMEventType, Method> methodMap;
    private static Map<Object, IMEventHandlerProxy> REGISTRY = new HashMap<Object, IMEventHandlerProxy>();

    private IMEventHandlerProxy(Object proxyObject, IMContext imContext){
        this.proxyObject = proxyObject;
        this.imContext = imContext;
        this.methodMap = new HashMap<IMEventType, Method>();
        // 初始化methodMap
        for (Method m : proxyObject.getClass().getDeclaredMethods()) {
            // 把proxyObject中含有@IMEventHandler注释的方法加入到methodMap中
            if(m.isAnnotationPresent(IMEventHandler.class)){
                IMEventHandler handler = m.getAnnotation(IMEventHandler.class);
                this.methodMap.put(handler.value(), m);
                if(!m.isAccessible()){
                    m.setAccessible(true);
                }
            }
        }
    }

    @Override
    public void onIMEvent(IMEvent imEvent) throws IMException {
        //To change body of implemented methods use File | Settings | File Templates.
        Method m =  methodMap.get(imEvent.getType());
        if(m != null){
            try {
                // 调用methodMap中的方法
                m.invoke(proxyObject, imEvent);
            } catch (Throwable e) {
//                LOG.warn("invoke IMEventHandler Error!! proxyObject=" + proxyObject +", method=" + m.getName(), e);
                System.out.println("invoke IMEventHandler Error!! proxyObject=" + proxyObject +", method=" + m.getName());
            }
        }else{
//            LOG.warn("IMEventHandler Not Found!! imEvent = " + imEvent);
             System.out.println("IMEventHandler Not Found!! imEvent = " + imEvent);
        }
    }

    /**
     *  init IMService.Type.EVENT Service
     */
    public void register(){
        IMEventService eventHub = imContext.getSerivce(IMService.Type.EVENT);
        eventHub.register(this.methodMap.keySet().toArray(new IMEventType[0]), this);
    }

    /**
     *
     * @param proxyObject 判断该Object是否已经init
     * @param imContext
     */
    public static void register(Object proxyObject, IMContext imContext) {
        if(!REGISTRY.containsKey(proxyObject)){
            IMEventHandlerProxy proxy = new IMEventHandlerProxy(proxyObject, imContext);
            proxy.register();
            REGISTRY.put(proxyObject, proxy);
        }
    }
}
