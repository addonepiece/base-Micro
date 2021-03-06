package com.dly.id;

import com.dly.util.RandomUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ID生成器,用于生成ID
 *
 */

@FunctionalInterface
public interface IDGenerator<T> {
    T generate();

    /**
     * 使用UUID生成id
     */
    IDGenerator<String> UUID = () -> java.util.UUID.randomUUID().toString().replace("-", "");

    /**
     * 随机字符
     */
    IDGenerator<String> RANDOM = RandomUtil::randomChar;

    /**
     * md5(uuid()+random())
     */
    IDGenerator<String> MD5 = () -> {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(UUID.generate().concat(RandomUtil.randomChar()).getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    };

    /**
     * 雪花算法
     */
    IDGenerator<Long> SNOW_FLAKE = SnowflakeIdGenerator.getInstance()::nextId;

    /**
     * 雪花算法转String
     */
    IDGenerator<String> SNOW_FLAKE_STRING = () -> String.valueOf(SNOW_FLAKE.generate());

    /**
     * 雪花算法的16进制
     */
    IDGenerator<String> SNOW_FLAKE_HEX = () -> Long.toHexString(SNOW_FLAKE.generate());
}
