package org.visminer.core.model.database;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the metric_value database table.
 * 
 */
@Embeddable
public class MetricValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="metric_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int metricId;

	@Column(name="software_unit_id", insertable=false, updatable=false, unique=true, nullable=false)
	private int softwareUnitId;

	public MetricValuePK() {
	}
	public int getMetricId() {
		return this.metricId;
	}
	public void setMetricId(int metricId) {
		this.metricId = metricId;
	}
	public int getSoftwareUnitId() {
		return this.softwareUnitId;
	}
	public void setSoftwareUnitId(int softwareUnitId) {
		this.softwareUnitId = softwareUnitId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MetricValuePK)) {
			return false;
		}
		MetricValuePK castOther = (MetricValuePK)other;
		return 
			(this.metricId == castOther.metricId)
			&& (this.softwareUnitId == castOther.softwareUnitId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.metricId;
		hash = hash * prime + this.softwareUnitId;
		
		return hash;
	}
}