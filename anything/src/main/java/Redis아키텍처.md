## Redis 아키텍처
### 1. Replication(Master-Slave)
- AOF나 RDB와 같은 방식의 백업만을는 장애 대비가 부족
- 데이터베이스의 Replication 처럼 가용성과 조회 트래픽 분산을 통해 성능 향상 가능
- Master Node장애시 Slave Node를 Master로 전환
- Slave는 Read-Only 설정이 필요하며, Master 노드는 백업 기능을 활성화 하지 않는 경우 Master 재시작시 Slave도 빈상태로 복제 됨
- 수동 FailOver임

### 2. Sentinel
- Redis에서 HA를 제공하기 위한 장치(HA는 이중화 구성을 통해 서비스 중단이 없는 상태의 고가용성 유지를 위한 방법)
- Master-Slave 구조에서 Master가 다운시 slave를 Master로 승격시키는 오토 failover 수행
- Sentinal이라는 Node를 두어서 Master Node에 대한 Health Check를 진행하고, 만약 실패하면 다른 Sentinal Node와 투표를 진행하여 과반수 이상이 되었을 때 Master와 Slave노드를 전환함

### 3. Cluster
- 클러스터는 샤딩 기법을 이용하여 여러 노드에 자동적인 데이터를 분산 함
- 해시 함수를 통해 어떠한 노드로 들어가야하는지 판단
- 백업 서버의 보완을 통해 데이터의 유실 없이 서비스를 계속 이어나갈 수 있음

### 어떤 것을 선택하는 것이 좋을까?
- 현재 트래픽에 알맞는 것을 선택하면 좋을 것 같은데 소규모에서는 Sentinel 방식이 더 안정적으로 보이고, 대규모에서는 Cluster를 이용하는 것이 더 좋을 것 같다.
- Sentinel 방식은 Master-Slave, Sentinel Node 총 3개로 구성해도 되며 비록 Master와 Slave 2곳에 대한 분산 처리가 가능하지만 어느정도 적은 대수로도 HA 구성을 쉽게할 수 있다는 장점이 있음
- Cluster 방식은 우선 샤딩을 통해 여러 개의 서버가 Master로 동작할 수 있는 장점이 있지만 특정 노드에 데이터가 쏠리는 현상이 발생할 수 있음, 또한 백업 서버도 별도로 구성하여 데이터의 유실이 없이 처리하려면 꽤나 많은 노드가 필요할 수 있음
- Replication 방식은 수동 전환이 필요하여 HA 구성이 안되므로 정말 문제가 되지 않는 서비스들에서만 사용할 수 있을 것 같음