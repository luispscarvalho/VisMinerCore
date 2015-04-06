package org.visminer.core.model.database;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "committer")
@NamedQuery(name = "Committer.findAll", query = "SELECT c FROM Committer c")
public class CommitterDB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "email", length = 255, nullable = false)
	private String email;

	@Column(name = "name", length = 255, nullable = false)
	private String name;

	// bi-directional many-to-one association to Commit
	@OneToMany(mappedBy = "committer")
	private List<CommitDB> commits;

	// bi-directional many-to-many association to Repository
	@ManyToMany(mappedBy = "committers")
	private List<RepositoryDB> repositories;

	public CommitterDB() {
	}

	public CommitterDB(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CommitDB> getCommits() {
		return this.commits;
	}

	public void setCommits(List<CommitDB> commits) {
		this.commits = commits;
	}

	public CommitDB addCommit(CommitDB commit) {
		getCommits().add(commit);
		commit.setCommitter(this);

		return commit;
	}

	public CommitDB removeCommit(CommitDB commit) {
		getCommits().remove(commit);
		commit.setCommitter(null);

		return commit;
	}

	public List<RepositoryDB> getRepositories() {
		return this.repositories;
	}

	public void setRepositories(List<RepositoryDB> repositories) {
		this.repositories = repositories;
	}

	public void addRepository(RepositoryDB repository) {
		repositories.add(repository);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommitterDB other = (CommitterDB) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
