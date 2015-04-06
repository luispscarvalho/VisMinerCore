package org.visminer.core.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

public class MetricValueDB {

	@Entity
	@Table(name = "metric_value")
	@NamedQuery(name = "MetricValue.findAll", query = "SELECT m FROM MetricValue m")
	public class Database implements Serializable {
		private static final long serialVersionUID = 1L;

		@EmbeddedId
		private MetricValuePK id;

		@Column(name = "value", length = 45, nullable = false)
		private String value;

		// bi-directional many-to-one association to Metric
		@ManyToOne
		private MetricDB metric;

		// bi-directional many-to-one association to SoftwareEntity
		@ManyToOne
		@JoinColumn(name = "software_entity_id")
		private SoftwareUnitDB softwareEntity;

		public Database() {
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

		public SoftwareUnitDB getSoftwareEntity() {
			return this.softwareEntity;
		}

		public void setSoftwareEntity(SoftwareUnitDB softwareEntity) {
			this.softwareEntity = softwareEntity;
		}

	}

	@Embeddable
	public class MetricValuePK implements Serializable {
		// default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		@Column(name = "software_entity_id", insertable = false, updatable = false)
		private int softwareEntityId;

		@Column(name = "metric_id", insertable = false, updatable = false)
		private int metricId;

		public MetricValuePK() {
		}

		public int getSoftwareEntityId() {
			return this.softwareEntityId;
		}

		public void setSoftwareEntityId(int softwareEntityId) {
			this.softwareEntityId = softwareEntityId;
		}

		public int getMetricId() {
			return this.metricId;
		}

		public void setMetricId(int metricId) {
			this.metricId = metricId;
		}

		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof MetricValuePK)) {
				return false;
			}
			MetricValuePK castOther = (MetricValuePK) other;
			return (this.softwareEntityId == castOther.softwareEntityId)
					&& (this.metricId == castOther.metricId);
		}

		public int hashCode() {
			final int prime = 31;
			int hash = 17;
			hash = hash * prime + this.softwareEntityId;
			hash = hash * prime + this.metricId;

			return hash;
		}
	}
}
