package com.training.dataconsume.component;

import java.util.Objects;

import org.hibernate.type.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.dataconsume.model.Users;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserSerializer implements RedisSerializer<Users> {
    private final ObjectMapper objectMapper;

    @Override
    public byte[] serialize(Users userRedis) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(userRedis); // Replace with actual serialization logic
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing User to JSON", e);
        }
    }

    @Override
    public Users deserialize(byte[] bytes) throws SerializationException {
        try {
            if (Objects.nonNull(bytes)) {
                return objectMapper.readValue(bytes, Users.class);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException("Error deserializing JSON to User", e);
        }
    }
}