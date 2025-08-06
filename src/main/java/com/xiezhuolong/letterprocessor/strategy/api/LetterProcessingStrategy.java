package com.xiezhuolong.letterprocessor.strategy.api;

/**
 * 字符串处理策略接口，定义字符处理的标准行为。
 * <p>
 * 所有字符处理策略（如移除连续字符、替换连续字符）都需实现此接口，
 * 提供单次处理的具体逻辑。
 * </p>
 */
public interface LetterProcessingStrategy {
    /**
     * 对输入字符串执行单次处理操作。
     * <p>
     * 单次处理指对字符串进行一次扫描并处理符合条件的字符片段，
     * 可能需要多次调用才能达到最终稳定状态。
     * </p>
     *
     * @param s 输入字符串（仅包含小写字母a-z）
     * @return 单次处理后的字符串
     * @throws IllegalArgumentException 如果输入字符串包含非小写字母字符
     */
    String processOnce(String s);
}
