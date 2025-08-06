package com.xiezhuolong.letterprocessor.strategy.impl;

/**
 * 字符替换策略实现类，用于将连续出现次数达标的字符替换为前一个字母。
 * <p>
 * 处理规则：
 * - 当连续字符长度≥{@link #MIN_CONTINUOUS_LENGTH}时：
 *   - 若字符为'a'，则替换为空字符串
 *   - 其他字符替换为字母表中前一个字母（如'b'→'a'，'c'→'b'）
 * - 当连续字符长度<{@link #MIN_CONTINUOUS_LENGTH}时，保留该片段
 * </p>
 * 采用单例模式实现，通过{@link #INSTANCE}获取实例。
 */
public class CharacterReplacementProcessor extends AbstractLetterProcessor {
    /**
     * 单例实例，全局唯一
     */
    public static final CharacterReplacementProcessor INSTANCE = new CharacterReplacementProcessor();

    /**
     * 私有构造器，防止外部创建实例，确保单例模式
     */
    private CharacterReplacementProcessor() {
    }

    /**
     * 处理连续字符片段：替换长片段为前一个字母，保留短片段。
     *
     * @param c      连续的字符
     * @param length 字符连续出现的长度
     * @return 替换后的字符（长度达标时）或原字符片段（长度不足时）
     */
    @Override
    protected String handleContinuousSegment(char c, int length) {
        if (length >= MIN_CONTINUOUS_LENGTH) {
            // 'a'替换为空，其他字符替换为前一个字母
            return c == 'a' ? "" : String.valueOf((char) (c - 1));

            // 题目给出的示例a替换为空。如果按26个字母循环的逻辑应该要返回z，则使用下面代码。
            // return c == 'a' ? "z" : String.valueOf((char) (c - 1));
        } else {
            return String.valueOf(c).repeat(length);
        }
    }
}
