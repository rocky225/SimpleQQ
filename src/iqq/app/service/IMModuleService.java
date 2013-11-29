package iqq.app.service;

import iqq.app.core.IMException;
import iqq.app.core.IMModule;
import iqq.app.core.IMService;

import java.util.List;

/**
 * 模块服务，提供模块的动态安装和启动
 */
public interface IMModuleService extends IMService {
    public void installModule(IMModule module) throws IMException;

    public void removeModule(IMModule module) throws IMException;

    public List<IMModule> getModuleList();
}
