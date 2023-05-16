package com.cyh.allinthememory.config;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplaceContext;

/**
 * @author keyh
 * @description 自定义更换敏感词策略，使用方法SensitiveWordHelper.replace(text, new MySensitiveWordReplace())
 * @date 2023/5/16 22:58
 */
public class MySensitiveWordReplace implements ISensitiveWordReplace {

    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        String sensitiveWord = context.sensitiveWord();
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if ("五星红旗".equals(sensitiveWord)) {
            return "旗帜";
        }

        if ("天安门".equals(sensitiveWord)) {
            return "门";
        }

        if ("毛主席".equals(sensitiveWord)) {
            return "教员";
        }
        // 其他默认使用 * 代替
        int wordLength = context.wordLength();
        return CharUtil.repeat('*', wordLength);
    }

}