package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.visminer.core.constant.TreeType;

@Entity
@Table(name = "tree")
@NamedQuery(name = "Tree.findAll", query = "SELECT t FROM Tree t")
public class TreeDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "full_name", nullable = false, length = 255)
	private String fullName;

	@Column(name = "type", nullable = false, length = 2)
	private TreeType type;

	// bi-directional many-to-many association to Commit
	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JoinTable(name = "commit_has_tree", joinColumns = { @JoinColumn(name = "tree_id") }, inverseJoinColumns = { @JoinColumn(name = "commit_id") })
	private List<CommitDB> commits;

	// bi-directional many-to-one association to Repository
	@ManyToOne
	@JoinColumn(name = "repository_id", nullable = false)
	private RepositoryDB repository;

	public TreeDB() {
	}

	public TreeDB(String fullName, String name, TreeType type) {
		super();
		this.fullName = fullName;
		this.name = name;
		this.type = type;
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
