# springboot-demo

一个基于 **Spring Boot 3.3.8 + Java 17** 的最小示例工程，包含三个 REST 接口。

## 环境要求

- JDK 17+
- Maven 3.6+

## 快速开始

```bash
# 启动
mvn spring-boot:run

# 或者打包后运行
mvn clean package
java -jar target/springboot-demo-0.0.1-SNAPSHOT.jar
```

服务默认监听 `http://localhost:8080`。

## 接口列表

### 1. 打招呼

```
GET /api/hello?name=Alice
```

```json
{
  "message": "Hello, Alice!",
  "timestamp": "2026-11-17 18:30:00"
}
```

`name` 参数可选，缺省为 `World`。

### 2. 服务器时间

```
GET /api/time?zone=Asia/Shanghai
```

```json
{
  "zone": "Asia/Shanghai",
  "datetime": "2026-11-17 18:30:00",
  "epochMillis": 1763375400000
}
```

`zone` 参数可选，缺省为 `Asia/Shanghai`，可传入任意合法的时区 ID。

### 3. 回显

```
POST /api/echo
Content-Type: application/json

{"message": "hello"}
```

```json
{
  "echo": "hello",
  "length": 5,
  "timestamp": "2026-11-17 18:30:00"
}
```

`message` 为必填项，为空时返回 400。

## 项目结构

```
src/main/java/com/example/demo
├── DemoApplication.java          # 启动类
├── controller/ApiController.java # 三个接口
├── service/GreetingService.java  # 业务逻辑
├── dto/                          # 请求 / 响应体
└── exception/                    # 全局异常处理
```

## License

MIT
