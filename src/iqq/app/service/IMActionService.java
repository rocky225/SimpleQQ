package iqq.app.service;

import iqq.app.core.IMService;
import iqq.app.ui.action.IMActionMap;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/27/13<br/>
 * Time: 10:25 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public interface IMActionService extends IMService {
    public IMActionMap getActionMap(Object actionsObject);

    public IMActionMap getActionMap(Class<? extends Object> actionsClass,
                                    Object actionsObject);
}
