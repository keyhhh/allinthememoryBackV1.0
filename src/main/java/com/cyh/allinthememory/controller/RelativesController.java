package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.RecordRelatives;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.entity.UserRelatives;
import com.cyh.allinthememory.service.RecordRelativesService;
import com.cyh.allinthememory.service.UserRelativesService;
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
 * @description 亲属控制器
 * @date 2023/5/9 19:40
 */
@Slf4j
@RestController
@RequestMapping("/relatives")
public class RelativesController {
    @Autowired
    private RecordRelativesService recordRelativesService;

    @Autowired
    private UserRelativesService userRelativesService;


    //获取该用户亲属的标签
    @PostMapping("getre")
    public R<List<UserRelatives>> getRe(@RequestBody  User user, HttpServletRequest request) {
        LambdaQueryWrapper<UserRelatives> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRelatives::getUserId, user.getUserId());
        List<UserRelatives> userRelativesList = userRelativesService.list(queryWrapper);
        return R.success(userRelativesList);
//        return R.error("/获取该用户亲属的标签");
    }

    //获取亲属的记录
    @PostMapping("getrerecord")
    public R<RecordRelatives> getReRecord(@RequestBody User user, HttpServletRequest request) {


//        return R.success(recordRelatives);
        return R.error("/获取亲属的记录");
    }

//    upReRecord
//    //上传亲属的记录
//    @PostMapping("uprerecord")
//    public R<RecordRelatives> upReRecord(@RequestBody User user, HttpServletRequest request) {
//
//
////        return R.success(recordRelatives);
//        return R.error("/获取亲属的记录");
//    }
}
