package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.visminer.core.constant.SoftwareUnitType;


/**
 * The persistent class for the software_unit database table.
 * 
 */
@Entity
@Table(name="software_unit")
public class SoftwareUnitDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SOFTWARE_UNIT_ID_GENERATOR", sequenceName="SOFTWARE_UNIT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOFTWARE_UNIT_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=255)
	private String name;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private SoftwareUnitType type;

	//bi-directional many-to-one association to MetricValue
	@OneToMany(mappedBy="softwareUnit")
	private List<MetricValueDB> metricValues;

	//bi-directional many-to-one association to Commit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="commit_id", nullable=false)
	private CommitDB commit;

	//bi-directional many-to-one association to File
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="file_id")
	private FileDB file;

	//bi-directional many-to-one association to SoftwareUnit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	private SoftwareUnitDB softwareUnit;

	//bi-directional many-to-one association to SoftwareUnit
	@OneToMany(mappedBy="softwareUnit")
	private List<SoftwareUnitDB> softwareUnits;

	public SoftwareUnitDB() {
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

	public List<MetricValueDB> getMetricValues() {
		return this.metricValues;
	}

	public void setMetricValues(List<MetricValueDB> metricValues) {
		this.metricValues = metricValues;
	}

	public MetricValueDB addMetricValue(MetricValueDB metricValue) {
		getMetricValues().add(metricValue);
		metricValue.setSoftwareUnit(this);

		return metricValue;
	}

	public MetricValueDB removeMetricValue(MetricValueDB metricValue) {
		getMetricValues().remove(metricValue);
		metricValue.setSoftwareUnit(null);

		return metricValue;
	}

	public CommitDB getCommit() {
		return this.commit;
	}

	public void setCommit(CommitDB commit) {
		this.commit = commit;
	}

	public FileDB getFile() {
		return this.file;
	}

	public void setFile(FileDB file) {
		this.file = file;
	}

	public SoftwareUnitDB getSoftwareUnit() {
		return this.softwareUnit;
	}

	public void setSoftwareUnit(SoftwareUnitDB softwareUnit) {
		this.softwareUnit = softwareUnit;
	}

	public List<SoftwareUnitDB> getSoftwareUnits() {
		return this.softwareUnits;
	}

	public void setSoftwareUnits(List<SoftwareUnitDB> softwareUnits) {
		this.softwareUnits = softwareUnits;
	}

	public SoftwareUnitDB addSoftwareUnit(SoftwareUnitDB softwareUnit) {
		getSoftwareUnits().add(softwareUnit);
		softwareUnit.setSoftwareUnit(this);

		return softwareUnit;
	}

	public SoftwareUnitDB removeSoftwareUnit(SoftwareUnitDB softwareUnit) {
		getSoftwareUnits().remove(softwareUnit);
		softwareUnit.setSoftwareUnit(null);

		return softwareUnit;
	}

}