package org.visminer.core.metric;

import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.annotations.VisMinerMetric.Target;
import org.visminer.core.ast.AST;
import org.visminer.core.ast.TypeDeclaration;
import org.visminer.core.model.bean.SoftwareUnit;

@VisMinerMetric(description = "Number of Classes Metric", name = "Number of Classes", on = true, targets = { Target.CLASS })
public class NOCMetric implements ITypedMetric<Integer> {

	private int noc = 0;

	@Override
	public Map<SoftwareUnit, Integer> calculate(SoftwareUnit superUnit, AST ast) {
		noc = 0;
		for (TypeDeclaration type : ast.getDocument().getTypesDeclarations()) {
			if (!type.isInterface())
				noc++;
		}

		superUnit.addMetric(this);
		
		return null;
	}

	@Override
	public Integer getValue() {
		return noc;
	}

	@Override
	public void setValue(Integer value) {
		this.noc = value;
	}

}
