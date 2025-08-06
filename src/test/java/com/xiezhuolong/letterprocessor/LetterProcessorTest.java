package com.xiezhuolong.letterprocessor;

import com.xiezhuolong.letterprocessor.strategy.impl.CharacterReplacementProcessor;
import com.xiezhuolong.letterprocessor.strategy.impl.CharacterRetentionProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterProcessorTest {

    /**
     * Stage 1 测试：移除连续3个及以上相同字符
     */
    @Test
    void testStage1Example() {
        LetterProcessor stage1Processor = new LetterProcessor(CharacterRetentionProcessor.INSTANCE);

        // 输入 "aabcccbbad" -> 处理后结果为 "d"
        assertEquals("d", stage1Processor.process("aabcccbbad"));

        // 输入 "aaa" -> 处理后结果为 ""
        assertEquals("", stage1Processor.process("aaa"));

        // 输入 "aabbb" -> 处理后结果为 "aa"
        assertEquals("aa", stage1Processor.process("aabbb"));
    }

    /**
     * Stage 2 测试：替换连续3个及以上相同字符为前一个字母
     */
    @Test
    void testStage2Example() {
        LetterProcessor stage2Processor = new LetterProcessor(CharacterReplacementProcessor.INSTANCE);

        // 输入 "ccc" -> 处理后结果为 "b"
        assertEquals("b", stage2Processor.process("ccc"));

        // 输入 "bbb" -> 处理后结果为 "a"
        assertEquals("a", stage2Processor.process("bbb"));

        // 输入 "aaa" -> 处理后结果为 ""
        assertEquals("", stage2Processor.process("aaa"));
        // 题目给出的示例a替换为空。如果按26个字母循环的逻辑应该要返回z，则使用下面代码。
        // assertEquals("z", stage2Processor.process("aaa"));

        // 输入 "abcccbad" -> 处理过程：abcccbad → abbbad → aaad → d
        assertEquals("d", stage2Processor.process("abcccbad"));
        // 题目给出的示例a替换为空。如果按26个字母循环的逻辑应该要返回z，则使用下面代码。
        // assertEquals("zd", stage2Processor.process("abcccbad"));

        // 示例："aabbbcccc" → 处理过程：
        // 第一次：aabbbcccc → aa + (bbb→a) + (cccc→b) → aaa b → 第二次：(aaa→"") + b → b
        assertEquals("b", stage2Processor.process("aabbbcccc"));
        // 题目给出的示例a替换为空。如果按26个字母循环的逻辑应该要返回z，则使用下面代码。
        // assertEquals("zb", stage2Processor.process("aabbbcccc"));

        // 测试边界值：最小连续长度配置为2（需修改application.properties为2）
        // 注：实际测试时可通过动态修改配置或新增测试配置文件实现
    }
}
