package com.cyh.allinthememory.config;

import com.cyh.allinthememory.service.impl.CustomWordAllow;
import com.cyh.allinthememory.service.impl.CustomWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author keyh
 * @description 敏感词检测类
 * @date 2023/5/16 22:49
 */
@Configuration
public class SensitiveWordConfig {

    @Autowired
    private CustomWordAllow customWordAllow;

    @Autowired
    private CustomWordDeny customWordDeny;

    /**
     * 初始化引导类
     *
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        // 可根据数据库数据判断 动态增加配置
        return SensitiveWordBs.newInstance()
                // 设置黑名单
                .wordDeny(WordDenys.chains(WordDenys.system(),customWordDeny))
                // 设置白名单
                .wordAllow(WordAllows.chains(WordAllows.system(), customWordAllow))
                .ignoreCase(true)
                .ignoreWidth(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreRepeat(true)
                .enableEmailCheck(true)
                .enableUrlCheck(true)
                // 各种其他配置
                .init();
    }

}
