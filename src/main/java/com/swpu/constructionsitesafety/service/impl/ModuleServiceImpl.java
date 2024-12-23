package com.swpu.constructionsitesafety.service.impl;

import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.Module;
import com.swpu.constructionsitesafety.mapper.ModuleMapper;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.constructionsitesafety.utils.AccessLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 模块表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {
    @Autowired
    private ModuleMapper moduleMapper;
    private final AccessLimiter accessLimiter = new AccessLimiter();
    @Override
    public List<Module> lookViews() {
        List<Module> modules = moduleMapper.LookModules();
        return modules;
    }

    @Override
    public Integer StudyAddViews(Integer moduleId) {
        boolean result = accessLimiter.isAccessAllowed(BaseContext.getUserId());
        int result1 = 0;
        if (result) {
            accessLimiter.incrementAccess(BaseContext.getUserId());
            result1 = moduleMapper.addViews(moduleId);
        }
        return result1;
    }
}
