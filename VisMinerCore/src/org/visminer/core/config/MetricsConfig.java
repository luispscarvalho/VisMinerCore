package org.visminer.core.config;

import java.util.ArrayList;
import java.util.List;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.metric.IMetric;

public class MetricsConfig {

	private static VisMinerMetric annotations;

	private static List<IMetric<AST>> fileMetrics = new ArrayList<IMetric<AST>>();
	private static List<IMetric<TypeDeclaration>> classMetrics = new ArrayList<IMetric<TypeDeclaration>>();
	private static List<IMetric<MethodDeclaration>> methodMetrics = new ArrayList<IMetric<MethodDeclaration>>();

	@SuppressWarnings("unchecked")
	public static void setMetricsClasspath(List<String> classPaths) {
		for (String classPath : classPaths) {
			try {
				IMetric<?> metric = (IMetric<?>) Class.forName(classPath)
						.newInstance();
				annotations = metric.getClass().getAnnotation(
						VisMinerMetric.class);
				if (isFileMetric()) {
					fileMetrics.add((IMetric<AST>) metric);
				} else if (isClassMetric()) {
					classMetrics.add((IMetric<TypeDeclaration>) metric);
				} else if (isMethodMetric()) {
					methodMetrics.add((IMetric<MethodDeclaration>) metric);
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean isFileMetric() {
		return annotations.on()
				&& annotations.target().equals(VisMinerMetric.Target.FILE);
	}

	private static boolean isClassMetric() {
		return annotations.on()
				&& annotations.target().equals(VisMinerMetric.Target.CLASS);
	}

	private static boolean isMethodMetric() {
		return annotations.on()
				&& annotations.target().equals(VisMinerMetric.Target.METHOD);
	}

	public static List<IMetric<AST>> getFileMetrics() {
		return fileMetrics;
	}

	public static List<IMetric<TypeDeclaration>> getClassMetrics() {
		return classMetrics;
	}

	public static List<IMetric<MethodDeclaration>> getMethodMetrics() {
		return methodMetrics;
	}

}
