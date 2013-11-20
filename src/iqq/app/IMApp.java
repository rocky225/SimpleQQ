package iqq.app;

import com.alee.laf.rootpane.WebWindow;
import iqq.app.core.IMService;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ss
 * Date: 11/19/13
 * Time: 2:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class IMApp {

    //    private static final Logger LOG = Logger.getLogger(IMApp.class);
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
