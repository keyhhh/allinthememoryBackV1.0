package com.cyh.allinthememory.service.impl;

import com.github.houbb.sensitive.word.api.IWordAllow;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author keyh
 * @description 白名单敏感词类
 * @date 2023/5/16 22:46
 */
@Service
public class CustomWordAllow implements IWordAllow {

    /**
     * 允许的内容-返回的内容不被当做敏感词
     * @return
     */
    @Override
    public List<String> allow() {
        // 从数据库中查询白名单敏感词
        return Arrays.asList("五星红旗");
    }

}
