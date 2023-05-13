package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 宇恒
 */
@Data
public class Capsule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long capsuleId;

    private Long userId;

    private String title;

    private String message;

    /**胶囊创建时间*/
    private LocalDate  dateCreate;

    /**胶囊发送时间*/
    private LocalDate dateSend;

    /**胶囊发送形式 0手机  1邮件 */
    private Integer typeSend;

    /**是否为还未发送不可以查看 0可查看  1不可查看 */
    private Boolean isLocked;
}
