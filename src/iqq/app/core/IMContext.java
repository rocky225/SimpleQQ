package iqq.app.core;

/**
 * IM环境上下文，所有的模块都是用IMContext来获取对象<br></br>
 *
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 10:03 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface IMContext {
    public <T extends IMService> T getSerivce(IMService.Type type);
}
