package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.Help;
import com.cyh.allinthememory.entity.HelpDiscuss;
import com.cyh.allinthememory.service.HelpDiscussService;
import com.cyh.allinthememory.service.HelpService;
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
 * @description 求助信息
 * @date 2023/5/8 21:08
 */
@Slf4j
@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    private HelpService helpService;
    @Autowired
    private HelpDiscussService helpDiscussService;

    @PostMapping("/gethelp")
    public R<List<Help>> likeRecordPublic(@RequestBody Help help, HttpServletRequest request) {
        List<Help> helpList = helpService.list();

        return R.success(helpList);
    }

    @PostMapping("/gethelpbyid")
    public R<Help> getHelpByID(@RequestBody Help help, HttpServletRequest request) {
        LambdaQueryWrapper<Help>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Help::getHelpId, help.getHelpId());
        Help help1 = helpService.getOne(queryWrapper);

        return R.success(help1);
    }

    @PostMapping("/getdiscuss")
    public R<List<HelpDiscuss>> getDiscuss(@RequestBody Help help, HttpServletRequest request) {

        LambdaQueryWrapper<HelpDiscuss>  queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HelpDiscuss::getHelpId, help.getHelpId());
        List<HelpDiscuss> list = helpDiscussService.list(queryWrapper);

        return R.success(list);
    }
}
