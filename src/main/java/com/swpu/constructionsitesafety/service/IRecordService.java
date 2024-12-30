package com.swpu.constructionsitesafety.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swpu.constructionsitesafety.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.swpu.constructionsitesafety.entity.User;
import com.swpu.constructionsitesafety.entity.vo.RecordVO;

import java.util.List;

/**
 * <p>
 * 考核记录表 服务类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface IRecordService extends IService<Record> {
    List<RecordVO> getAllRecord();

    List<RecordVO> getNameByRecord(String likeName);
}
