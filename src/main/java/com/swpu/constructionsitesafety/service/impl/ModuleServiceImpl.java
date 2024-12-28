package com.swpu.constructionsitesafety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.Module;
import com.swpu.constructionsitesafety.mapper.ModuleMapper;
import com.swpu.constructionsitesafety.service.IModuleService;
import com.swpu.constructionsitesafety.utils.AccessLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	private final AccessLimiter accessLimiter = new AccessLimiter();
	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> lookViews() {
		List<Module> modules = moduleMapper.LookModules();
		return modules;
	}

	@Override
	public Integer StudyAddViews(Integer moduleId) {
		boolean result = accessLimiter.isAccessAllowed(BaseContext.getUserId(), moduleId);
		int result1 = 0;
		if (result) {
			accessLimiter.incrementAccess(BaseContext.getUserId(), moduleId);
			result1 = moduleMapper.addViews(moduleId);
		}
		return result1;
	}

	@Override
	public Integer studiedNum(Integer moduleId) {
		return moduleMapper.studiedNum(moduleId);
	}
}
