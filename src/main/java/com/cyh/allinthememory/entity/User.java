package com.cyh.allinthememory.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 宇恒
 */
@Data
//@TableName(value = "") 让实体类对应数据库表名
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String MALE = "男";
    public static final String FEMALE = "女";


    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    private String userName;

    /**
     * 数据库中为枚举类型
     */
    private String gender;

    private String phone;

    private String email;

    private LocalDateTime birthday;

    /**头像*/
    private String avatar;

    /**用户状态，目前妹使用**/
    private Integer userStatus;

    /**紧急联系人*/
    private String urgentContact;

    /**同求的求助信息的id*/
    private String likedHelpId;

    /**点赞的公开记录*/
    private String likedRecordId;

    private String password;

    private String account;




}
