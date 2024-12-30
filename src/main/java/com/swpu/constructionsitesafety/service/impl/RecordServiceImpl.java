package com.swpu.constructionsitesafety.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swpu.constructionsitesafety.entity.Record;
import com.swpu.constructionsitesafety.entity.vo.RecordVO;
import com.swpu.constructionsitesafety.mapper.RecordMapper;
import com.swpu.constructionsitesafety.mapper.UserMapper;
import com.swpu.constructionsitesafety.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 考核记录表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public List<RecordVO> getAllRecord() {
        List<RecordVO> result = new ArrayList<>();
        result = recordMapper.getAllRecord();
        return result;
    }

    @Override
    public List<RecordVO> getNameByRecord(String likeName) {
        List<RecordVO> result = new ArrayList<>();
        result = recordMapper.getNameByRecord(likeName);
        return result;
    }
}
