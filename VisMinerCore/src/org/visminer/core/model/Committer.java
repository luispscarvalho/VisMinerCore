package org.visminer.core.model;

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

public class Committer {

	public class Business {

		private int id;
		private String name;
		private String email;
		
		public Business(int id, String name, String email){
			this.id = id;
			this.name = name;
			this.email = email;
		}

		public Business(){}

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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
			Business other = (Business) obj;
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

	@Entity
	@Table(name="committer")
	@NamedQuery(name="Committer.findAll", query="SELECT c FROM Committer c")
	public class Database implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id", nullable=false)
		private int id;

		@Column(name="email", length=255, nullable=false)
		private String email;

		@Column(name="name", length=255, nullable=false)
		private String name;

		//bi-directional many-to-one association to Commit
		@OneToMany(mappedBy="committer")
		private List<Commit.Database> commits;

		//bi-directional many-to-many association to Repository
		@ManyToMany(mappedBy="committers")
		private List<Database> repositories;

		public Database() {
		}

		public Database(String email, String name) {
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

		public List<Commit.Database> getCommits() {
			return this.commits;
		}

		public void setCommits(List<Commit.Database> commits) {
			this.commits = commits;
		}

		public Commit.Database addCommit(Commit.Database commit) {
			getCommits().add(commit);
			commit.setCommitter(this);

			return commit;
		}

		public Commit.Database removeCommit(Commit.Database commit) {
			getCommits().remove(commit);
			commit.setCommitter(null);

			return commit;
		}

		public List<Database> getRepositories() {
			return this.repositories;
		}

		public void setRepositories(List<Database> repositories) {
			this.repositories = repositories;
		}

		public void addRepository(Database repository){
			this.getRepositories().add(repository);
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
			Database other = (Database) obj;
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
}
