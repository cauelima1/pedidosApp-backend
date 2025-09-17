package pedidosApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class TokenBlackListService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void blackListToken(String token, long expirationMillis){
        redisTemplate.opsForValue().set(token, "blacklisted", expirationMillis, TimeUnit.MILLISECONDS);
    }

    public boolean isBlackList(String token){
        return redisTemplate.hasKey(token);
    }

    public void listarBlacklist() {
        Set<String> keys = redisTemplate.keys("*");
        keys.forEach(key -> {
            String value = redisTemplate.opsForValue().get(key);
            System.out.println("Token: " + key + " | Status: " + value);
        });
    }

}
