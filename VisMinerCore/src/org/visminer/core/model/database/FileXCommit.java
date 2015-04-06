package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.visminer.core.model.bean.File;

@Entity
@Table(name = "file_x_commit")
@NamedQuery(name = "FileXCommit.findAll", query = "SELECT f FROM FileXCommit f")
public class FileXCommit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FileXCommitPK id;

	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	// bi-directional many-to-one association to Commit
	@ManyToOne
	private CommitDB commit;

	// bi-directional many-to-one association to File
	@ManyToOne
	private FileDB file;

	// bi-directional many-to-one association to SoftwareEntity
	@OneToMany(mappedBy = "fileXCommit", cascade = CascadeType.PERSIST)
	private List<SoftwareUnitDB> softwareEntities;

	public FileXCommit() {
	}

	public FileXCommit(FileXCommitPK fileXCommitPK) {
		this.id = fileXCommitPK;
	}

	public FileXCommitPK getId() {
		return this.id;
	}

	public void setId(FileXCommitPK id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	public List<SoftwareUnitDB> getSoftwareEntities() {
		return this.softwareEntities;
	}

	public void setSoftwareEntities(List<SoftwareUnitDB> softwareEntities) {
		this.softwareEntities = softwareEntities;
	}

	public SoftwareUnitDB addSoftwareEntity(SoftwareUnitDB softwareEntity) {
		getSoftwareEntities().add(softwareEntity);
		softwareEntity.setFileXCommit(this);

		return softwareEntity;
	}

	public SoftwareUnitDB removeSoftwareEntity(SoftwareUnitDB softwareEntity) {
		getSoftwareEntities().remove(softwareEntity);
		softwareEntity.setFileXCommit(null);

		return softwareEntity;
	}

}
