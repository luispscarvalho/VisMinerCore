package org.visminer.core.metric;

import java.util.Map;

import org.visminer.core.ast.AST;
import org.visminer.core.model.bean.SoftwareUnit;

public interface IMetric<ValueType> {

	public Map<SoftwareUnit, ValueType> calculate(
			SoftwareUnit superUnit, AST ast);

}
