package flatmap;

import java.time.LocalDate;
import java.util.List;

public class FlatMap {

    private static final List<User> users = List.of(
            new User(1L, "alpha", List.of(
                    new AccessLog(1L, "192.168.0.1", LocalDate.of(2015, 11, 30), 10),
                    new AccessLog(3L, "192.168.0.2", LocalDate.of(2018, 3, 3), 20),
                    new AccessLog(7L, "192.168.0.3", LocalDate.of(2020, 7, 15), 100))),
            new User(2L, "beta", List.of(
                    new AccessLog(2L, "192.168.0.1", LocalDate.of(2018, 3, 3), 25),
                    new AccessLog(4L, "192.168.0.5", LocalDate.of(2019, 8, 23), 17),
                    new AccessLog(6L, "192.168.0.6", LocalDate.of(2020, 5, 1), 80))),
            new User(3L, "gamma", List.of(
                    new AccessLog(5L, "192.168.0.1", LocalDate.of(2020, 2, 25), 150),
                    new AccessLog(8L, "192.168.0.8", LocalDate.of(2020, 8, 5), 200),
                    new AccessLog(9L, "192.168.0.9", LocalDate.of(2021, 1, 5), 55))));


    /**
     * 1. 사용자는 사용자가 접속한 기록에 대한 정보가 담겨있음
     * 2. 사용자들의 접근 기록중 ip가 192.168.0.1인 것들만 불러오고 싶음
     * 3. 사용자들의 접근 기록을 불러옴
     * 4. 사용자별 접근 기록은 List로 이루어져있으며 List들에 대해 개별로 192.168.0.1에 대한 정보를 비교해서 처리해야 함
     * 5. 사용자별로 뽑아내야하는 정보가 아닌, 사용자들의 전체 로그를 한줄로 세워서 반복문을 돌려 처리해야하는 상황
     * 6. flatMap을 이용하면 2차원 배열을 1차원 평면으로 처리할 수 있음
     */

    public static void main(String[] args) {
        List<AccessLog> accessLogList = users.stream()
                .map(User::accessLogs)
                .flatMap(accessLogs -> accessLogs.stream().filter(accessLog-> accessLog.ip().equals("192.168.0.1")))
                .toList();


        System.out.printf(accessLogList.toString());

        // 결과 값
        /*
            [
              AccessLog[id=1, ip=192.168.0.1, accessAt=2015-11-30, responseTime=10],
              AccessLog[id=2, ip=192.168.0.1, accessAt=2018-03-03, responseTime=25],
              AccessLog[id=5, ip=192.168.0.1, accessAt=2020-02-25, responseTime=150]
             ]
         */
    }
}
