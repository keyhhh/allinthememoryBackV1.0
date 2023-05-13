package com.cyh.allinthememory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyh.allinthememory.entity.RecordRelatives;
import com.cyh.allinthememory.entity.UserRelatives;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author keyh
 * @description 用户亲属的记录信息
 * @date 2023/5/9 19:35
 */
@Mapper
public interface RecordRelativesMapper  extends BaseMapper<RecordRelatives> {
}
