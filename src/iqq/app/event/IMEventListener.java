package iqq.app.event;

import iqq.app.core.IMException;

/**
 * <b>Event Listener</b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 4:27 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface IMEventListener {
    public void onIMEvent(IMEvent imEvent) throws IMException;
}
