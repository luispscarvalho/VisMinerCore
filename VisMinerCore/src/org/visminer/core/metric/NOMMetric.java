package org.visminer.core.metric;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.bean.SoftwareUnit;
import org.visminer.core.persistence.IMetricPersistance;

@VisMinerMetric(description = "Number of Methods Metric", name = "Number of Methods", on = true, target = Target.CLASS)
public class NOMMetric implements IMetric<TypeDeclaration> {

	@Override
	public void calculate(TypeDeclaration clazz, SoftwareUnit classUnit,
			IMetricPersistance persistance) {
		int nom = clazz.getMethods().size();

		// TODO precisa setar o id surrogate em classUnit
		persistance.save(classUnit, nom);
	}

}
