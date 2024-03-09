package com.example.demorediscluster;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class DemoRedisClusterApplication {
  private final RedisTemplate<String, String> redisTemplate;

  public static void main(String[] args) {
    SpringApplication.run(DemoRedisClusterApplication.class, args);
  }


  @GetMapping("/get/{key}")
  public Object getKey(@PathVariable String key){
    log.info("getKey() called with: [{}]", key);
    return redisTemplate.opsForValue().get(key);
  }

  @PostMapping("/set")
  public ResponseEntity<?> setValue(@RequestBody KeyValueDto dto){
    log.info("setValue() called with: [{}]", dto);
     redisTemplate.opsForValue().set(dto.key, dto.value);
     return ResponseEntity.ok().build();
  }

  public record KeyValueDto (String key, String value){};
}
