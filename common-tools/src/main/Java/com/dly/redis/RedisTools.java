package com.dly.redis;

import com.dly.exception.code.BasicErrCodes;
import com.dly.util.FileUtil;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RedisTools
{
    /**
     * 字符串序列化与反序列化
     */
    public static enum StringSerializer implements RedisSerializer<String>
    {
        INSTANCE;

        @Override
        public byte[] serialize(String s) throws SerializationException
        {
            return (null != s ? s.getBytes() : new byte[0]);
        }

        @Override
        public String deserialize(byte[] bytes) throws SerializationException
        {
            if (bytes.length > 0){
                return new String(bytes);
            }
            else{
                return null;
            }
        }
    }

    /**
     * 长整形序列化与反序列化
     */
    public static enum LongSerializer implements RedisSerializer<Long>
    {
        INSTANCE;

        @Override
        public byte[] serialize(Long aLong) throws SerializationException
        {
            if (null != aLong){
                return aLong.toString().getBytes();
            }
            else{
                return new byte[0];
            }
        }

        @Override
        public Long deserialize(byte[] bytes) throws SerializationException
        {
            if (bytes.length > 0){
                return Long.parseLong(new String(bytes));
            }
            else{
                return null;
            }
        }
    }


    /**
     * 整形的序列化与反序列化
     */
    public static enum IntSerializer implements RedisSerializer<Integer>
    {
        INSTANCE;

        @Override
        public byte[] serialize(Integer i) throws SerializationException
        {
            if (null != i){
                return i.toString().getBytes();
            }
            else{
                return new byte[0];
            }
        }

        @Override
        public Integer deserialize(byte[] bytes) throws SerializationException
        {
            if (bytes.length > 0){
                return Integer.parseInt(new String(bytes));
            }
            else{
                return null;
            }
        }
    }

    /**
     * 数组的序列化与反序列化
     */
    public static enum ListSerializer implements RedisSerializer<Object>
    {
        INSTANCE;

        @Override
        public byte[] serialize(Object value) throws SerializationException
        {
            if (value == null) {
                return null;
            }

            byte[] rv = null;
            ByteArrayOutputStream bos = null;
            ObjectOutputStream os = null;

            try {
                bos = new ByteArrayOutputStream();
                os = new ObjectOutputStream(bos);
                os.writeObject(value);
                os.close();
                bos.close();
                rv = bos.toByteArray();
            }
            catch (Exception e) {
                throw BasicErrCodes.REDIS_SERIALIZE_ERR.exception( e );
            }
            finally {
                FileUtil.closeOutputStream(os);
                FileUtil.closeOutputStream(bos);
            }

            return rv;
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException
        {
            if( bytes == null || bytes.length == 0 ){
                return null;
            }

            Object rv = null;
            ByteArrayInputStream bis = null;
            ObjectInputStream is = null;

            try {
                if (bytes != null) {
                    bis = new ByteArrayInputStream(bytes);
                    is = new ObjectInputStream(bis);
                    rv = is.readObject();
                    is.close();
                    bis.close();
                }
            }
            catch (Exception e) {
                throw BasicErrCodes.REDIS_DESERIALIZE_ERR.exception( e );
            }
            finally {
                FileUtil.closeInputStream(is);
                FileUtil.closeInputStream(bis);
            }

            return rv;
        }
    }

}
