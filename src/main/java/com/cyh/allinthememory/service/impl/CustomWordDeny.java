package com.cyh.allinthememory.service.impl;

/**
 * @author keyh
 * @description 黑名单敏感词类
 * @date 2023/5/16 22:47
 */

import com.github.houbb.sensitive.word.api.IWordDeny;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义敏感词
 * @author 宇恒
 */
@Service
public class CustomWordDeny implements IWordDeny {

    /**
     * 拒绝出现的数据-返回的内容被当做是敏感词
     *
     * @return
     */
    @Override
    public List<String> deny() {
        // 从数据库中查询自定义敏感词
        return Arrays.asList("五星红旗");
    }

}