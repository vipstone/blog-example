package com.example;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * URL 去重
 */
public class URLRepeat {
    // 待去重 URL
    public static final String[] URLS = {
            "www.apigo.cn",
            "www.baidu.com",
            "www.apigo.cn"
    };

    public static void main(String[] args) {
//        setRepeat();

        // 创建一个布隆过滤器
        BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                10, // 期望处理的元素数量
                0.01); // 期望的误报概率

        for (int i = 0; i < URLS.length; i++) {
            String url = URLS[i];
            if (filter.mightContain(url)) {
                // 用重复的 URL
                System.out.println("URL 已存在了：" + url);
            } else {
                // 将 URL 存储在布隆过滤器中
                filter.put(url);
            }
        }
    }

    /**
     * 使用 Set 集合实现 URL 去重
     */
    private static void setRepeat() {
        Set<String> set = new HashSet();
        for (int i = 0; i < URLS.length; i++) {
            String url = URLS[i];
            boolean result = set.add(url);
            if (!result) {
                // 用重复的 URL
                System.out.println("URL 已存在了：" + url);
            }
        }
    }
}
