package com.cyh.allinthememory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyh.allinthememory.entity.Record;
import com.cyh.allinthememory.entity.RecordPublic;
import com.cyh.allinthememory.mapper.RecordMapper;
import com.cyh.allinthememory.mapper.RecordPublicMapper;
import com.cyh.allinthememory.service.RecordPublicService;
import com.cyh.allinthememory.service.RecordService;
import org.springframework.stereotype.Service;

/**
 * @author keyh
 * @description 公共记录
 * @date 2023/5/8 15:10
 */
@Service
public class RecordPublicServiceImpl  extends ServiceImpl<RecordPublicMapper, RecordPublic> implements RecordPublicService {
}
