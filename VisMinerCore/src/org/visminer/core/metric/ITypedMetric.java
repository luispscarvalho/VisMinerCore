package org.visminer.core.metric;

public interface ITypedMetric<ValueType> extends IMetric<ValueType> {

	public ValueType getValue();
	
	public void setValue(ValueType value);

}
