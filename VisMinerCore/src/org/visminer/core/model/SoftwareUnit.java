package org.visminer.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.visminer.core.constant.SoftwareUnitType;
import org.visminer.core.model.File.FileXCommit;
import org.visminer.core.persistence.dao.MetricValueDAO;

public class SoftwareUnit {

	public class Business {

		private int id;
		private String name;
		private SoftwareUnitType type;
		private List<Metric.Business> metrics;

		public Business() {
		}

		public Business(int id, String name, SoftwareUnitType type) {
			super();
			this.id = id;
			this.name = name;
			this.type = type;
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

		public SoftwareUnitType getType() {
			return type;
		}

		public void setType(SoftwareUnitType type) {
			this.type = type;
		}

		public List<Metric.Business> getMetrics() {
			if (this.metrics == null)
				initMetrics();
			return this.metrics;
		}

		public void setMetrics(List<Metric.Business> metrics) {
			this.metrics = metrics;
		}

		private void initMetrics() {
			MetricValueDAO metricDao = new MetricValueDAO();

			List<MetricValue.Database> metricsValues = metricDao
					.getBySoftwareEntity(this.id);
			this.metrics = new ArrayList<Metric.Business>(metricsValues.size());

			for (MetricValue.Database metricValue : metricsValues) {
				Metric.Business metric = (new Metric()).new Business(
						metricValue.getMetric().getName(), metricValue
								.getMetric().getDescription(),
						metricValue.getValue(), metricValue.getMetric().getId());
				this.metrics.add(metric);
			}
		}

		public Metric.Business getMetric(String name) {

			if (this.metrics == null)
				initMetrics();

			Metric.Business m = (new Metric()).new Business();
			m.setName(name);

			int index = this.metrics.indexOf(m);
			return this.metrics.get(index);

		}

	}

	@Entity
	@Table(name = "software_entity")
	@NamedQuery(name = "SoftwareEntity.findAll", query = "SELECT s FROM SoftwareEntity s")
	public class Database implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;

		@Column(name = "name", nullable = false, length = 255)
		private String name;

		@Column(name = "type", nullable = false, length = 2)
		private SoftwareUnitType type;

		// bi-directional many-to-one association to MetricValue
		@OneToMany(mappedBy = "softwareEntity")
		private List<MetricValue.Database> metricValues;

		// bi-directional many-to-one association to FileXCommit
		@ManyToOne(optional = false)
		@JoinColumns({
				@JoinColumn(name = "commit_id", referencedColumnName = "commit_id", nullable = false),
				@JoinColumn(name = "file_id", referencedColumnName = "file_id", nullable = false) })
		private FileXCommit fileXCommit;

		// bi-directional many-to-one association to SoftwareEntity
		@ManyToOne
		@JoinColumn(name = "parent")
		private SoftwareUnit.Database softwareEntity;

		// bi-directional many-to-one association to SoftwareEntity
		@OneToMany(mappedBy = "softwareEntity")
		private List<SoftwareUnit.Database> softwareEntities;

		public Database() {
		}

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public SoftwareUnitType getType() {
			return this.type;
		}

		public void setType(SoftwareUnitType type) {
			this.type = type;
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
			metricValue.setSoftwareEntity(this);

			return metricValue;
		}

		public MetricValue.Database removeMetricValue(
				MetricValue.Database metricValue) {
			getMetricValues().remove(metricValue);
			metricValue.setSoftwareEntity(null);

			return metricValue;
		}

		public FileXCommit getFileXCommit() {
			return this.fileXCommit;
		}

		public void setFileXCommit(FileXCommit fileXCommit) {
			this.fileXCommit = fileXCommit;
		}

		public SoftwareUnit.Database getSoftwareEntity() {
			return this.softwareEntity;
		}

		public void setSoftwareEntity(SoftwareUnit.Database softwareEntity) {
			this.softwareEntity = softwareEntity;
		}

		public List<SoftwareUnit.Database> getSoftwareEntities() {
			return this.softwareEntities;
		}

		public void setSoftwareEntities(
				List<SoftwareUnit.Database> softwareEntities) {
			this.softwareEntities = softwareEntities;
		}

		public SoftwareUnit.Database addSoftwareEntity(
				SoftwareUnit.Database softwareEntity) {
			getSoftwareEntities().add(softwareEntity);
			softwareEntity.setSoftwareEntity(this);

			return softwareEntity;
		}

		public SoftwareUnit.Database removeSoftwareEntity(
				SoftwareUnit.Database softwareEntity) {
			getSoftwareEntities().remove(softwareEntity);
			softwareEntity.setSoftwareEntity(null);

			return softwareEntity;
		}

	}
}
