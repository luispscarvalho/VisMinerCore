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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.visminer.core.model.bean.Repository;
import org.visminer.core.utility.StringUtils;

@Entity
@Table(name = "repository")
@NamedQuery(name = "Repository.findAll", query = "SELECT r FROM Repository r")
public class RepositoryDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	@Column(name = "path", length = 1024, nullable = false)
	private String path;

	@Column(name = "remote_name", length = 255, nullable = true)
	private String remoteName;

	@Column(name = "remote_owner", length = 255, nullable = true)
	private String remoteOwner;

	@Column(name = "remote_service", length = 2, nullable = true)
	private int remoteService;

	@Column(name = "sha", length = 40, nullable = false, unique = true)
	private String sha;

	@Column(name = "type", length = 2, nullable = false)
	private int type;

	// bi-directional many-to-many association to Committer
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "committer_has_repository", joinColumns = { @JoinColumn(name = "repository_id") }, inverseJoinColumns = { @JoinColumn(name = "committer_id") })
	private List<CommitterDB> committers;

	// bi-directional many-to-one association to Tree
	@OneToMany(mappedBy = "repository")
	private List<TreeDB> trees;

	public RepositoryDB() {
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

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRemoteName() {
		return this.remoteName;
	}

	public void setRemoteName(String remoteName) {
		this.remoteName = remoteName;
	}

	public String getRemoteOwner() {
		return this.remoteOwner;
	}

	public void setRemoteOwner(String remoteOwner) {
		this.remoteOwner = remoteOwner;
	}

	public int getRemoteService() {
		return this.remoteService;
	}

	public void setRemoteService(int remoteService) {
		this.remoteService = remoteService;
	}

	public String getSha() {
		return this.sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<CommitterDB> getCommitters() {
		return this.committers;
	}

	public void setCommitters(List<CommitterDB> committers) {
		this.committers = committers;
	}

	public List<TreeDB> getTrees() {
		return this.trees;
	}

	public void setTrees(List<TreeDB> trees) {
		this.trees = trees;
	}

	public TreeDB addTree(TreeDB tree) {
		getTrees().add(tree);
		tree.setRepository(this);

		return tree;
	}

	public TreeDB removeTree(TreeDB tree) {
		getTrees().remove(tree);
		tree.setRepository(null);

		return tree;
	}

	public void getValuesOf(Repository repository) {

		this.name = repository.getName();
		this.path = repository.getPath();
		this.remoteName = repository.getRemoteName();
		this.remoteOwner = repository.getRemoteOwner();
		this.remoteService = repository.getRemoteType();
		this.type = repository.getType();
		this.sha = StringUtils.sha1(this.path);

	}

}
