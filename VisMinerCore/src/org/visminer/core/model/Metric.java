package org.visminer.core.model;

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

public class Metric {

	public class Business {

		private int id;
		private String name;
		private String description;
		private String value;

		public Business() {
		}

		public Business(String name, String description, String value, int id) {
			super();
			this.name = name;
			this.description = description;
			this.value = value;
			this.id = id;
		}

		public Business(String name, String description, int id) {
			super();
			this.name = name;
			this.description = description;
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Business other = (Business) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	}

	@Entity
	@Table(name = "metric")
	@NamedQuery(name = "Metric.findAll", query = "SELECT m FROM Metric m")
	public class Database implements Serializable {
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
		private List<MetricValue.Database> metricValues;

		public Database() {
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

		public List<MetricValue.Database> getMetricValues() {
			return this.metricValues;
		}

		public void setMetricValues(List<MetricValue.Database> metricValues) {
			this.metricValues = metricValues;
		}

		public MetricValue.Database addMetricValue(
				MetricValue.Database metricValue) {
			getMetricValues().add(metricValue);
			metricValue.setMetric(this);

			return metricValue;
		}

		public MetricValue.Database removeMetricValue(
				MetricValue.Database metricValue) {
			getMetricValues().remove(metricValue);
			metricValue.setMetric(null);

			return metricValue;
		}

	}

}
