package com.cyh.allinthememory.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author keyh
 * @description 用户亲属关系表
 * @date 2023/5/9 19:21
 */
@Data
public class UserRelatives implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long relativesId;

    private String name;

    private LocalDateTime birthday;

    private String gender;

    private String called;

}
