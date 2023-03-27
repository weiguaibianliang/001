package com.Util;

import basic.model.base.RedisModel;
import com.instrument.template.MdRedisTemplate;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil implements MdRedisTemplate {
    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public String get(RedisModel redisModel) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public void setIfAbsent(String key, String value) {

    }

    @Override
    public void setDay(String key, Object value, Integer day) {

    }

    @Override
    public void setHours(String key, Object value, Integer hours) {

    }

    @Override
    public void setMinute(String key, Object value, Integer minute) {

    }

    @Override
    public void setSecond(String key, Object value, Integer second) {

    }

    @Override
    public void set(RedisModel redisModel) {

    }

    @Override
    public void expireMinute(String key, Integer time) {

    }

    @Override
    public void expireSecond(String key, Integer time) {

    }

    @Override
    public void expire(RedisModel redisModel) {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void delete(RedisModel redisModel) {

    }

    @Override
    public void deleteByPrex(String prex) {

    }

    @Override
    public Boolean hasKey(String key) {
        return null;
    }

    @Override
    public Boolean hasKey(RedisModel redisModel) {
        return null;
    }

    @Override
    public void setSet(String key, Set<String> set) {

    }

    @Override
    public Set<String> getSet(RedisModel redisModel) {
        return null;
    }

    @Override
    public Set<String> getSet(String key) {
        return null;
    }

    @Override
    public void setZSet(String key, Set<String> set) {

    }

    @Override
    public void addZSet(String key, Set<String> set) {

    }

    @Override
    public Set<String> getReverseZSet(String key, Integer limit) {
        return null;
    }

    @Override
    public void setList(String key, List<String> list) {

    }

    @Override
    public List<String> getList(String key) {
        return null;
    }

    @Override
    public void setMap(String key, Map<String, String> map) {

    }

    @Override
    public Map<String, String> getMap(String key) {
        return null;
    }

    @Override
    public Long getNumber(String key, int year) {
        return null;
    }

    @Override
    public int getBatchNum(String key) {
        return 0;
    }

    @Override
    public Integer increment(String key, long delta) {
        return null;
    }

    @Override
    public Boolean waitLock(String key, String value, int second) {
        return null;
    }

    @Override
    public boolean waitLock(String key) {
        return false;
    }

    @Override
    public boolean waitLock(String key, String value) {
        return false;
    }

    @Override
    public boolean waitLock(String key, String value, long second, int retryTimes, long sleepMillis) {
        return false;
    }

    @Override
    public boolean tryLock(String key, String value, long second) {
        return false;
    }

    @Override
    public boolean tryLock(String key, long second) {
        return false;
    }

    @Override
    public boolean releaseLock(String key) {
        return false;
    }

    @Override
    public boolean leftPushOne(String key, String value) {
        return false;
    }

    @Override
    public boolean leftPushBatch(String key, List<String> list) {
        return false;
    }

    @Override
    public String rightPopOne(String key) {
        return null;
    }

    @Override
    public String blobRightPop(String key, long timeout, TimeUnit timeUnit) {
        return null;
    }
}
