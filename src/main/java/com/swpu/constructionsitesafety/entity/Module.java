package com.swpu.constructionsitesafety.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 模块表
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Getter
@Setter
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模块名
     */
    private String name;

    /**
     * 访问量
     */
    private Integer views;
}
