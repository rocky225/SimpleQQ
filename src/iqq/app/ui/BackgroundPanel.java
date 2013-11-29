package iqq.app.ui;

import com.alee.laf.panel.WebPanel;

import java.awt.*;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/27/13<br/>
 * Time: 10:08 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class BackgroundPanel extends WebPanel {

    private static final long serialVersionUID = -4326635468326074828L;

    private Window view;

    /**
     * @param application
     */
    public BackgroundPanel(Window view) {
        super();
        this.view = view;
    }

    /**
     * @return the frameView
     */
    public Window getView() {
        return view;
    }
}
