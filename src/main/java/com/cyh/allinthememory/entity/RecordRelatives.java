package com.cyh.allinthememory.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author keyh
 * @description 亲属记录信息
 * @date 2023/5/9 19:25
 */
@Data
public class RecordRelatives  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long relativesId;

    private Long reRecordId;

    private String message;

    private String image;
}
