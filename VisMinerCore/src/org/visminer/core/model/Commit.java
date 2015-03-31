package org.visminer.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.visminer.core.model.File.FileXCommit;
import org.visminer.core.persistence.dao.FileDAO;
import org.visminer.core.persistence.dao.SoftwareUnitDAO;

public class Commit {

	public class Business {

		private int id;
		private String name;
		private String message;
		private Date date;
		private List<File.Business> files;

		public Business() {
		}

		public Business(int id, String name, String message, Date date) {
			super();
			this.id = id;
			this.name = name;
			this.message = message;
			this.date = date;

			FileDAO fileDao = new FileDAO();
			SoftwareUnitDAO unitDao = new SoftwareUnitDAO();

			List<File.Database> filesDb = fileDao.getByCommit(id);
			files = new ArrayList<File.Business>(filesDb.size());

			for (File.Database f : filesDb) {
				File.Business file = (new File()).new Business(f.getId(),
						f.getPath(), f.getSha(), f.getFileXCommits().get(0)
								.isDeleted());
				files.add(file);
				file.setSoftwareEntitiesFromDB(unitDao.getByCommitAndFile(
						this.id, file.getId()));
			}

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

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public List<File.Business> getFiles() {
			return files;
		}

		public void setFiles(List<File.Business> files) {
			this.files = files;
		}

	}

	@Entity
	@Table(name = "commit")
	@NamedQuery(name = "Commit.findAll", query = "SELECT c FROM Commit c")
	public class Database implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "date", nullable = false)
		private Date date;

		@Lob
		@Column(name = "message", nullable = false)
		private String message;

		@Column(name = "name", length = 40, nullable = false, unique = true)
		private String name;

		// bi-directional many-to-one association to Committer
		@ManyToOne
		@JoinColumn(nullable = false, name = "committer_id")
		private Committer.Database committer;

		// bi-directional many-to-many association to Tree
		@ManyToMany(mappedBy = "commits")
		private List<Tree> trees;

		// bi-directional many-to-one association to FileXCommit
		@OneToMany(mappedBy = "commit", fetch = FetchType.LAZY)
		private List<FileXCommit> fileXCommits;

		public Database() {
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

		public Committer.Database getCommitter() {
			return this.committer;
		}

		public void setCommitter(Committer.Database committer) {
			this.committer = committer;
		}

		public List<Tree> getTrees() {
			return this.trees;
		}

		public void setTrees(List<Tree> trees) {
			this.trees = trees;
		}

		public List<FileXCommit> getFileXCommits() {
			return this.fileXCommits;
		}

		public void setFileXCommits(List<FileXCommit> fileXCommits) {
			this.fileXCommits = fileXCommits;
		}

		public FileXCommit addFileXCommit(FileXCommit fileXCommit) {
			getFileXCommits().add(fileXCommit);
			fileXCommit.setCommit(this);

			return fileXCommit;
		}

		public FileXCommit removeFileXCommit(FileXCommit fileXCommit) {
			getFileXCommits().remove(fileXCommit);
			fileXCommit.setCommit(null);

			return fileXCommit;
		}

		public void addTree(Tree tree) {
			getTrees().add(tree);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
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
			Commit.Database other = (Commit.Database) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	}
}
