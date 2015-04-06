package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "metric")
@NamedQuery(name = "Metric.findAll", query = "SELECT m FROM Metric m")
public class MetricDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "description", length = 255, nullable = false)
	private String description;

	@Column(name = "name", length = 45, nullable = false, unique = true)
	private String name;

	// bi-directional many-to-one association to MetricValue
	@OneToMany(mappedBy = "metric")
	private List<MetricValueDB.Database> metricValues;

	public MetricDB() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<MetricValueDB.Database> getMetricValues() {
		return this.metricValues;
	}

	public void setMetricValues(List<MetricValueDB.Database> metricValues) {
		this.metricValues = metricValues;
	}

	public MetricValueDB.Database addMetricValue(MetricValueDB.Database metricValue) {
		getMetricValues().add(metricValue);
		metricValue.setMetric(this);

		return metricValue;
	}

	public MetricValueDB.Database removeMetricValue(
			MetricValueDB.Database metricValue) {
		getMetricValues().remove(metricValue);
		metricValue.setMetric(null);

		return metricValue;
	}

}
