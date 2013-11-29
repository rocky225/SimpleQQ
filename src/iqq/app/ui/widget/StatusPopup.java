package iqq.app.ui.widget;

import com.alee.extended.panel.GroupPanel;
import com.alee.laf.button.WebButton;
import com.alee.laf.menu.WebMenuItem;
import com.alee.managers.popup.PopupStyle;
import com.alee.managers.popup.PopupWay;
import com.alee.managers.popup.WebButtonPopup;
import iqq.app.service.IMSkinService;
import iqq.app.util.SkinUtils;
import iqq.im.bean.QQStatus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 9:32 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class StatusPopup extends WebButton implements ActionListener {

    private WebButtonPopup popup;
    private GroupPanel gp;
    private QQStatus status = QQStatus.OFFLINE;
    private Map<QQStatus, WebMenuItem> statusMap;
    private StatusChangeListner statusListener;

    public StatusPopup() {
        super();
        this.setPainter(SkinUtils.getPainter(IMSkinService.Type.NPICON, "transparent"));

        popup = new WebButtonPopup(this, PopupWay.downRight);
        popup.setPopupStyle(PopupStyle.lightSmall);    // QQ状态样式
        gp = new GroupPanel(false);
//        setCurrentStatus(QQStatus.ONLINE);
//        initPopupList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public interface StatusChangeListner {
        public void statusChanged(QQStatus newStatus, QQStatus oldStatus);
    }
}
