package com.swpu.constructionsitesafety.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swpu.constructionsitesafety.context.BaseContext;
import com.swpu.constructionsitesafety.entity.Record;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.vo.RecordVO;
import com.swpu.constructionsitesafety.service.IRecordService;
import com.swpu.constructionsitesafety.service.IUserService;
import com.swpu.constructionsitesafety.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.swpu.constructionsitesafety.utils.ReturnCode.RC403;

/**
 * <p>
 * 考核记录表 前端控制器
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@RestController
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private IRecordService recordService;

	@Autowired
	private IUserService userService;

	@GetMapping("/getPoints")
	public ResultData<List<Record>> getPoints() {
		QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", BaseContext.getUserId());
		List<Record> records = recordService.list(queryWrapper);
		return ResultData.success(records);
	}
	@GetMapping("/getAllRecord")
	public ResultData<List<RecordVO>> getAllRecord(){
		User user = userService.getById(BaseContext.getUserId());
		if(user.getAuthority() == 1){
			return ResultData.success(recordService.getAllRecord());
		}
		return ResultData.fail(RC403.getCode(), RC403.getMessage());
	}
}
