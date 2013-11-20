package iqq.app.core;

/**
 * 生命周期管理                              <br></br>
 * 实现了这个接口就可以支持对象的重复使用<br></br>
 * Created with IntelliJ IDEA. <br></br>
 * User: Rocky   <br></br>
 * Date: 11/20/13   <br></br>
 * Time: 9:57 AM   <br></br>
 * To change this template use File | Settings | File Templates.  <br></br>
 */
public interface IMLifeCycle {
    /**
     * 初始化，在使用之前调用
     * @param context	初始化失败抛出
     */
    public void init(IMContext context) throws IMException;

    /**
     * 销毁，在使用完毕之后调用
     * @throws QQException 销毁失败抛出
     */
    public void destroy() throws IMException;
}
