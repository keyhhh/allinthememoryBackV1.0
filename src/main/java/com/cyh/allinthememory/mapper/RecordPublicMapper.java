package com.cyh.allinthememory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyh.allinthememory.entity.Record;
import com.cyh.allinthememory.entity.RecordPublic;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author keyh
 * @description 公共记录接口
 * @date 2023/5/8 15:08
 */
@Mapper
public interface RecordPublicMapper extends BaseMapper<RecordPublic> {
}
