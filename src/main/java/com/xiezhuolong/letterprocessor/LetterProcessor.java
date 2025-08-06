package com.xiezhuolong.letterprocessor;

import com.xiezhuolong.letterprocessor.strategy.api.LetterProcessingStrategy;

/**
 * 字符串处理器，使用策略模式封装字符处理逻辑。
 * <p>
 * 该类通过传入不同的{@link LetterProcessingStrategy}实现，
 * 支持多种字符处理规则。核心功能是对输入字符串进行连续处理，
 * 直到字符串不再变化为止。
 * </p>
 */
public class LetterProcessor {
    /**
     * 字符处理策略实例，决定具体的处理逻辑
     */
    private LetterProcessingStrategy processorStrategy; // 字符串处理策略

    /**
     * 构造函数，初始化处理器并指定处理策略。
     *
     * @param processorStrategy 字符处理策略实现
     */
    public LetterProcessor(LetterProcessingStrategy processorStrategy) {
        this.processorStrategy = processorStrategy;
    }

    /**
     * 对输入字符串进行完整处理，直到字符串稳定。
     * <p>
     * 处理流程：
     * 1. 验证输入字符串的合法性（非空、仅含小写字母）
     * 2. 反复调用策略的{@link LetterProcessingStrategy#processOnce(String)}方法
     * 3. 当处理前后的字符串相同时，停止处理并返回结果
     * </p>
     *
     * @param input 输入字符串
     * @return 最终处理后的字符串
     * @throws IllegalArgumentException 如果输入为null或包含非小写字母字符
     */
    public String process(String input) {
        // 输入合法性校验
        if (input == null) {
            throw new IllegalArgumentException("输入字符串不能为null");
        }
        if (input.isEmpty()) {
            return ""; // 空字符串直接返回（或根据业务改为抛出异常）
        }
        if (!input.matches("[a-z]*")) {
            throw new IllegalArgumentException("输入必须仅包含小写字母（a-z），当前输入：" + input);
        }

        String current = input;

        // 持续处理直到字符串不再变化
        while (true) {
            String next = processorStrategy.processOnce(current);
            if (next.equals(current)) { // 如果处理后的字符串与原字符串相同，说明已处理完成
                break;
            }
            current = next;
        }

        return current; // 返回处理后的字符串
    }
}