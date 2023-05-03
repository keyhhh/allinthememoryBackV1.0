package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.mapper.UserMapper;
import com.cyh.allinthememory.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description userService的实现类
 * @date 2023/4/27 18:43
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
