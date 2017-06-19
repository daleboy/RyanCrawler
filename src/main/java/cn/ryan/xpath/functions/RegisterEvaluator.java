package cn.ryan.xpath.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import cn.ryan.xpath.functions.axis.AxisFunction;
import cn.ryan.xpath.functions.filter.FilterFunction;
import cn.ryan.xpath.functions.select.SelectFunction;
import cn.ryan.xpath.util.ClassScanner;
import cn.ryan.xpath.util.ObjectFactory;

public class RegisterEvaluator {
	private static Map<String, AxisFunction> axisFunctions = new HashMap<>();
	private static Map<String, FilterFunction> filterFunctions = new HashMap<>();
	private static Map<String, SelectFunction> selectFunctions = new HashMap<>();

	static {
		registerDefault();
	}

	/**
	 * 注册轴，谓语，函数
	 */
	private static void registerDefault() {
		ClassScanner.SubClassVisitor<FunctionName> functionVisitor = new ClassScanner.SubClassVisitor<FunctionName>(true, FunctionName.class);
		ClassScanner.scan(functionVisitor, Lists.newArrayList("cn.ryan.xpath.functions"));
		List<Class<? extends FunctionName>> allFunctionClasses = functionVisitor.getSubClass();
		for (Class<? extends FunctionName> clazz : allFunctionClasses) {
			FunctionName nameAware = ObjectFactory.newInstance(clazz);
			if (nameAware instanceof AxisFunction) {
				registerAxisFunction((AxisFunction) nameAware);
			} else if (nameAware instanceof FilterFunction) {
				registerFilterFunction((FilterFunction) nameAware);
			} else if (nameAware instanceof SelectFunction) {
				registerSelectFunction((SelectFunction) nameAware);
			}
		}
	}

	public synchronized static void registerAxisFunction(AxisFunction f) {
		if (!axisFunctions.containsKey(f.getName())) {
			axisFunctions.put(f.getName(), f);
		}
	}

	public synchronized static void registerFilterFunction(FilterFunction f) {
		if (!filterFunctions.containsKey(f.getName())) {
			filterFunctions.put(f.getName(), f);
		}
	}

	public synchronized static void registerSelectFunction(SelectFunction f) {
		if (!selectFunctions.containsKey(f.getName())) {
			selectFunctions.put(f.getName(), f);
		}
	}

	public static SelectFunction getSelectFunction(String functionName) {
		return selectFunctions.get(functionName);
	}

	public static FilterFunction getFilterFunction(String functionName) {
		return filterFunctions.get(functionName);
	}

	public static AxisFunction getAxisFunction(String functionName) {
		return axisFunctions.get(functionName);
	}
}
