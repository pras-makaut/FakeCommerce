package com.example.FakeCommerce.Services.Cache;

import com.example.FakeCommerce.dtos.GetProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRedisCache {

    private static final String KEY_SUMMARY = "product:summary:";

    private final StringRedisTemplate stringRedisTemplate;

    private  static final Duration CACHE_TTL = Duration.ofMinutes(1);
    private final ObjectMapper objectMapper;

    public Optional<GetProductResponseDto> getSummary(Long id){
        String responseJson = stringRedisTemplate.opsForValue().get(KEY_SUMMARY + id);

        if(responseJson == null){ // cache miss
            log.info("Cache miss with this " + id);
            return Optional.empty();
        }
        log.info("Cache hit with this id " + id);
        try{
            GetProductResponseDto responseDto = objectMapper.readValue(responseJson,GetProductResponseDto.class);
            return Optional.of(responseDto);
        } catch (Exception e) {
            log.error("parsing product summary from cahche : {}",e.getMessage());
            stringRedisTemplate.delete(KEY_SUMMARY + id); // because the data is crupted
            throw new RuntimeException(e);
        }
    }
    public void putSummary(Long id,GetProductResponseDto productResponseDto){
        try {
            stringRedisTemplate.opsForValue().set(KEY_SUMMARY + id , objectMapper.writeValueAsString(productResponseDto),CACHE_TTL);
        } catch (Exception e){
            log.error("error in setting data");
            throw new RuntimeException("Error  in serializing data");

        }
    }
}
