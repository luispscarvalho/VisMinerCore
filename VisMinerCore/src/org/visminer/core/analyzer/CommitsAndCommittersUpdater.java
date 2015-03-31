package org.visminer.core.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.visminer.core.model.Commit;
import org.visminer.core.model.Committer;
import org.visminer.core.model.Repository;
import org.visminer.core.persistence.dao.CommitDAO;
import org.visminer.core.persistence.dao.CommitterDAO;

public class CommitsAndCommittersUpdater {

	public static List<Commit> save(IRepositorySystem repositorySystem,
			Repository.Database repository) {
		List<Commit.Business> commits = repositorySystem.getCommits();
		List<Committer.Business> committers = new ArrayList<Committer.Business>();

		List<Committer> listAux = new ArrayList<Committer>();
		CommitterDAO committerDAO = new CommitterDAO();
		CommitDAO commitDAO = new CommitDAO();

		for (Commit.Business commit : commits) {

			Committer.Business committer = commit.getCommitter();
			int index = committers.indexOf(committer);

			if (index > -1) {
				commit.setCommitter(committers.get(index));
				continue;
			}

			Committer committerAux = committerDAO.getByEmail(committer
					.getEmail());

			if (committerAux != null) {
				committers.add(committerAux);
				commit.setCommitter(committerAux);
				continue;
			}

			committers.add(committer);
			listAux.add(committer);

		}

		committerDAO.batchSave(listAux);
		commitDAO.batchSave(commits);
		saveContributors(repositorySystem, committers, repository);

		return commits;
	}

	private static void saveContributors(IRepositorySystem repositorySystem,
			List<Committer> committers, Repository repository) {
		Set<Committer> committersAux = repositorySystem.getContributors();
		List<CommitterContributesRepository> listAux = new ArrayList<CommitterContributesRepository>();

		for (Committer committer : committers) {

			CommitterContributesRepositoryPK id = new CommitterContributesRepositoryPK(
					committer.getId(), repository.getId());
			CommitterContributesRepository object = new CommitterContributesRepository(
					id);

			if (committersAux.contains(committer)) {
				object.setContributor(true);
			} else {
				object.setContributor(false);
			}
			listAux.add(object);
		}

		CommitterContributesRepositoryDAO dao = new CommitterContributesRepositoryDAO();
		dao.batchSave(listAux);
	}

}
