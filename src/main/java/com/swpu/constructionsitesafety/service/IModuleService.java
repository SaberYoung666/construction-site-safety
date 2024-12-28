package com.swpu.constructionsitesafety.service;

import com.swpu.constructionsitesafety.entity.Module;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 模块表 服务类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface IModuleService extends IService<Module> {
    List<Module> lookViews();

    Integer StudyAddViews(Integer moduleId);

    Integer studiedNum(Integer moduleId);
}
