package net.picklestring.flux_weavers.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CastingContext {
	public boolean isStack = false;
	public boolean isCanceled = false;
	public Stack<Object> stack = new Stack<>();
	public HashMap<String, Object> extraContext = new HashMap<>();
}
