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


## 开发环境
- **JDK 版本**：21
- **构建工具**：Maven 3.6+
- **IDE 兼容**：IntelliJ IDEA（项目包含 `.idea` 配置文件夹，其他 IDE 可通过 Maven 导入）
- **编码格式**：UTF-8
- **测试框架**：JUnit 5.11.4