package iqq.app.ui.content.login;

import com.alee.extended.image.WebImage;
import com.alee.laf.label.WebLabel;
import com.alee.managers.tooltip.WebCustomTooltip;
import iqq.app.ui.BackgroundPanel;
import iqq.app.ui.IMFrameView;

import java.awt.*;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: Rocky<br/>
 * Date: 11/29/13<br/>
 * Time: 9:24 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class LoginPanel extends BackgroundPanel {

    private IMFrameView view;
    private WebImage faceImage;
    private WebLabel regAccountLbl;
    private WebLabel forgetPwdLbl;
    private WebLabel settingLbl;
    private WebCustomTooltip errTooltip;
    private HideTooltipTask errHideTask;
    private Color color = Color.GRAY;
    private Color enteredColor = Color.RED;

    /**
     * @param application
     */
    public LoginPanel(IMFrameView view) {
        super(view);
        this.view = view;
        this.errHideTask = new HideTooltipTask();
    }

    private class HideTooltipTask implements Runnable {
        @Override
        public void run() {
            if (errTooltip != null) {
                errTooltip.setVisible(false);
                errTooltip = null;
            }
        }

    }
}
