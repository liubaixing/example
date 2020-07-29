package com.example.sharding;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class ShardTest {


    @Test
    public void demo(){
        SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();
        Set<String> singleSet = new HashSet<>();
        Set<String> doubleSet = new HashSet<>();
        Integer singleCount = 0;
        Integer doubleCount = 0;
        for (int i = 1; i <= 1000000; i++){
            if(Long.valueOf(keyGenerator.generateKey().toString())%2 == 1){
                singleCount++;
            }else{
                doubleCount ++;
            }
        }

        System.out.printf("单数数量=%o;双数数量=%o",singleCount,doubleCount);
    }


    @Test
    public void hash(){
        String[] keys = {"太阳", "月亮", "星星"};
        for(int i=0; i<keys.length; i++) {
            System.out.printf("%s>>>;FNV1_32_HASH=%o,hash=%o%n",keys[i],FNV1_32_HASH(keys[i]),hash(keys[i]));
        }
    }


    private static int FNV1_32_HASH(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static int hash(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes;
        try {
            keyBytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + value, e);
        }

        md5.update(keyBytes);
        byte[] digest = md5.digest();

        // hash code, Truncate to 32-bits
        int hashCode = (digest[3] & 0xFF) << 24
                | (digest[2] & 0xFF) << 16
                |  (digest[1] & 0xFF) << 8
                | (digest[0] & 0xFF);

        return hashCode & 0xffffffff;
    }
}
