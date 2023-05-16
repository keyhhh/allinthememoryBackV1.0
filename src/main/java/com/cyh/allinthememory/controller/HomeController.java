package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.Record;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.RecordService;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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
    private SensitiveWordBs sensitiveWordBs;


    /**
     * @param user:
     * @param request:
     * @return R<List < Record>>
     * @author 宇恒
     * @description TODO 获取全部的记录
     * @date 2023/5/12 22:59
     */
    @PostMapping("getrecord")
    public R<List<Record>> getRecord(@RequestBody User user, HttpServletRequest request) {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getUserId, user.getUserId());
        List<Record> recordList = recordService.list(queryWrapper);

        if (recordList == null) {
            return R.error("No data is queried");
        }
        return R.success(recordList);
    }

    @PostMapping("getrecordbydate")
    public R<List<Record>> getRecordByDate(@RequestBody Record record, HttpServletRequest request) {
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getUserId, record.getUserId()).eq(Record::getDatePublish, record.getDatePublish());
        List<Record> recordList = recordService.list(queryWrapper);
        if (recordList == null) {
            return R.error("No data is queried");
        }
        return R.success(recordList);
    }

    @PostMapping("getrecordbytag")
    public R<List<Record>> getRecordByTag(@RequestBody Record record, HttpServletRequest request) {
        List<Record> recordListAll = recordService.list();
        List<Record> recordListByTag = new ArrayList<>();
        for (Record record1 : recordListAll) {
            String[] recordAllTag = record1.getTag().split(",");
            List<String> recordAllTagList = Arrays.asList(recordAllTag);
            if (recordAllTagList.contains(record.getTag())) {
                recordListByTag.add(record1);
            }
        }
        return R.success(recordListByTag);
    }


    /**
     * @param record:
     * @param request:
     * @return R<Record>
     * @author 宇恒
     * @description TODO 上传记录
     * @date 2023/5/12 22:59
     */
    @PostMapping("uprecord")
    public R<Record> upRecord(@RequestBody Record record, HttpServletRequest request) throws Exception {
        boolean contains = sensitiveWordBs.contains(record.getMessage());
        boolean contains1 = sensitiveWordBs.contains(record.getTag());
        if (contains || contains1) {
            //存在敏感词
            if (record.getIsPublic() == 1) {
                //还公开，就不给提交
                return R.error("提交失败，文章包括敏感词，无法设置公开！");
            }
        }
        boolean save = recordService.save(record);
        if (save) {
            return R.success(record);
        } else {
            return R.error("提交失败");
        }

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
    public R<String> isOpen(@RequestBody Record record, HttpServletRequest request) {
        Record serviceById = recordService.getById(record.getRecordId());
        //是否包含敏感词
        boolean contains = sensitiveWordBs.contains(serviceById.getMessage());
        if (contains) {
            List<String> sensitiveWordAll = sensitiveWordBs.findAll(serviceById.getMessage());
            return R.sensitiveWordError(String.join(",", sensitiveWordAll));
        } else {
            LambdaUpdateWrapper<Record> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Record::getRecordId, record.getRecordId());

            Record record1 = new Record();
            record1.setIsPublic(record.getIsPublic());

            recordService.update(record1, updateWrapper);
            return R.success("开启成功");
        }
    }

    /**
     * @param record:
     * @param request:
     * @return R<String>
     * @author 宇恒
     * @description TODO 删除记录，就传过来一个记录的id
     * @date 2023/5/12 22:58
     */
    @PostMapping("derecord")
    public R<String> deRecord(@RequestBody Record record, HttpServletRequest request) {
        //删除记录表中信息
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getRecordId, record.getRecordId());
        boolean remove = recordService.remove(queryWrapper);
        if (remove) {
//            //删除公开表中的记录，但是我的公开记录表没有用到，要展示公开记录时直接遍历 isPublic 属性为1的数据
//            LambdaQueryWrapper<RecordPublic> queryWrapper1 = new LambdaQueryWrapper<>();
//            queryWrapper1.eq(RecordPublic::getRecordId, record.getRecordId());
//            boolean removePublic = recordPublicService.remove(queryWrapper1);
//            if (removePublic) {
//                return R.success("删除成功");
//            } else {
//                return R.error("删除失败");
//            }
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

}
