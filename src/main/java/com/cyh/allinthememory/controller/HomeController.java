package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.Record;
import com.cyh.allinthememory.entity.User;
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

    @PostMapping("getrecord")
    public R<List<Record>> getRecord(@RequestBody User user, HttpServletRequest request){

        List<Record> record = recordService.list();

        if (record == null) {
            return R.error("No data is queried");
        }

        return R.success(record);
    }
}
