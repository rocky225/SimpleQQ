package iqq.app;

import com.alee.laf.rootpane.WebWindow;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: ss
 * Date: 11/19/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class IMApp {

    private static final Logger LOG = Logger.getLogger(IMApp.class);
    private static final IMApp instance = new IMApp();
    private WebWindow startWin;

    /**
     * 获取一个私有的初始类
     */
    public static IMApp me() {
        return instance;
    }

    /**
     * 显示登录前的QQ图标
     *
     * @return
     */
    public WebWindow startWin() {
        if (startWin == null) {
            startWin = new WebWindow() {

            };
        }
        return null;
    }

    public static void main(String[] args) {
        LOG.info(System.getProperty("java.vm.name") + System.getProperty("java.version"));
        //      显示登录前的QQ图标
        IMApp.me().startWin();
    }
}
