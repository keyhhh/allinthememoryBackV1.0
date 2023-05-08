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
import java.util.List;

/**
 * @author keyh
 * @description home界面的接口
 * @date 2023/5/3 13:44
 */
@RestController
@Slf4j
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordPublicService recordPublicService;

    @PostMapping("getrecord")
    public R<List<Record>> getRecord(@RequestBody User user, HttpServletRequest request){
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getUserId,user.getUserId());
        List<Record> record = recordService.list(queryWrapper);

        if (record == null) {
            return R.error("No data is queried");
        }

        return R.success(record);
    }

    @PostMapping("uprecord")
    public R<Record> upRecord(@RequestBody Record record, HttpServletRequest request){
        log.info("###########################"+record);
        recordService.save(record);
        if (record.getIsPublic() == 1){
            RecordPublic recordPublic = new RecordPublic();
            recordPublic.setUserId(record.getUserId());
            recordPublic.setRecordId(record.getRecordId());
            recordPublic.setIsMemory(record.getIsMemory());
            recordPublicService.save(recordPublic);
        }
        return R.success(record);
    }


    /**
     * @param record:
    	 * @param request:
      * @return R<String>
     * @author 宇恒
     * @description TODO 修改记录的公开和私密属性
     * @date 2023/5/8 18:05
     */
    @PostMapping("isopen")
    public R<String> isOpen(@RequestBody Record record, HttpServletRequest request){
        log.info("#$%^&*" + record);
        LambdaUpdateWrapper<Record> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Record::getRecordId, record.getRecordId());

        Record record1 = new Record();
        record1.setIsPublic(record.getIsPublic());

        recordService.update(record1,updateWrapper);
        return R.success("s");
    }

}
