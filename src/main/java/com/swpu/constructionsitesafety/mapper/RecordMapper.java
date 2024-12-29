package com.swpu.constructionsitesafety.mapper;

import com.swpu.constructionsitesafety.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swpu.constructionsitesafety.entity.vo.RecordVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 考核记录表 Mapper 接口
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
public interface RecordMapper extends BaseMapper<Record> {
    @Select("SELECT name, score, time FROM record JOIN site_safety.user u on u.id = record.user_id;")
    List<RecordVO> getAllRecord();
}
