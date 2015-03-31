package org.visminer.core.metric;

import java.util.Map;

import org.visminer.core.ast.AST;
import org.visminer.core.model.SoftwareUnit;

public interface IMetric<ValueType> {

	public Map<SoftwareUnit, ValueType> calculate(AST ast);

	public String valueToString();

}
