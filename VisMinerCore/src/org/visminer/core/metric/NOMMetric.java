package org.visminer.core.metric;

import java.util.List;
import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.MethodDeclaration;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.bean.SoftwareUnit;

@VisMinerMetric(description = "Number of Methods Metric", name = "Number of Methods", on = true, targets = { Target.CLASS })
public class NOMMetric implements ITypedMetric<Integer> {

	private int nom = 0;

	@Override
	public Map<SoftwareUnit, Integer> calculate(SoftwareUnit superUnit, AST ast) {
		nom = 0;
		for (TypeDeclaration type : ast.getDocument().getTypesDeclarations()) {
			List<MethodDeclaration> methods = type.getMethods();
			nom += methods.size();
		}

		superUnit.addMetric(this);

		return null;
	}

	@Override
	public Integer getValue() {
		return nom;
	}

	@Override
	public void setValue(Integer value) {
		this.nom = value;
	}

}
