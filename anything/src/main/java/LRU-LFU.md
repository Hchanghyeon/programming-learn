## 페이지/캐시 교체 알고리즘
- 메모리가 꽉 찼을 때 어떤 페이지를 스왑 영역으로 내보낼 것인지에 대한 결정 알고리즘
- Cache에서도 특정 메모리 사이즈를 선택하고, 꽉 찼을 때 교체 알고리즘을 통해 삭제 가능


### LRU
- Least Recently Used
- 사용한지 가장 오래된 데이터부터 삭제하는 알고리즘
- 모든 키를 Head-Tail 형태로 나열하고,데이터를 사용하면 Head로 옮김
- 새 데이터가 들어오면 Tail을 삭제

### LFU
- Least Frequently Used
- 사용 빈도 수가 가장 적은 데이터부터 삭제하는 방식
- 최근에 사용한 데이터라도 자주 사용되지 않는다면 제거 대상이 가능
- 각 데이터는 사용된 횟수를 카운트하는 노드에 연결
- 최대 사용량까지 사용하고 있을 때 새로운 데이터가 들어오면 카운트가 적은 데이터가 삭제됨


### Redis Key Evict 전략
https://redis.io/docs/latest/develop/reference/eviction/


``` 
maxmemory 100mb
```
- maxmemory 설정을 통해 저장될 수 있는 양을 제한
- 64비트 시스템의 컴퓨터는 기본적으로 0으로 설정되는데, 메모리 제한이 없다는 뜻임

### 메모리 정책
- maxmemory-policy 설정 값으로 변경 가능(redis.conf나 config set으로 설정)

[삭제하지 않는 정책]
- noeviction
  - 캐시를 지우지 않는 정책 -> 메모리가 MAX일 경우 에러 발생

[ALL KEY 메모리 정책]

모든 키를 정리 가능한 알고리즘

- allkeys-lru
  - LRU 알고리즘을 기반으로 키를 삭제
- allkey-random
  - 랜덤하게 키를 삭제
- allkeys-lfu
  - 가장 적게 사용된 키 삭제

[volatile 메모리 정책]
volatile은 EXPIRE SET에 있는 키들만 정리함.
EXPIRE SET은 EXPIRE 설정이 된 키들을 의미하며 기본적으로 아무설정안하면 만료 시간이 정해져있지않음

- volatile-lru
  - EXPIRE SET안에 있는 키를 LRU 알고리즘 기반으로 삭제
- volatile-random
  - EXPIRE SET안에 있는 키들을 랜덤하게 삭제
- volatile-ttl
  - EXPIRE SET안에 있는 키들을 TTL이 짧은 순 대로 삭제
- volatile-lfu
  - EXPIRE SET안에 있는 키 중 가장 적게 사용된 키가 삭제


expire 삭제 코드(내부 스케줄러 - activeExpireCycle)
https://github.com/redis/redis/blob/unstable/src/expire.c#L123
