package org.example.redis;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.example.redis.leaderboard.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class SimpleTest {

    @Autowired
    private RankingService rankingService;

    @Test
    void getRanks() {
        rankingService.getTopRank(1);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Long user100 = rankingService.getUserRanking("user_100");
        stopWatch.stop();
        System.out.println("stopWatch.getTotalTimeMillis() = " + stopWatch.getTotalTimeMillis());
        System.out.println("user100 = " + user100);


        stopWatch.start();
        List<String> topRank = rankingService.getTopRank(10);
        stopWatch.stop();
        System.out.println("stopWatch.getTotalTimeMillis() = " + stopWatch.getTotalTimeMillis());
    }

    @Test
    void insetScore() {
        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000); // 0 ~ 999999
            String userId = "user_" + i;
            rankingService.setUserScore(userId, score);
        }
    }

    @Test
    void inMemorySortPerformance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000); // 0 ~ 999999
            list.add(score);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Collections.sort(list); // nlongn time complexity 를 가지는게 제일 빠르다
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("totalTimeMillis = " + totalTimeMillis);

    }


}
