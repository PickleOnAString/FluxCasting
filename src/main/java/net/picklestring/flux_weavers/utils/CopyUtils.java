package net.picklestring.flux_weavers.utils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.function.Function;

public class CopyUtils {
	public static HashMap<Type, Function<Object, Object>> copyMethods = new HashMap<>();

	static {
		copyMethods.put(Vector3.class, (obj) -> {
			Vector3 vec3 = (Vector3) obj;
			return new Vector3(vec3.getX(), vec3.getY(), vec3.getZ());
		});
	}

	public static Object Copy(Object obj) {
		if (!copyMethods.containsKey(obj.getClass())) return obj;
		return copyMethods.get(obj.getClass()).apply(obj);
	}
}
