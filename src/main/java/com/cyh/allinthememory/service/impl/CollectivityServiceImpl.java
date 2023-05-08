package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.Capsule;
import com.cyh.allinthememory.entity.Collectivity;
import com.cyh.allinthememory.mapper.CapsuleMapper;
import com.cyh.allinthememory.mapper.CollectivityMapper;
import com.cyh.allinthememory.service.CapsuleService;
import com.cyh.allinthememory.service.CollectivityService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 集体
 * @date 2023/5/8 22:55
 */
@Service
public class CollectivityServiceImpl  extends ServiceImpl<CollectivityMapper, Collectivity> implements CollectivityService {
}
