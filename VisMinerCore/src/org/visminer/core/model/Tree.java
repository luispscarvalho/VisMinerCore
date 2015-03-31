package org.visminer.core.model;

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

public class Tree {

	public class Business {

		private int id;
		private String fullName;
		private String name;
		private TreeType type;

		public Business(int id, String name, String fullName, TreeType type) {
			super();
			this.id = id;
			this.name = name;
			this.fullName = fullName;
			this.type = type;
		}

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

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public TreeType getType() {
			return type;
		}

		public void setType(TreeType type) {
			this.type = type;
		}

	}

	@Entity
	@Table(name = "tree")
	@NamedQuery(name = "Tree.findAll", query = "SELECT t FROM Tree t")
	public class Database implements Serializable {
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
		private List<Commit.Database> commits;

		// bi-directional many-to-one association to Repository
		@ManyToOne
		@JoinColumn(name = "repository_id", nullable = false)
		private Repository.Database repository;

		public Database() {
		}

		public Database(String fullName, String name, TreeType type) {
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

		public List<Commit.Database> getCommits() {
			return this.commits;
		}

		public void setCommits(List<Commit.Database> commits) {
			this.commits = commits;
		}

		public Repository.Database getRepository() {
			return this.repository;
		}

		public void setRepository(Repository.Database repository) {
			this.repository = repository;
		}

	}
}
