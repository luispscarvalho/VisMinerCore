package org.visminer.core.metric;

public interface ITypedMetric<ValueType> extends IMetric<ValueType> {

	public ValueType getAccumulatedValue();
	
	public void setAccumulatedValue(ValueType value);

}
