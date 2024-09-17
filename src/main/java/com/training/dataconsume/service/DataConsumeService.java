package com.training.dataconsume.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.dataconsume.model.UserConsume;
import com.training.dataconsume.model.Users;
import com.training.dataconsume.repository.UserRepository;

@Service
public class DataConsumeService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    public void consumeData() {
        Set<String> keys = redisTemplate.keys("*");

        if (keys != null) {
            for (String username : keys) {
                Object data = redisTemplate.opsForValue().get(username);

                if (data != null) {
                    UserConsume userConsume = new ObjectMapper().convertValue(data, UserConsume.class);

                    Users user = new Users();
                    user.setUsername(userConsume.getUsername());
                    user.setPassword(userConsume.getPassword());
                    user.setToken(userConsume.getToken());
                    user.setRole(userConsume.getRole());

                    userRepository.save(user);
                }
            }
        }
    }
}
