package com.swpu.constructionsitesafety.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetExamVO {
	private Integer examId;

	private List<QuestionVO> questions;
}
