package iqq.app.ui;

import com.alee.extended.painter.Painter;
import com.alee.laf.panel.WebPanel;
import iqq.app.core.IMContext;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/27/13<br/>
 * Time: 10:05 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface View {
    public void changeSkin(Painter<?> painter);
    public void setContentPanel(WebPanel container);
    public IMContext getContext();
}
