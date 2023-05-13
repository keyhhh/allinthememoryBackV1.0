package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.entity.UserRelatives;
import com.cyh.allinthememory.mapper.UserMapper;
import com.cyh.allinthememory.mapper.UserRelativesMapper;
import com.cyh.allinthememory.service.UserRelativesService;
import com.cyh.allinthememory.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 用户亲属关系
 * @date 2023/5/9 19:37
 */
@Service
public class UserRelativesServiceImpl  extends ServiceImpl<UserRelativesMapper, UserRelatives> implements UserRelativesService {
}
