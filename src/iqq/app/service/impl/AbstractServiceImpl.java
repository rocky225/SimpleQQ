package iqq.app.service.impl;

import iqq.app.core.IMContext;
import iqq.app.core.IMException;
import iqq.app.core.IMModule;

/**
 *  <br></br>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/20/13<br/>
 * Time: 3:18 PM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class AbstractServiceImpl implements IMModule {

    private IMContext context;

    @Override
    public void init(IMContext context) throws IMException {
        //To change body of implemented methods use File | Settings | File Templates.
        this.context = context;
    }

    @Override
    public void destroy() throws IMException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public IMContext getContext() {
        return context;
    }
}
