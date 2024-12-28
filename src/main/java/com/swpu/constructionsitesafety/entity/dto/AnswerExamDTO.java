package com.swpu.constructionsitesafety.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerExamDTO {
	private Integer examId;

	private List<AnswerQuestionDTO> answers;
}
