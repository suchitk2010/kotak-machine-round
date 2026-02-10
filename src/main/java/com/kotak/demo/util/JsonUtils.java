package com.kotak.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {

	private JsonUtils() {
	}

	private static final String LOG_TAG = "[ OBJECT MAPPING ERROR ] %s";

	private static ObjectMapper mapper = JsonMapper.builder()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
			.findAndAddModules()
			.build();

	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static boolean isValidJson(String str) {
		try {
			mapper.readTree(str);
			return true;
		} catch (Exception e) {
			logError(e);
			return false;
		}
	}

	public static byte[] objectToBytes(Object payload) {
		try {
			String json = objectToJson(payload);
			return json != null ? json.getBytes() : new byte[0];
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static <T> T jsonToObject(String payload, TypeReference<T> typeRef) {
		try {
			return mapper.readValue(payload, typeRef);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static <T> T jsonToObject(InputStream stream, TypeReference<T> typeRef) {
		try {
			return mapper.readValue(stream, typeRef);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static <T> T jsonToObject(String payload, Class<T> clazz) {
		try {
			return mapper.readValue(payload, clazz);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static <T> T bytesToObject(byte[] bytes, Class<T> clazz) {
		try {
			String json = new String(bytes);
			return jsonToObject(json, clazz);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static <T> List<T> jsonToObjectList(String payload, Class<T> clazz) {
		try {
			List<?> list = mapper.readValue(payload, List.class);
			List<T> typeList = new ArrayList<>();
			list.forEach(i -> typeList.add(jsonToObject(objectToJson(i), clazz)));
			return typeList;
		} catch (Exception e) {
			logError(e);
			return new ArrayList<>();
		}
	}

	public static <T> T streamToObject(InputStream stream, Class<T> clazz) {
		try {
			return mapper.readValue(stream, clazz);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static <T> List<T> streamToObjectList(InputStream stream, Class<T> clazz) {
		try {
			List<?> list = mapper.readValue(stream, List.class);
			List<T> typeList = new ArrayList<>();
			list.forEach(i -> typeList.add(jsonToObject(objectToJson(i), clazz)));
			return typeList;
		} catch (Exception e) {
			logError(e);
			return new ArrayList<>();
		}
	}

	public static String objectToJson(Object object) {
		if (object == null) {
			return null;
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object object) {
		if (object == null) {
			return new HashMap<>();
		}
		try {
			return jsonToObject(mapper.writeValueAsString(object), Map.class);
		} catch (Exception e) {
			logError(e);
			return new HashMap<>();
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> stringToMap(String json) {
		try {
			return jsonToObject(json, Map.class);
		} catch (Exception e) {
			logError(e);
			return new HashMap<>();
		}
	}

	public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
		try {
			return jsonToObject(objectToJson(map), clazz);
		} catch (Exception e) {
			logError(e);
			return null;
		}
	}

	public static String getJsonString(Object obj) {
		if (obj != null) {
			boolean isString = obj instanceof String;
			return isString ? String.valueOf(obj) : objectToJson(obj);
		}
		return null;
	}

	public static <T> TypeReference<T> getTypeReference() {
		return new TypeReference<T>() {
		};
	}

	private static void logError(Exception e) {
		String message = String.format(LOG_TAG, e.getMessage());
		log.error(message);
	}

}
