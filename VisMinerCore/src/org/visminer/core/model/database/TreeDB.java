package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.visminer.core.constant.TreeType;


/**
 * The persistent class for the tree database table.
 * 
 */
@Entity
@Table(name="tree")
public class TreeDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TREE_ID_GENERATOR", sequenceName="TREE_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TREE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable=false)
	private Date lastUpdate;

	@Column(nullable=false, length=255)
	private String name;
	
	@Column(name="full_name", nullable=false, length=255)
	private String fullName;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private TreeType type;

	//bi-directional many-to-many association to Commit
	@ManyToMany
	@JoinTable(
		name="commit_reference_tree"
		, joinColumns={
			@JoinColumn(name="tree_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="commit_id", nullable=false)
			}
		)
	private List<CommitDB> commits;

	//bi-directional many-to-one association to Repository
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="repository_id", nullable=false)
	private RepositoryDB repository;

	public TreeDB() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public TreeType getType() {
		return this.type;
	}

	public void setType(TreeType type) {
		this.type = type;
	}

	public List<CommitDB> getCommits() {
		return this.commits;
	}

	public void setCommits(List<CommitDB> commits) {
		this.commits = commits;
	}

	public RepositoryDB getRepository() {
		return this.repository;
	}

	public void setRepository(RepositoryDB repository) {
		this.repository = repository;
	}

}