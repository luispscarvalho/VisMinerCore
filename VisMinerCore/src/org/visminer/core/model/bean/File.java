package org.visminer.core.model.bean;

import java.util.List;

public class File {

	private int id;
	private String path;
	private String sha;
	private boolean deleted;
	private List<SoftwareUnit> softwareEntities;

	public File() {
	}

	public File(int id, String path, String sha, boolean deleted) {
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

	public List<SoftwareUnit> getSoftwareEntities() {
		return softwareEntities;
	}

	public void setSoftwareEntities(List<SoftwareUnit> softwareEntities) {
		this.softwareEntities = softwareEntities;
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
		File other = (File) obj;
		if (sha == null) {
			if (other.sha != null)
				return false;
		} else if (!sha.equals(other.sha))
			return false;
		return true;
	}

}
