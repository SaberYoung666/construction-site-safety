package com.swpu.constructionsitesafety.service.impl;

import com.swpu.constructionsitesafety.entity.Question;
import com.swpu.constructionsitesafety.mapper.QuestionMapper;
import com.swpu.constructionsitesafety.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 题目表 服务实现类
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
