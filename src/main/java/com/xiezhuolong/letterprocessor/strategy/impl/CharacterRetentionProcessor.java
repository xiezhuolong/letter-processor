package com.xiezhuolong.letterprocessor.strategy.impl;

/**
 * 字符保留策略实现类，用于移除连续出现次数达到阈值的字符。
 * <p>
 * 处理规则：
 * - 当连续字符长度≥{@link #MIN_CONTINUOUS_LENGTH}时，移除该片段
 * - 当连续字符长度<{@link #MIN_CONTINUOUS_LENGTH}时，保留该片段
 * </p>
 * 采用单例模式实现，通过{@link #INSTANCE}获取实例。
 */
public class CharacterRetentionProcessor extends AbstractLetterProcessor {
    /**
     * 单例实例，全局唯一
     */
    public static final CharacterRetentionProcessor INSTANCE = new CharacterRetentionProcessor();

    /**
     * 私有构造器，防止外部创建实例，确保单例模式
     */
    private CharacterRetentionProcessor() {
    }


    /**
     * 处理连续字符片段：保留短片段，移除长片段。
     *
     * @param c      连续的字符
     * @param length 字符连续出现的长度
     * @return 保留的字符片段（长度不足阈值时）或空字符串（长度达标时）
     */
    @Override
    protected String handleContinuousSegment(char c, int length) {
        // 连续长度<阈值则保留，否则移除
        return length < MIN_CONTINUOUS_LENGTH ?
                String.valueOf(c).repeat(length) : "";
    }
}
