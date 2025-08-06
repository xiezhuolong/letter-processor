# Letter Processor 项目说明

## 功能
处理字符串中连续重复的字母，支持两个阶段：
1. 移除连续≥3个的字母；
2. 将连续≥3个的字母替换为前一个字母。

## 设计思路
使用**策略模式**分离不同处理逻辑：
- `LetterProcessingStrategy`：定义处理策略的接口。
- `CharacterRetentionProcessor`/`CharacterReplacementProcessor`：具体策略实现。
- `LetterProcessor`：上下文类，封装策略调用。

## 运行方式
1. 编译：`mvn clean compile`  
2. 运行 `Main` 类：`java -cp target/classes com.xiezhuolong.Main`  
3. 执行测试：`mvn test`  