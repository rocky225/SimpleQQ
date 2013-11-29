package iqq.app.ui;

import com.alee.extended.painter.Painter;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import iqq.app.core.IMContext;
import iqq.app.core.IMException;
import iqq.app.core.IMModule;
import iqq.app.core.IMService;
import iqq.app.event.IMEvent;
import iqq.app.event.IMEventType;
import iqq.app.service.IMActionService;
import iqq.app.service.IMEventService;
import iqq.app.ui.widget.IMTitleComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>一个Frame模板的基本实现</b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/27/13<br/>
 * Time: 10:01 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMFrameView extends WebFrame implements IMModule, View {

    private static final long serialVersionUID = 4865581108740165663L;
    private int defaultCloseOperation = JFrame.HIDE_ON_CLOSE;
    private Map<String, Object> values = new HashMap<String, Object>();
    private IMContext context;

    private IMTitleComponent titleComponent;

    public IMFrameView() {
    }

    @Override
    public void init(IMContext context) throws IMException {
        this.context = context;

        // 设置window的关闭按钮功能
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // 关闭当前窗口
                super.windowClosing(e);
                // 调用broadcast通知后台关闭程序
                if (getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE) {
                    IMEventService events = getContext().getSerivce(IMService.Type.EVENT);
                    events.broadcast(new IMEvent(IMEventType.APP_EXIT_READY));
                }
            }
        });
    }

    @Override
    public void setDefaultCloseOperation(int operation) {
        this.defaultCloseOperation = operation;
    }

    @Override
    public int getDefaultCloseOperation() {
        return this.defaultCloseOperation;
    }

    @Override
    public void destroy() throws IMException {
        dispose();
    }

    public IMTitleComponent getIMTitleComponent() {
        if (this.titleComponent == null) {
            this.titleComponent = new IMTitleComponent(this);
        }
        return this.titleComponent;
    }

    public void setIMTitleComponent(IMTitleComponent titleComponent) {
        this.titleComponent = titleComponent;
    }

    public IMActionService getActionService() {
        return getContext().getSerivce(IMService.Type.ACTION);
    }

    @Override
    public void setIconImage(Image image) {
//        super.setIconImage(image);
//        firePropertyChange("appLogo", null, image);
    }

    protected void broadcastIMEvent(IMEvent event) {
        IMEventService eventHub = context.getSerivce(IMService.Type.EVENT);
        eventHub.broadcast(event);
    }

    protected void broadcastIMEvent(IMEventType type, Object target) {
        IMEventService eventHub = context.getSerivce(IMService.Type.EVENT);
        eventHub.broadcast(new IMEvent(type, target));
    }

    @Override
    public void changeSkin(Painter<?> painter) {
        WebPanel mainPl = (WebPanel) getContentPane();
        mainPl.setPainter(painter);
    }

    @Override
    public void setContentPanel(WebPanel container) {
        setContentPane(container);
//        changeSkin(SkinUtils.getPainter(IMSkinService.Type.NPICON, "appBg"));
    }

    @Override
    public IMContext getContext() {
        return context;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void show() {
        super.show();
//        super.setVisible(true);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    public String getString(String key) {
        return (String) values.get(key);
    }

    public void addValue(String key, Object value) {
        values.put(key, value);
    }

    public void addString(String key, String value) {
        values.put(key, value);
    }
}
