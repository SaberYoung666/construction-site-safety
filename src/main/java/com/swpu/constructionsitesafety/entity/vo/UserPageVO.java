package com.swpu.constructionsitesafety.entity.vo;

import com.swpu.constructionsitesafety.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPageVO {
	private Long pages;
	private List<User> users;
}
