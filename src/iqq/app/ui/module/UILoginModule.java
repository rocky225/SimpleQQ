package iqq.app.ui.module;

import iqq.app.core.IMContext;
import iqq.app.core.IMException;
import iqq.app.event.IMEvent;
import iqq.app.event.IMEventHandler;
import iqq.app.event.IMEventHandlerProxy;
import iqq.app.event.IMEventType;
import iqq.app.ui.IMFrameView;

/**
 * <b>登录界面模块,负责界面的显示和UI事件的处理</b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/27/13<br/>
 * Time: 10:00 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class UILoginModule extends IMFrameView {

    private LoginPanel loginPanel;
    private boolean isVerifyCode = false;

    @Override
    public void init(IMContext context) throws IMException {
        super.init(context);

        // 初始化窗口
        initFrame();

        // 初始化显示内容，登录面板
        loginPanel = new LoginPanel(this);
        setContentPanel(loginPanel);

        // 初始化用户登录信息
        initAccount();

        // 注册感兴趣的事件
        IMEventHandlerProxy.register(this, context);
    }

    private void initFrame() {
        // 设置标题
        setTitle(I18NUtil.getMessage("app.name"));
        setIconImage(SkinUtils.getImageIcon("appLogo").getImage());

        // 设置程序宽度 和高度
        setSize(SettingUtils.getInt("appWidth"), SettingUtils.getInt("appHeight"));

        // 设置在屏幕显示位置，右中
        setLocation(LocationUtil.getScreenRight(getWidth(), getHeight()));
        setAlwaysOnTop(true);

        // 关闭后退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @IMEventHandler(IMEventType.LOGIN_READY)
    protected void processIMLoginReady(IMEvent event) {
//        LOG.info("Ready to login......");
        this.show();
        broadcastIMEvent(new IMEvent(IMEventType.RECENT_ACCOUNT_FIND));
    }
}
