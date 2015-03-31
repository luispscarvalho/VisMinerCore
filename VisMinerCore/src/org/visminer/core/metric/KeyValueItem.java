package org.visminer.core.metric;

public class KeyValueItem<KeyType, ValueType> {

	KeyType key;
	ValueType value;

	public KeyType getKey() {
		return key;
	}

	public void setKey(KeyType key) {
		this.key = key;
	}

	public ValueType getValue() {
		return value;
	}

	public void setValue(ValueType value) {
		this.value = value;
	}

}
