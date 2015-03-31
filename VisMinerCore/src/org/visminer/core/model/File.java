package org.visminer.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class File {

	public class Business {

		private int id;
		private String path;
		private String sha;
		private boolean deleted;
		private List<SoftwareUnit.Business> softwareEntities;

		public Business() {
		}

		public Business(int id, String path, String sha, boolean deleted) {
			super();
			this.id = id;
			this.path = path;
			this.sha = sha;
			this.deleted = deleted;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public boolean isDeleted() {
			return deleted;
		}

		public void setDeleted(boolean deleted) {
			this.deleted = deleted;
		}

		public String getSha() {
			return sha;
		}

		public void setSha(String sha) {
			this.sha = sha;
		}

		public List<SoftwareUnit.Business> getSoftwareEntities() {
			return softwareEntities;
		}

		public void setSoftwareEntities(
				List<SoftwareUnit.Business> softwareEntities) {
			this.softwareEntities = softwareEntities;
		}

		public void setSoftwareEntitiesFromDB(
				List<SoftwareUnit.Database> dbEntities) {
			this.softwareEntities = new ArrayList<SoftwareUnit.Business>(
					dbEntities.size());

			for (SoftwareUnit.Database s : dbEntities) {
				SoftwareUnit.Business softwareEntity = (new SoftwareUnit()).new Business(
						s.getId(), s.getName(), s.getType());
				this.softwareEntities.add(softwareEntity);
			}

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((sha == null) ? 0 : sha.hashCode());
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
			if (sha == null) {
				if (other.sha != null)
					return false;
			} else if (!sha.equals(other.sha))
				return false;
			return true;
		}

	}

	@Entity
	@Table(name = "file")
	@NamedQuery(name = "File.findAll", query = "SELECT f FROM File f")
	public class Database implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", nullable = false)
		private int id;

		@Column(name = "path", length = 1024, nullable = false)
		private String path;

		@Column(name = "sha", length = 40, nullable = false, unique = true)
		private String sha;

		// bi-directional many-to-one association to FileXCommit
		@OneToMany(mappedBy = "file", fetch = FetchType.LAZY)
		private List<FileXCommit> fileXCommits;

		public Database() {
		}

		public int getId() {
			return this.id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPath() {
			return this.path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getSha() {
			return this.sha;
		}

		public void setSha(String sha) {
			this.sha = sha;
		}

		public List<FileXCommit> getFileXCommits() {
			return this.fileXCommits;
		}

		public void setFileXCommits(List<FileXCommit> fileXCommits) {
			this.fileXCommits = fileXCommits;
		}

		public FileXCommit addFileXCommit(FileXCommit fileXCommit) {
			getFileXCommits().add(fileXCommit);
			fileXCommit.setFile(this);

			return fileXCommit;
		}

		public FileXCommit removeFileXCommit(FileXCommit fileXCommit) {
			getFileXCommits().remove(fileXCommit);
			fileXCommit.setFile(null);

			return fileXCommit;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((sha == null) ? 0 : sha.hashCode());
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
			if (sha == null) {
				if (other.sha != null)
					return false;
			} else if (!sha.equals(other.sha))
				return false;
			return true;
		}

	}

	@Entity
	@Table(name="file_x_commit")
	@NamedQuery(name="FileXCommit.findAll", query="SELECT f FROM FileXCommit f")
	public class FileXCommit implements Serializable {
		private static final long serialVersionUID = 1L;

		@EmbeddedId
		private FileXCommitPK id;

		@Column(name="deleted", nullable=false)
		private boolean deleted;
		
		//bi-directional many-to-one association to Commit
		@ManyToOne
		private Commit.Database commit;

		//bi-directional many-to-one association to File
		@ManyToOne
		private File.Database file;

		//bi-directional many-to-one association to SoftwareEntity
		@OneToMany(mappedBy="fileXCommit", cascade=CascadeType.PERSIST)
		private List<SoftwareUnit.Database> softwareEntities;

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

		public Commit.Database getCommit() {
			return this.commit;
		}

		public void setCommit(Commit.Database commit) {
			this.commit = commit;
		}

		public File.Database getFile() {
			return this.file;
		}

		public void setFile(File.Database file) {
			this.file = file;
		}

		public List<SoftwareUnit.Database> getSoftwareEntities() {
			return this.softwareEntities;
		}

		public void setSoftwareEntities(List<SoftwareUnit.Database> softwareEntities) {
			this.softwareEntities = softwareEntities;
		}

		public SoftwareUnit.Database addSoftwareEntity(SoftwareUnit.Database softwareEntity) {
			getSoftwareEntities().add(softwareEntity);
			softwareEntity.setFileXCommit(this);

			return softwareEntity;
		}

		public SoftwareUnit.Database removeSoftwareEntity(SoftwareUnit.Database softwareEntity) {
			getSoftwareEntities().remove(softwareEntity);
			softwareEntity.setFileXCommit(null);

			return softwareEntity;
		}

	}

	@Embeddable
	public class FileXCommitPK implements Serializable {
		//default serial version id, required for serializable classes.
		private static final long serialVersionUID = 1L;

		@Column(name="file_id", insertable=false, updatable=false)
		private int fileId;

		@Column(name="commit_id", insertable=false, updatable=false)
		private int commitId;

		public FileXCommitPK() {
		}
		public FileXCommitPK(int fileId, int commitId) {
			this.fileId = fileId;
			this.commitId = commitId;
		}
		public int getFileId() {
			return this.fileId;
		}
		public void setFileId(int fileId) {
			this.fileId = fileId;
		}
		public int getCommitId() {
			return this.commitId;
		}
		public void setCommitId(int commitId) {
			this.commitId = commitId;
		}

		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!(other instanceof FileXCommitPK)) {
				return false;
			}
			FileXCommitPK castOther = (FileXCommitPK)other;
			return 
				(this.fileId == castOther.fileId)
				&& (this.commitId == castOther.commitId);
		}

		public int hashCode() {
			final int prime = 31;
			int hash = 17;
			hash = hash * prime + this.fileId;
			hash = hash * prime + this.commitId;
			
			return hash;
		}
	}
	
}
