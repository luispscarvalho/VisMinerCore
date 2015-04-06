package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.visminer.core.constant.SoftwareUnitType;

@Entity
@Table(name = "software_entity")
@NamedQuery(name = "SoftwareEntity.findAll", query = "SELECT s FROM SoftwareEntity s")
public class SoftwareUnitDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "type", nullable = false, length = 2)
	private SoftwareUnitType type;

	// bi-directional many-to-one association to MetricValue
	@OneToMany(mappedBy = "softwareEntity")
	private List<MetricValueDB.Database> metricValues;

	// bi-directional many-to-one association to FileXCommit
	@ManyToOne(optional = false)
	@JoinColumns({
			@JoinColumn(name = "commit_id", referencedColumnName = "commit_id", nullable = false),
			@JoinColumn(name = "file_id", referencedColumnName = "file_id", nullable = false) })
	private FileXCommit fileXCommit;

	// bi-directional many-to-one association to SoftwareEntity
	@ManyToOne
	@JoinColumn(name = "parent")
	private SoftwareUnitDB softwareEntity;

	// bi-directional many-to-one association to SoftwareEntity
	@OneToMany(mappedBy = "softwareEntity")
	private List<SoftwareUnitDB> softwareEntities;

	public SoftwareUnitDB() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public List<MetricValueDB.Database> getMetricValues() {
		return this.metricValues;
	}

	public void setMetricValues(List<MetricValueDB.Database> metricValues) {
		this.metricValues = metricValues;
	}

	public MetricValueDB.Database addMetricValue(
			MetricValueDB.Database metricValue) {
		getMetricValues().add(metricValue);
		metricValue.setSoftwareEntity(this);

		return metricValue;
	}

	public MetricValueDB.Database removeMetricValue(
			MetricValueDB.Database metricValue) {
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

	public SoftwareUnitDB getSoftwareEntity() {
		return this.softwareEntity;
	}

	public void setSoftwareEntity(SoftwareUnitDB softwareEntity) {
		this.softwareEntity = softwareEntity;
	}

	public List<SoftwareUnitDB> getSoftwareEntities() {
		return this.softwareEntities;
	}

	public void setSoftwareEntities(
			List<SoftwareUnitDB> softwareEntities) {
		this.softwareEntities = softwareEntities;
	}

	public SoftwareUnitDB addSoftwareEntity(
			SoftwareUnitDB softwareEntity) {
		getSoftwareEntities().add(softwareEntity);
		softwareEntity.setSoftwareEntity(this);

		return softwareEntity;
	}

	public SoftwareUnitDB removeSoftwareEntity(
			SoftwareUnitDB softwareEntity) {
		getSoftwareEntities().remove(softwareEntity);
		softwareEntity.setSoftwareEntity(null);

		return softwareEntity;
	}

}
