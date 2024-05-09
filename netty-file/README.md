### Netty Client/Server File to DB
- Client에서 10만줄로 이루어진 데이터를 읽고, Client에서 Server로 Netty 통신을 통해 읽어들인다.
- 읽어드린 데이터를 DB로 파싱하여 저장한다.

**조건**
1. Text는 100,000 line이고 한 줄에는 9개의 UUID로 이루어져있는데 구분자로 구분되어있다.
2. NettyClient와 NettyServer 구조로 구현한다.
3. 데이터베이스에 저장할 때는 id값 외 나머지 UUID1~9번 컬럼 총 10개의 컬럼으로 저장한다.


### SimpleChannelInboundHandler 와 ChannelInboundHandler
- SimpleChannelInboundHandler는 argument로 넘어온 Message에 대한 책임이 없다.
  - 알아서 메모리에서 해제해주기 때문
- ChannelInboundHandler는 channelRead 메서드를 오버라이딩하면, 인자로 넘어온 Message는 직접 해제해야한다.
  - 안그럴 경우 메모리 Leak 발생
- SimpleChannelInboundHandler는 특정 유형의 메시지만 명시적으로 처리가 가능함
  - 직접 타입을 지정하여 사용

### 트러블 슈팅

**문제 상황**

1. 파일의 크기가 32MB여서 전송시 1MB씩 32번 전송되는 현상
2. 위와 같은 현상으로 인해 NettyServer쪽에서는 Handler의 readComplete이 32번 호출되고, 데이터베이스에 저장되는 로직이 32번 수행되는데 이때 전달되는 데이터의 양이 한 줄씩 잘 쪼개지지 않기 때문에 문제가 발생함
3. 채널의 option으로 버퍼의 사이즈를 늘리더라도 제대로 수행되지 않음

**해결 방법**

1. SimpleChannelInboundHandler를 이용하고, 파일의 맨마지막에 END를 붙임
2. 이것을 channelRead0에서 데이터를 수신받을 때 마다 공용으로 쓰는 Buffer를 두고 End문자열이 발견되는 순간 저장로직으로 저장 수행

### 결과
![Untitled](https://github.com/Hchanghyeon/programming-learn/assets/92444744/e0895122-189e-4f04-87d7-de8671f9cb8e)
