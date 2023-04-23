package com.cyh.allinthememory.controller;

import com.cyh.allinthememory.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 宇恒
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class HomeController {

    @GetMapping("default")
    public R<String> save(@RequestBody String addressBook) {
        log.info("访问成功！！！！！！！！！！！！");
        return R.success(addressBook);
    }
}
