package com.swpu.constructionsitesafety.controller;

import com.swpu.constructionsitesafety.entity.dto.AddViewsDTO;
import com.swpu.constructionsitesafety.service.IModuleService;
import com.swpu.constructionsitesafety.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 模块表 前端控制器
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@RestController
@RequestMapping("/module")
@Slf4j
public class ModuleController {
	@Autowired
	private IModuleService moduleService;

	@GetMapping("/lookViews")
	public ResultData lookViews() {
		return ResultData.success(moduleService.lookViews());
	}

	@PostMapping("/studyAddViews")
	public ResultData addViews(@RequestBody AddViewsDTO addViewsDTO) {
		moduleService.StudyAddViews(addViewsDTO.getId());
		return ResultData.success();
	}

	@GetMapping("/studiedNum")
	public ResultData<Integer> studiedNum(@RequestParam Integer moduleId) {
		log.info("moduleId:{}", moduleId);
		return ResultData.success(moduleService.studiedNum(moduleId));
	}
}
