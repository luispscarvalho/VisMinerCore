package org.visminer.core.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the metric_value database table.
 * 
 */
@Entity
@Table(name="metric_value")
public class MetricValueDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MetricValuePK id;

	@Lob
	@Column(nullable=false)
	private String value;

	//bi-directional many-to-one association to Metric
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metric_id", nullable=false, insertable=false, updatable=false)
	private MetricDB metric;

	//bi-directional many-to-one association to SoftwareUnit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="software_unit_id", nullable=false, insertable=false, updatable=false)
	private SoftwareUnitDB softwareUnit;

	public MetricValueDB() {
	}

	public MetricValuePK getId() {
		return this.id;
	}

	public void setId(MetricValuePK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MetricDB getMetric() {
		return this.metric;
	}

	public void setMetric(MetricDB metric) {
		this.metric = metric;
	}

	public SoftwareUnitDB getSoftwareUnit() {
		return this.softwareUnit;
	}

	public void setSoftwareUnit(SoftwareUnitDB softwareUnit) {
		this.softwareUnit = softwareUnit;
	}

}