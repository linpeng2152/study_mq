package com.linp.study_mq;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * created by : linpeng
 * ON 2019-10-26 10:21
 * used for:
 */

public abstract class JacksonUtils {

    protected static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public JacksonUtils() {
    }

    public static String serializeObjectToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception var2) {
            log.error("serialize object to json", var2);
            return null;
        }
    }

    public static String serializeObjectToJson(Object obj, boolean indent) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (indent) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, indent);
            }

            return mapper.writeValueAsString(obj);
        } catch (Exception var3) {
            log.error("serialize object to json", var3);
            return null;
        }
    }

    public static void serializeObjectToFile(Object obj, File file, boolean indent) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (indent) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, indent);
            }

            mapper.writeValue(file, obj);
        } catch (Exception var4) {
            log.error("serialize object to json", var4);
        }

    }

    public static <T> T deserializeFormFile(File file, Class<T> clazz, boolean indent) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (indent) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, indent);
            }

            return mapper.readValue(file, clazz);
        } catch (Exception var4) {
            log.error("deserializeFormFile", var4);
            return null;
        }
    }

    public static <T> T deserializeFormFile(InputStream in, Class<T> clazz, boolean indent) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (indent) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, indent);
            }

            return mapper.readValue(in, clazz);
        } catch (Exception var4) {
            log.error("deserializeFormFile", var4);
            return null;
        }
    }

    public static <T> T deserializeJsonToObject(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception var3) {
            log.error("deserialize json to object", var3);
            return null;
        }
    }

    public static <T> T deserializeJsonToObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception var3) {
            log.error("deserialize json to object", var3);
            return null;
        }
    }

    public static Object deserializeJsonToObject(String json, JavaType jt) {
        try {
            return mapper.readValue(json, jt);
        } catch (Exception var3) {
            log.error("deserialize json to object", var3);
            return null;
        }
    }

    public static <T> JavaType getListJavaType(Class<T> clazz) {
        TypeFactory instance = TypeFactory.defaultInstance();
        return instance.constructCollectionType(List.class, clazz);
    }

    public static <T> List<T> deserializeJsonToList(String json, Class<T> clazz) {
        JavaType jt = getListJavaType(clazz);

        try {
            return (List)mapper.readValue(json, jt);
        } catch (Exception var4) {
            log.error("deserialize json to object", var4);
            return null;
        }
    }

    public static <K, V> JavaType getMapJavaType(Class<K> clazzKey, Class<V> clazzValue) {
        TypeFactory instance = TypeFactory.defaultInstance();
        return instance.constructMapType(Map.class, clazzKey, clazzValue);
    }

    public static <K, V> Map<K, V> deserializeJsonToMap(String json, Class<K> clazzKey, Class<V> clazzValue) {
        JavaType jt = getMapJavaType(clazzKey, clazzValue);

        try {
            return (Map)mapper.readValue(json, jt);
        } catch (Exception var5) {
            log.error("deserialize json to object", var5);
            return null;
        }
    }

    public static <K, V> List<Map<K, V>> deserializeJsonToListMap(String json, Class<K> clazzKey, Class<V> clazzValue) {
        TypeFactory instance = TypeFactory.defaultInstance();
        CollectionType jt = instance.constructCollectionType(List.class, instance.constructMapType(Map.class, clazzKey, clazzValue));

        try {
            return (List)mapper.readValue(json, jt);
        } catch (Exception var6) {
            log.error("deserialize json to object", var6);
            return null;
        }
    }
}
