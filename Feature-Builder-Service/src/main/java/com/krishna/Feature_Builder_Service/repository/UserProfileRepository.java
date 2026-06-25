package com.krishna.Feature_Builder_Service.repository;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.krishna.Feature_Builder_Service.model.UserProfile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserProfileRepository {

    private final RedisTemplate<String,Object> redisTemplate;

    private static final String PREFIX = "user-profile:";

    public UserProfile get(String userId) {

        return (UserProfile)
                redisTemplate.opsForValue()
                             .get(PREFIX + userId);
    }

    public void save(UserProfile profile) {

        redisTemplate.opsForValue()
                     .set(
                         PREFIX + profile.getUserId(),
                         profile
                     );
    }
}