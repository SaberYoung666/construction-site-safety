package com.swpu.constructionsitesafety;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.swpu.constructionsitesafety.mapper")
public class ConstructionSiteSafetyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstructionSiteSafetyApplication.class, args);
	}

}
