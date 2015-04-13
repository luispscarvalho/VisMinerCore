package org.visminer.core.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the metric database table.
 * 
 */
@Entity
@Table(name="metric")
public class MetricDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="METRIC_ID_GENERATOR", sequenceName="METRIC_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="METRIC_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String acronym;

	@Column(nullable=false, length=255)
	private String description;

	@Column(nullable=false, length=255)
	private String name;

	//bi-directional many-to-one association to MetricValue
	@OneToMany(mappedBy="metric")
	private List<MetricValueDB> metricValues;

	public MetricDB() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MetricValueDB> getMetricValues() {
		return this.metricValues;
	}

	public void setMetricValues(List<MetricValueDB> metricValues) {
		this.metricValues = metricValues;
	}

	public MetricValueDB addMetricValue(MetricValueDB metricValue) {
		getMetricValues().add(metricValue);
		metricValue.setMetric(this);

		return metricValue;
	}

	public MetricValueDB removeMetricValue(MetricValueDB metricValue) {
		getMetricValues().remove(metricValue);
		metricValue.setMetric(null);

		return metricValue;
	}

}