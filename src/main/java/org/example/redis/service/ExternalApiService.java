package org.example.redis.service;

import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    public String getUserName(String userId) {
        // 외부 서비스나 DB 호출
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("getting user name from external api...");

        if (userId.equals("A")) {
            return "Adam";
        }

        if (userId.equals("B")) {
            return "Bob";
        }

        return "John Doe";
    }

    public int getUserAge(String userId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("getting user age from external api...");

        if (userId.equals("A")) {
            return 28;
        }

        if (userId.equals("B")) {
            return 32;
        }

        return 30;
    }

}
