package com.swpu.constructionsitesafety.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MybatisPlusGenerator {
	public static void main(String[] args) {
		FastAutoGenerator.create("jdbc:mysql://localhost:3306/site_safety?useSSL=false&serverTimezone=UTC&serverTimezone=CTT", "root", "123456")
				// 全局配置
				.globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")))
				// 包配置
				.packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
				// 策略配置
				.strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
						.entityBuilder()
						.enableLombok()
						.addTableFills(
								new Column("create_time", FieldFill.INSERT)
						)
						.build())
				// 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.templateEngine(new FreemarkerTemplateEngine())
				.execute();
	}

	// 处理 all 情况
	protected static List<String> getTables(String tables) {
		return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
	}
}
