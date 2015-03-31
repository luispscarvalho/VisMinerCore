package org.visminer.core.model;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.visminer.core.constant.TreeType;
import org.visminer.core.persistence.dao.CommitDAO;
import org.visminer.core.persistence.dao.CommitterDAO;
import org.visminer.core.persistence.dao.TreeDAO;
import org.visminer.core.utility.StringUtils;

public class Repository {

	public class Business {

		private int id;
		private String path;
		private String name;
		private String remoteOwner;
		private String remoteName;
		private int type;
		private int remoteType;

		private List<Committer.Business> committers;
		private List<Commit.Business> commits;
		private List<Tree.Business> trees;
		private List<File.Business> files;

		private int currentCommitIndex;
		private Commit.Business currentCommit;
		private Tree.Business currentTree;

		public void start() {
			TreeDAO treeDao = new TreeDAO();
			CommitterDAO committerDao = new CommitterDAO();

			List<Tree.Database> treesDb = treeDao.getByRepository(id);
			List<Committer.Database> committersDb = committerDao
					.getByRepository(id);

			this.trees = new ArrayList<Tree.Business>(treesDb.size());
			this.committers = new ArrayList<Committer.Business>(
					committersDb.size());

			for (Tree.Database t : treesDb) {
				Tree.Business tree = (new Tree()).new Business(t.getId(),
						t.getName(), t.getFullName(), t.getType());
				trees.add(tree);
			}

			for (Committer.Database c : committersDb) {
				Committer.Business committer = (new Committer()).new Business(
						c.getId(), c.getName(), c.getEmail());
				committers.add(committer);
			}

			setTree("master", TreeType.BRANCH);

		}

		public boolean setTree(String name, TreeType type) {

			for (Tree.Business t : this.trees) {
				if (t.getName().equals(name) && t.getType() == type) {
					this.currentTree = t;
					updateTree();
					return true;
				}
			}

			return false;

		}

		public boolean previousCommit() {

			if (this.currentCommitIndex == 1)
				return false;
			this.currentCommitIndex--;
			this.currentCommit = this.commits.get(currentCommitIndex - 1);
			updateProject();
			return true;

		}

		public boolean nextCommit() {

			if (this.currentCommitIndex == this.commits.size())
				return false;
			this.currentCommitIndex++;
			this.currentCommit = this.commits.get(currentCommitIndex - 1);
			updateProject();
			return true;

		}

		public void firstCommit() {
			this.currentCommitIndex = 1;
			this.currentCommit = this.commits.get(currentCommitIndex - 1);
			updateProject();
		}

		public void lastCommit() {
			this.currentCommitIndex = this.commits.size();
			this.currentCommit = this.commits.get(currentCommitIndex - 1);
			updateProject();
		}

		private void updateTree() {

			CommitDAO dao = new CommitDAO();

			List<Commit.Database> commitsDb = dao
					.getByTree(currentTree.getId());
			this.commits = new ArrayList<Commit.Business>(commitsDb.size());

			for (Commit.Database c : commitsDb) {
				Commit.Business commit = (new Commit()).new Business(c.getId(),
						c.getName(), c.getMessage(), c.getDate());
				commits.add(commit);
			}

			currentCommitIndex = commits.size();
			currentCommit = commits.get(currentCommitIndex - 1);
			updateProject();

		}

		private void updateProject() {

			files = new ArrayList<File.Business>();
			for (int i = 0; i < currentCommitIndex; i++) {
				for (File.Business f : this.commits.get(i).getFiles()) {
					if (this.files.contains(f) && f.isDeleted())
						files.remove(f);
					else if (!this.files.contains(f))
						this.files.add(f);
				}
			}

		}

		public Business() {
		}

		public Business(int id, String path, String name, String remoteOwner,
				String remoteName, int type, int remoteType) {
			super();
			this.id = id;
			this.path = path;
			this.name = name;
			this.remoteOwner = remoteOwner;
			this.remoteName = remoteName;
			this.type = type;
			this.remoteType = remoteType;
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRemoteOwner() {
			return remoteOwner;
		}

		public void setRemoteOwner(String remote_owner) {
			this.remoteOwner = remote_owner;
		}

		public String getRemoteName() {
			return remoteName;
		}

		public void setRemoteName(String remote_name) {
			this.remoteName = remote_name;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getRemoteType() {
			return remoteType;
		}

		public void setRemoteType(int remoteType) {
			this.remoteType = remoteType;
		}

		public List<Committer.Business> getCommitters() {
			return committers;
		}

		public List<Commit.Business> getCommits() {
			return commits;
		}

		public List<Tree.Business> getTrees() {
			return trees;
		}

		public List<File.Business> getFiles() {
			return files;
		}

		public void setFiles(List<File.Business> files) {
			this.files = files;
		}

		public Commit.Business getCurrentCommit() {
			return currentCommit;
		}

		public void setCurrentCommit(Commit.Business currentCommit) {
			this.currentCommit = currentCommit;
			updateProject();
		}

		public Tree.Business getCurrentTree() {
			return currentTree;
		}

		public void setCurrentTree(Tree.Business currentTree) {
			this.currentTree = currentTree;
			updateTree();
		}

	}

	@Entity
	@Table(name = "repository")
	@NamedQuery(name = "Repository.findAll", query = "SELECT r FROM Repository r")
	public class Database implements Serializable {
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
		private List<Committer.Database> committers;

		// bi-directional many-to-one association to Tree
		@OneToMany(mappedBy = "repository")
		private List<Tree.Database> trees;

		public Database() {
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

		public List<Committer.Database> getCommitters() {
			return this.committers;
		}

		public void setCommitters(List<Committer.Database> committers) {
			this.committers = committers;
		}

		public List<Tree.Database> getTrees() {
			return this.trees;
		}

		public void setTrees(List<Tree.Database> trees) {
			this.trees = trees;
		}

		public Tree.Database addTree(Tree.Database tree) {
			getTrees().add(tree);
			tree.setRepository(this);

			return tree;
		}

		public Tree.Database removeTree(Tree.Database tree) {
			getTrees().remove(tree);
			tree.setRepository(null);

			return tree;
		}

		public void getValuesOf(Repository.Business repository) {

			this.name = repository.getName();
			this.path = repository.getPath();
			this.remoteName = repository.getRemoteName();
			this.remoteOwner = repository.getRemoteOwner();
			this.remoteService = repository.getRemoteType();
			this.type = repository.getType();
			this.sha = StringUtils.sha1(this.path);

		}

	}

	public static Business fromDatabase(Database db) {

		Business biz = (new Repository()).new Business(db.getId(),
				db.getPath(), db.getName(), db.getRemoteOwner(),
				db.getRemoteName(), db.getType(), db.getRemoteService());
		return biz;

	}

}
