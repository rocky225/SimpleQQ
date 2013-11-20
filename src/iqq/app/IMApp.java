package iqq.app;

import com.alee.laf.rootpane.WebWindow;
import iqq.app.core.IMContext;
import iqq.app.core.IMService;
import iqq.app.event.IMEvent;
import iqq.app.event.IMEventType;
import iqq.app.service.IMEventService;
import iqq.app.service.impl.IMEventServiceImpl;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ss
 * Date: 11/19/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class IMApp implements IMContext {

    //    private static final Logger LOG = Logger.getLogger(IMApp.class);
    private static final IMApp instance = new IMApp();
    private WebWindow startWin;
    private Map<IMService.Type, IMServiceEntry> services;
    private boolean appExiting;

    /**
     * 初始化所有的IMServiceEntry服务
     */
    private IMApp() {
        this.services = new HashMap<IMService.Type, IMServiceEntry>();
        this.services.put(IMService.Type.EVENT, new IMServiceEntry(new IMEventServiceImpl(), 7));
        this.appExiting = false;
    }

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
                @Override
                public void paint(Graphics g) {
                    g.drawImage(doubleBufferedDraw(), 0, 0, null);
                    // 处置
                    g.dispose();
                }

                // 使用一个图片创建一个Image
                public Image doubleBufferedDraw() {
                    Image image = this.createImage(this.getWidth(), this.getHeight());
                    Graphics2D g2d = (Graphics2D) image.getGraphics();
                    g2d.drawImage(new ImageIcon("res/images/logo128X128.png").getImage(), 0, 0, this);
                    g2d.dispose();
                    return image;
                }
            };
        }
        startWin.setBackground(Color.white);
        startWin.setSize(128, 128);
        startWin.setLocationRelativeTo(null);
        startWin.setAlwaysOnTop(true);
        startWin.setVisible(true);
        startWin.repaint();
        return startWin;
    }

    public void startup() {
        List<IMServiceEntry> serviceList = new ArrayList<IMServiceEntry>();
        serviceList.addAll(services.values());
        Collections.sort(serviceList);
        //按优先级从高到低排序，优先级高的服务优先初始化，数字越小优先级越高
        for (IMServiceEntry entry : serviceList) {
            try {
//                long start = System.currentTimeMillis();
                // 把this赋值给context
                entry.service.init(this);
//                long end = System.currentTimeMillis();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        IMEventService eventHub = (IMEventService) getSerivce(IMService.Type.EVENT);
        eventHub.broadcast(new IMEvent(IMEventType.LOGIN_READY));
    }

    public static void main(String[] args) {
//        LOG.info(System.getProperty("java.vm.name") + System.getProperty("java.version"));
        // 显示登录前的QQ图标 LOGO
        IMApp.me().startWin();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IMApp.me().startup();
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IMService> T getSerivce(IMService.Type type) {
        return (T) services.get(type).service;
    }

    private static class IMServiceEntry implements Comparable<IMServiceEntry> {
        public IMService service;
        public int priority;

        public IMServiceEntry(IMService service, int priority) {
            this.service = service;
            this.priority = priority;
        }

        @Override
        public int compareTo(IMServiceEntry o) {
            return this.priority - o.priority;
        }
    }
}
