package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.RecordRelatives;
import com.cyh.allinthememory.entity.UserRelatives;
import com.cyh.allinthememory.mapper.RecordRelativesMapper;
import com.cyh.allinthememory.mapper.UserRelativesMapper;
import com.cyh.allinthememory.service.RecordRelativesService;
import com.cyh.allinthememory.service.UserRelativesService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 用户亲属记录
 * @date 2023/5/9 19:38
 */
@Service
public class RecordRelativesServiceImpl extends ServiceImpl<RecordRelativesMapper, RecordRelatives> implements RecordRelativesService {
}
