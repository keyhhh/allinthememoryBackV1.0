package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.Record;
import com.cyh.allinthememory.entity.RecordPublic;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.RecordPublicService;
import com.cyh.allinthememory.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author keyh
 * @description 公开记录的控制器
 * @date 2023/5/8 12:57
 */
@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private RecordPublicService recordPublicService;
    @Autowired
    private RecordService recordService;

    /**
     * @param request:
      * @return R<List<Record>>
     * @author 宇恒
     * @description TODO 返回公开记录
     * @date 2023/5/10 21:52
     */
    @PostMapping("/getrecordpublic")
    public R<List<Record>> getRecordPublic( HttpServletRequest request){
        //查询记录表中全部公开地记录
        LambdaQueryWrapper<Record> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Record::getIsPublic, 1);
        List<Record> recordList = recordService.list(queryWrapper1);
        return R.success(recordList);
    }

    /**
     * @param record:
    	 * @param request:
      * @return R<String>
     * @author 宇恒
     * @description TODO点赞公开记录信息，公开记录的
     * @date 2023/5/10 21:52
     */
    @PostMapping("/likerecordpublic")
    public R<String> likeRecordPublic(@RequestBody Record record, HttpServletRequest request){

        LambdaUpdateWrapper<Record> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Record::getRecordId, record.getRecordId());

        Record record1 = new Record();
        record1.setLikedUserId(record.getLikedUserId());

        recordService.update(record1,updateWrapper);

        return R.success("s");
    }
}
