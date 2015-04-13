package org.visminer.core.model.database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commit database table.
 * 
 */
@Entity
@Table(name="commit")
public class CommitDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMMIT_ID_GENERATOR", sequenceName="COMMIT_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMIT_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Lob
	private String message;

	@Column(nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Committer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="committer_id", nullable=false)
	private CommitterDB committer;

	//bi-directional many-to-many association to Tree
	@ManyToMany(mappedBy="commits")
	private List<TreeDB> trees;

	//bi-directional many-to-one association to FileXCommit
	@OneToMany(mappedBy="commit")
	private List<FileXCommitDB> fileXCommits;

	//bi-directional many-to-many association to Issue
	@ManyToMany(mappedBy="commits")
	private List<IssueDB> issues;

	//bi-directional many-to-one association to SoftwareUnit
	@OneToMany(mappedBy="commit")
	private List<SoftwareUnitDB> softwareUnits;

	public CommitDB() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommitterDB getCommitter() {
		return this.committer;
	}

	public void setCommitter(CommitterDB committer) {
		this.committer = committer;
	}

	public List<TreeDB> getTrees() {
		return this.trees;
	}

	public void setTrees(List<TreeDB> trees) {
		this.trees = trees;
	}

	public List<FileXCommitDB> getFileXCommits() {
		return this.fileXCommits;
	}

	public void setFileXCommits(List<FileXCommitDB> fileXCommits) {
		this.fileXCommits = fileXCommits;
	}

	public FileXCommitDB addFileXCommit(FileXCommitDB fileXCommit) {
		getFileXCommits().add(fileXCommit);
		fileXCommit.setCommit(this);

		return fileXCommit;
	}

	public FileXCommitDB removeFileXCommit(FileXCommitDB fileXCommit) {
		getFileXCommits().remove(fileXCommit);
		fileXCommit.setCommit(null);

		return fileXCommit;
	}

	public List<IssueDB> getIssues() {
		return this.issues;
	}

	public void setIssues(List<IssueDB> issues) {
		this.issues = issues;
	}

	public List<SoftwareUnitDB> getSoftwareUnits() {
		return this.softwareUnits;
	}

	public void setSoftwareUnits(List<SoftwareUnitDB> softwareUnits) {
		this.softwareUnits = softwareUnits;
	}

	public SoftwareUnitDB addSoftwareUnit(SoftwareUnitDB softwareUnit) {
		getSoftwareUnits().add(softwareUnit);
		softwareUnit.setCommit(this);

		return softwareUnit;
	}

	public SoftwareUnitDB removeSoftwareUnit(SoftwareUnitDB softwareUnit) {
		getSoftwareUnits().remove(softwareUnit);
		softwareUnit.setCommit(null);

		return softwareUnit;
	}

}