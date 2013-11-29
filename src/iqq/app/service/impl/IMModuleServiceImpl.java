package iqq.app.service.impl;

import iqq.app.core.IMException;
import iqq.app.core.IMModule;
import iqq.app.service.IMModuleService;

import java.util.ArrayList;
import java.util.List;

/**
 * <b></b>
 * <br/><br/>
 * Created with IntelliJ IDEA.<br/>
 * User: ss<br/>
 * Date: 11/27/13<br/>
 * Time: 9:50 AM<br/>
 * To change this template use File | Settings | File Templates.
 */
public class IMModuleServiceImpl extends AbstractServiceImpl implements IMModuleService {

    private List<IMModule> modules;

    public IMModuleServiceImpl() {
        this.modules = new ArrayList<IMModule>();
    }

    /**
     * call IMEventHandlerProxy.register
     *
     * @param module implements AbstractModule
     * @throws IMException
     */
    @Override
    public void installModule(IMModule module) throws IMException {
        //  AbstractModule use IMEventHandlerProxy.register
        module.init(getContext());
        modules.add(module);
    }

    @Override
    public void removeModule(IMModule module) throws IMException {
        modules.remove(module);
        module.destroy();
    }

    @Override
    public List<IMModule> getModuleList() {
        return modules;
    }
}
