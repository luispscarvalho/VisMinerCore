package org.visminer.core.metric;

import java.util.List;

public interface IKeyValueMetric<KeyType, ValueType> extends IMetric<ValueType> {
	
	public List<KeyValueItem<KeyType, ValueType>> getKeyValues();
	
	public void setKeyValues(List<KeyValueItem<KeyType, ValueType>> keyValues);

}
