package org.example.redis.service;

import java.util.concurrent.TimeUnit;
import org.example.redis.dto.UserProfile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ExternalApiService externalApiService;
    private final StringRedisTemplate stringRedisTemplate;

    public UserService(ExternalApiService externalApiService, StringRedisTemplate stringRedisTemplate) {
        this.externalApiService = externalApiService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public UserProfile getUserProfile(String userId) {

        String userName = null;

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String cachedName = ops.get("nameKey:" + userId);

        if (cachedName != null) {
            userName = cachedName;
        } else {
            userName = externalApiService.getUserName(userId);
            ops.set("nameKey:" + userId, userName, 5, TimeUnit.SECONDS);
        }

//        String userName = externalApiService.getUserName(userId);
        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(userName, userAge);
    }

}
