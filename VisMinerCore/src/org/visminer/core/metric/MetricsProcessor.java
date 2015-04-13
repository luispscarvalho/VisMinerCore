package org.visminer.core.metric;

import java.util.List;

import org.visminer.core.ast.AST;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.config.MetricsConfig;
import org.visminer.core.constant.SoftwareUnitType;
import org.visminer.core.model.bean.SoftwareUnit;
import org.visminer.core.persistence.IMetricPersistance;

public class MetricsProcessor {

	private IMetricPersistance persistance;

	public void processAll(AST ast, IMetricPersistance metricPersistance) {
		persistance = metricPersistance;

		SoftwareUnit fileUnit = processFileMetrics(ast);
		for (TypeDeclaration classDecl : ast.getDocument()
				.getTypesDeclarations()) {
			SoftwareUnit classUnit = processClassMetrics(fileUnit, classDecl);
			for (MethodDeclaration methodDecl : classDecl.getMethods()) {
				processMethodMetrics(classUnit, methodDecl);
			}

		}

	}

	private SoftwareUnit processFileMetrics(AST ast) {
		SoftwareUnit fileUnit = createFileUnit(ast);
		List<IMetric<AST>> metrics = MetricsConfig.getFileMetrics();

		for (IMetric<AST> metric : metrics) {
			metric.calculate(ast, fileUnit, persistance);
		}

		return fileUnit;
	}

	private SoftwareUnit processClassMetrics(SoftwareUnit fileUnit,
			TypeDeclaration classDecl) {
		SoftwareUnit classUnit = createClassUnit(fileUnit, classDecl);
		List<IMetric<TypeDeclaration>> metrics = MetricsConfig
				.getClassMetrics();

		for (IMetric<TypeDeclaration> metric : metrics) {
			metric.calculate(classDecl, classUnit, persistance);
		}

		return classUnit;
	}

	private SoftwareUnit processMethodMetrics(SoftwareUnit classUnit,
			MethodDeclaration methodDecl) {
		SoftwareUnit methodUnit = createMethodUnit(classUnit, methodDecl);
		List<IMetric<MethodDeclaration>> metrics = MetricsConfig
				.getMethodMetrics();

		for (IMetric<MethodDeclaration> metric : metrics) {
			metric.calculate(methodDecl, methodUnit, persistance);
		}

		return methodUnit;
	}

	private SoftwareUnit createFileUnit(AST ast) {
		SoftwareUnit file = new SoftwareUnit();

		file.setName(ast.getDocument().getElementName());
		file.setParentUnit(null);
		file.setType(SoftwareUnitType.FILE);

		return file;
	}

	private SoftwareUnit createClassUnit(SoftwareUnit fileUnit,
			TypeDeclaration classDecl) {
		SoftwareUnit clazz = new SoftwareUnit();

		clazz.setName(classDecl.getName());
		clazz.setParentUnit(fileUnit);
		clazz.setType(SoftwareUnitType.CLASS);

		return clazz;
	}

	private SoftwareUnit createMethodUnit(SoftwareUnit clazz,
			MethodDeclaration methodDecl) {
		SoftwareUnit method = new SoftwareUnit();

		method.setName(methodDecl.getName());
		method.setParentUnit(clazz);
		method.setType(SoftwareUnitType.METHOD);

		return method;
	}

}
