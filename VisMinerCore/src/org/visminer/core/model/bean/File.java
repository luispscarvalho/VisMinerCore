package org.visminer.core.model.bean;

public class File {
	
	private int id;
	private String name;
	private String path;
	private String uid;
	
	public File(){}
	
	public File(int id, String name, String path, String uid) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.uid = uid;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
