package com.xiezhuolong;

import com.xiezhuolong.letterprocessor.LetterProcessor;
import com.xiezhuolong.letterprocessor.strategy.impl.CharacterReplacementProcessor;
import com.xiezhuolong.letterprocessor.strategy.impl.CharacterRetentionProcessor;

/**
 * 程序入口类，演示字符处理器的使用方法。
 * <p>
 * 该类通过两种不同的处理策略（保留策略和替换策略）处理示例字符串，
 * 并输出处理结果。
 * </p>
 */
public class Main {

    /**
     * 程序主方法，执行字符处理演示。
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        String s = "aabbbbccccccbbaaaad"; // 示例输入字符串

        // 使用字符保留策略处理（Stage 1）
        LetterProcessor stage1Processor = new LetterProcessor(CharacterRetentionProcessor.INSTANCE);
        String retentionResult = stage1Processor.process(s);
        System.out.println("Stage 1 Result: " + retentionResult);

        // 使用字符替换策略处理（Stage 2）
        LetterProcessor stage2Processor = new LetterProcessor(CharacterReplacementProcessor.INSTANCE);
        String replacementResult = stage2Processor.process(s);
        System.out.println("Stage 2 Result: " + replacementResult);

        // To run more tests, please execute the LetterProcessorTest JUnit test class. Thank you!
    }
}