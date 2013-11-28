package iqq.app.ui.widget;

import iqq.app.ui.BackgroundPanel;

import java.awt.*;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/27/13<br/>
 * Time: 10:07 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMTitleComponent extends BackgroundPanel {

    private static final long serialVersionUID = -4326635468326074828L;

    private Window view;

    /**
     * @param application
     */
    public IMTitleComponent(Window view) {
        super(view);
        this.view = view;
    }

    public Window getView() {
        return view;
    }
}
