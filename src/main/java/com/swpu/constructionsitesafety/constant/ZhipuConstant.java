package com.swpu.constructionsitesafety.constant;

/**
 * 智谱AI常量类
 */
public class ZhipuConstant {
	public static final String URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
	public static final String API_KEY = "ef5d8f21f982d3f2f5274385846963fc.KyVhXdexLcNtgjFD";
	public static final String KNOWLEDGE_BASE_ID = "1870723543998857216";
	public static final String SET_QUESTIONS = "请针对%s模块生成10道问答题，不需要答案，只需要问题，注意生成的一定是问答题，一定要是问句";
	public static final String ANSWER_QUESTIONS = "我对问题：'%s'的回答是：'%s'。默认问题是正确的，不要判断问题的对错，请判断我的回答是否是正确答案，并根据文档给出简要的解析，格式应当形如：'对（或错）,解析：'";
	public static final String MODEL = "glm-4-plus";
	public static final String SET_QUESTIONS_TEMPLATE = "从文档\n\"\"\"\n{{knowledge}}\n\"\"\"\n中解决问题\n\"\"\"\n{{question}}\n\"\"\"\n，只能根据文档作答\n回答时的格式应当形如：'{答案1}{答案2}...'，回答时应当严格遵循格式，不能包括序号，问题之间不能包括回车，不能包含'\n'\n不要复述问题，直接开始回答。";
	public static final String ANSWER_QUESTIONS_TEMPLATE = "从文档\n\"\"\"\n{{knowledge}}\n\"\"\"\n中找问题\n\"\"\"\n{{question}}\n\"\"\"\n的答案，如果找不到答案就用自身知识回答，如果回答不完全也需要判为错误，如果回答内容属于无语义的内容或无关内容需要判为错误，如果回答'不知道'则需要判为错误并给出提示，如果回答相对于文档是正确的则判为对，如果回答相对于文档是错误的则判为错\n不要复述问题，直接开始回答。";
}
