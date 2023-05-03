package com.cyh.allinthememory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyh.allinthememory.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author keyh
 * @description user的映射
 * @date 2023/4/27 18:44
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
