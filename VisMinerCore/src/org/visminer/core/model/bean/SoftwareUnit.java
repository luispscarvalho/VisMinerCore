package org.visminer.core.model.bean;

import java.util.ArrayList;
import java.util.List;

import org.visminer.core.constant.SoftwareUnitType;
import org.visminer.core.metric.IMetric;
import org.visminer.core.utility.StringUtils;

public class SoftwareUnit {

	private SoftwareUnit superUnit;

	private String id;
	private String name;
	private SoftwareUnitType type;
	private List<IMetric<?>> metrics = new ArrayList<IMetric<?>>();

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

	public void generateId() {
		this.id = StringUtils.uuid();
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

	public void setSuperUnit(SoftwareUnit superUnit) {
		this.superUnit = superUnit;
	}

	public SoftwareUnit getSuperUnit() {
		return superUnit;
	}

	public List<IMetric<?>> getMetrics() {
		return this.metrics;
	}

	public void setMetrics(List<IMetric<?>> metrics) {
		this.metrics = metrics;
	}

	public void addMetric(IMetric<?> metric) {
		this.metrics.add(metric);
	}

}
