package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.Help;
import com.cyh.allinthememory.entity.RecordPublic;
import com.cyh.allinthememory.mapper.HelpMapper;
import com.cyh.allinthememory.mapper.RecordPublicMapper;
import com.cyh.allinthememory.service.HelpService;
import com.cyh.allinthememory.service.RecordPublicService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 求助信息
 * @date 2023/5/8 21:07
 */
@Service
public class HelpServiceImpl  extends ServiceImpl<HelpMapper, Help> implements HelpService {
}
