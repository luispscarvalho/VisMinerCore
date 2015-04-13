package org.visminer.core.metric;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.bean.SoftwareUnit;
import org.visminer.core.persistence.IMetricPersistance;

@VisMinerMetric(description = "Number of Classes Metric", name = "Number of Classes", on = true, target = Target.FILE)
public class NOCMetric implements IMetric<AST> {

	@Override
	public void calculate(AST ast, SoftwareUnit fileUnit,
			IMetricPersistance persistance) {
		int noc = 0;
		for (TypeDeclaration type : ast.getDocument().getTypesDeclarations()) {
			if (!type.isInterface()) {
				noc++;
			}
		}

		// TODO precisa setar o id surrogate em fileUnit
		persistance.save(fileUnit, noc);
	}
}
