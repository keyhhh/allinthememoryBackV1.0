package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.Capsule;
import com.cyh.allinthememory.entity.HelpDiscuss;
import com.cyh.allinthememory.mapper.CapsuleMapper;
import com.cyh.allinthememory.mapper.HelpDiscussMapper;
import com.cyh.allinthememory.service.CapsuleService;
import com.cyh.allinthememory.service.HelpDiscussService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 胶囊
 * @date 2023/5/8 22:54
 */
@Service
public class CapsuleServiceImpl  extends ServiceImpl<CapsuleMapper, Capsule> implements CapsuleService {
}
