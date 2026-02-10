package com.kotak.demo.util;

import org.slf4j.Logger;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;

public class BeanUtils {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(BeanUtils.class);

	private BeanUtils() {
	}

	public static void copyProperties(Object src, Object target, Set<String> properties) {
		String[] ignorePropertyNames = Arrays.stream(org.springframework.beans.BeanUtils.getPropertyDescriptors(src.getClass())).map(PropertyDescriptor::getName)
				.filter(key -> properties == null || !properties.contains(key)).toArray(String[]::new);

		org.springframework.beans.BeanUtils.copyProperties(src, target, ignorePropertyNames);
	}

	public static void copyProperties(Object src, Object target) {
		try {
			org.springframework.beans.BeanUtils.copyProperties(src, target, "");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static <T> T initializeChild(Object parent, Class<T> childClazz) {
		try {
			String parentJson = JsonUtils.objectToJson(parent);
			return JsonUtils.jsonToObject(parentJson, childClazz);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public static <T> T initializeBean(Object source, Class<T> targetClazz) {
		try {
			String parentJson = JsonUtils.objectToJson(source);
			return source != null ? JsonUtils.jsonToObject(parentJson, targetClazz) : null;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

}
