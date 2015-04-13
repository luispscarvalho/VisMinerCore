package org.visminer.core.model.bean;

import org.visminer.core.constant.SoftwareUnitType;

public class SoftwareUnit {

	private SoftwareUnit parentUnit;

	private String id;
	private String name;
	private SoftwareUnitType type;

	public SoftwareUnit() {
	}

	public SoftwareUnit(String id, String name, SoftwareUnitType type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SoftwareUnitType getType() {
		return type;
	}

	public void setType(SoftwareUnitType type) {
		this.type = type;
	}

	public void setParentUnit(SoftwareUnit parentUnit) {
		this.parentUnit = parentUnit;
	}

	public SoftwareUnit getParentUnit() {
		return parentUnit;
	}

}
