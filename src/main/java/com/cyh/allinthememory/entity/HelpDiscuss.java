package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author keyh
 * @description 评论内容
 * @date 2023/5/8 22:47
 */
@Data
public class HelpDiscuss implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long helpId;

    private Long userId;

    private LocalDateTime datePublish;

    private String message;
}
