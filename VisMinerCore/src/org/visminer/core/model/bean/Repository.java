package org.visminer.core.model.bean;

import org.visminer.core.constant.RepositoryType;

public class Repository {

	private int id;
	private String description;
	private String name;
	private String path;
	private String remoteUrl;
	private RepositoryType type;
	private String uid;
	
	public Repository(){}
	
	public Repository(int id, String description, String name, String path,
			String remoteUrl, RepositoryType type, String uid) {
		super();
		this.id = id;
		this.description = description;
		this.name = name;
		this.path = path;
		this.remoteUrl = remoteUrl;
		this.type = type;
		this.uid = uid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public RepositoryType getType() {
		return type;
	}

	public void setType(RepositoryType type) {
		this.type = type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}	
	
}
