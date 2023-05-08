package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.Help;
import com.cyh.allinthememory.entity.HelpDiscuss;
import com.cyh.allinthememory.mapper.HelpDiscussMapper;
import com.cyh.allinthememory.mapper.HelpMapper;
import com.cyh.allinthememory.service.HelpDiscussService;
import com.cyh.allinthememory.service.HelpService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 评论
 * @date 2023/5/8 22:51
 */
@Service
public class HelpDiscussServiceImpl   extends ServiceImpl<HelpDiscussMapper, HelpDiscuss> implements HelpDiscussService {
}
