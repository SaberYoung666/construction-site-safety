package com.swpu.constructionsitesafety.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author saber
 * @since 2024-12-21
 */
@Getter
@Setter
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 模块id
     */
    private Integer moduleId;
}
