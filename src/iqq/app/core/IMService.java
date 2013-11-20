package iqq.app.core;

/**
 * <b>QQ服务:<br/></b>
 * <p/>
 * 提供和模块与协议无关的公共服务，供模块调用，如定时服务，网络连接服务，异步任务服务等
 * <br></br> <br></br>
 * User: ss <br></br>
 * Date: 11/20/13   <br></br>
 * Time: 9:42 AM    <br></br>
 * To change this template use File | Settings | File Templates.   <br></br>
 */
public interface IMService extends IMLifeCycle {
    public enum Type {
        EVENT,            //事件服务，负责事件的分发
        RESOURCE,        //资源服务，负责本地资源的获取
        MODULE,        //模块管理服务
        TIMER,            //定时服务
        HTTP,            //HTTP
        TASK,            //异步任务，可以执行比较耗时的操作
        I18N,            //国际化支持
        PROP,            //配置
        SKIN,            //皮肤服务
        ACTION            //Swing Action(Button等)
    }
}