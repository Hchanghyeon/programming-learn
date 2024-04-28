## @Async 어노테이션에 대한 오해와 진실
- 실제로 블로그 글을 보면, @Async를 사용하는 경우 SimpleAsyncTaskExecutor를 이용하여 쓰레드가 무한정으로 생성될 수 있다고 써져있는 경우가 많음
- 하지만 실제로는 ThreadPoolTaskExecutor로 동작하며, 아무리 100개의 요청을 동시에 Async로 처리한다고 하더라도 100개의 쓰레드가 생성되서 처리되지 않음
  - ThreadPoolTaskExecutor에 등록된 CoreSize 설정과 Max, Queue의 설정에 따라 처리되는 양이 달라지며, 아무 설정 안할 경우 8개의 코어 사이즈로만 동작하기 때문에 Async로 100개를 처리한다하더라도 한꺼번에는 최대 8개까지 임
- SimpleAsyncTaskExecutor의 경우 Java21이상에서 Virtual Thread를 활성화 시키면 자동으로 ThreadPoolTaskExecutor에서 SimpleAsyncTaskExecutor로 변경됨

### 별도의 설정 없이 100개 테스트 시도

#### [테스트 환경]


**AsyncProcess.java**
```java
@Slf4j
@Component
public class AsyncProcess {

    @Async
    public void init() throws InterruptedException {
        log.info(Thread.currentThread().getName() + ": 시작");
        Thread.sleep(3000); // 실행되는 쓰레드 3초 대기
        log.info(Thread.currentThread().getName() + ": 종료");
    }
}
```

```java
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncProcess asyncProcess;

    @GetMapping
    public void asyncTest() throws InterruptedException {
        for(int i = 0; i < 100; i++){
            asyncProcess.init();  // @Async 메서드 호출 100번으로 쓰레드 100개 생성을 기대함
        }
    }
}
```

**[결과]**
- 쓰레드가 최대 8개까지만 생성되어 처리되고, 종료되면 나머지 종료된 개수만큼 다시 최대 8개를 채워서 동작


[Spring Actuator Beans 확인]
- Spring Actuator Beans를 통해 기본 ApplicationTaskExecutor는 ThreadPoolTaskExecutor인 것을 확인해볼 수 있음

### 가상 스레드 활성화 후 테스트 시도(Java21에서 테스트)
**application.yml**
```yaml
spring:
  threads:
    virtual:
      enabled: true # 버츄어 스레드 활성화 
```
- @Async 사용시 버츄어 스레드 활성화를 시키면 SimpleAsyncTaskExecutor로 변경됨

**[결과]**
- 쓰레드 100개가 한꺼번에 생성되어 처리 됨


[Spring Actuator Beans 확인]

- Spring Actuator Beans를 통해 기본 ApplicationTaskExecutor가 SimpleAsyncTaskExecutor로 변경된 것을 확인할 수 있음 

### SimpleAsyncTaskExecutor로 사용하는 다른 방법
```java
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {
        Executor executor = new SimpleAsyncTaskExecutor();
        return executor;
    }
}
```
- Config로 직접 주입


### 결론
- @Async를 사용한다해서 꼭 SimpleAsyncTaskExecutor를 이용하지 않고, 직접 빈으로 등록하거나 가상 스레드를 사용하는 경우에만 사용한다.
- @Async를 사용할 때 기본적으로 ThreadPoolTaskExecutor가 동작하기 때문에 코어 사이즈 만큼만 Thread가 생성되어 처리 됨
  - 추가적인 내용으로는 ThreadPoolTaskExecutor의 Max사이즈를 100개로 둔다고 해서, 비동기로 처리할 때 100개를 사용할 수 없음
  - 내부적으로 Integer.MAX_VALUE 사이즈의 LinkedBlockingQueue를 생성해서 Core Size만큼의 쓰레드만 처리하고 그 이후는 큐에 대기하기 때문에 큐가 꽉 찼을 경우에만 Max Size만큼 동작함
- @Async를 SimpleAsyncTaskExecutor로 사용하는 경우 무한정 스레드를 생성할 수 있다.
  - 하지만 성능상 좋지 못할 수 있다.
  - 쓰레드 풀로 재활용이 아니기 때문에 만드는 비용과 삭제가 든다. -> 하지만 Virtual Thread를 이용해서 만든다면 이런 부분이 어느정도는 감소할 수 있다.
