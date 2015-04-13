package org.visminer.core.model.database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the committer_role database table.
 * 
 */
@Entity
@Table(name="committer_role")
public class CommitterRoleDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CommitterRolePK id;

	@Column(nullable=false)
	private boolean contribuitor;

	//bi-directional many-to-one association to Committer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="committer_id", nullable=false, insertable=false, updatable=false)
	private CommitterDB committer;

	//bi-directional many-to-one association to Repository
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="repository_id", nullable=false, insertable=false, updatable=false)
	private RepositoryDB repository;

	public CommitterRoleDB() {
	}

	public CommitterRolePK getId() {
		return this.id;
	}

	public void setId(CommitterRolePK id) {
		this.id = id;
	}

	public boolean getContribuitor() {
		return this.contribuitor;
	}

	public void setContribuitor(boolean contribuitor) {
		this.contribuitor = contribuitor;
	}

	public CommitterDB getCommitter() {
		return this.committer;
	}

	public void setCommitter(CommitterDB committer) {
		this.committer = committer;
	}

	public RepositoryDB getRepository() {
		return this.repository;
	}

	public void setRepository(RepositoryDB repository) {
		this.repository = repository;
	}

}