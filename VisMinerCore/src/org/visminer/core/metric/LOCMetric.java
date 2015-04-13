package org.visminer.core.metric;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.bean.SoftwareUnit;
import org.visminer.core.persistence.IMetricPersistance;

@VisMinerMetric(description = "Lines of Code Metric", name = "Lines of Code", on = true, target = Target.CLASS)
public class LOCMetric implements IMetric<TypeDeclaration> {

	@Override
	public void calculate(TypeDeclaration clazz, SoftwareUnit classUnit,
			IMetricPersistance persistance) {
		int loc = clazz.getLinesOfCode();

		// TODO precisa setar o id surrogate em classUnit
		persistance.save(classUnit, loc);
	}

}
