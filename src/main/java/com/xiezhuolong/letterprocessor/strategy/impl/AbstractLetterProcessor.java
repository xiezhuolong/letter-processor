package com.xiezhuolong.letterprocessor.strategy.impl;

import com.xiezhuolong.letterprocessor.strategy.api.LetterProcessingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 字符处理策略的抽象基类，实现通用的字符串扫描逻辑。
 * <p>
 * 该类负责将输入字符串分割为连续相同字符的片段，
 * 并将每个片段的具体处理逻辑交给子类实现。同时提供了
 * 从配置文件加载最小连续长度阈值的功能。
 * </p>
 */
public abstract class AbstractLetterProcessor implements LetterProcessingStrategy {
    /**
     * 触发处理的最小连续字符长度，从配置文件加载，默认值为3
     */
    protected static final int MIN_CONTINUOUS_LENGTH;

    static {
        // 静态初始化块加载配置文件
        Properties properties = new Properties();
        try (InputStream is = AbstractLetterProcessor.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            System.err.println("警告：加载 application.properties 失败，使用默认配置。原因：" + e.getMessage());
        }
        // 解析配置值，默认为3
        MIN_CONTINUOUS_LENGTH = Integer.parseInt(
                properties.getProperty("letter.processor.min-continuous-length", "3")
        );
    } // 提取常量，避免硬编码，如果项目原本有用spring，可以使用spring简化

    /**
     * 执行单次字符串处理，将字符串分割为连续字符片段并逐个处理。
     * <p>
     * 处理流程：
     * 1. 扫描字符串，识别连续相同的字符片段
     * 2. 对每个片段调用{@link #handleContinuousSegment(char, int)}进行处理
     * 3. 拼接所有处理结果并返回
     * </p>
     *
     * @param s 输入字符串（仅包含小写字母a-z）
     * @return 单次处理后的字符串
     */
    @Override
    public String processOnce(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char currentChar = s.charAt(i);
            int j = i;
            // 计算当前字符的连续长度
            while (j < s.length() && s.charAt(j) == currentChar) {
                j++;
            }
            int continuousLength = j - i;
            // 交给子类实现具体处理逻辑
            sb.append(handleContinuousSegment(currentChar, continuousLength));
            i = j;
        }
        return sb.toString();
    }

    /**
     * 抽象方法：处理连续字符片段的具体逻辑。
     * <p>
     * 子类需实现此方法，定义当连续字符长度达到阈值时的处理方式
     * （如移除、替换等）。
     * </p>
     *
     * @param c      连续的字符
     * @param length 字符连续出现的长度
     * @return 处理后的字符片段
     */
    protected abstract String handleContinuousSegment(char c, int length);
}
