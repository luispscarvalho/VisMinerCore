package org.visminer.core.analyzer;

import java.io.IOException;
import java.util.List;

import org.visminer.core.model.Commit;
import org.visminer.core.model.Repository;

public class Analyzer {

	private List<Commit.Database> commits = null;

	public void save(Repository.Database repository,
			IRepositorySystem repositorySystem) {
		repository = RepositoryUpdater.save(repositorySystem, repository);
		commits = CommitsAndCommittersUpdater
				.save(repositorySystem, repository);
		// TODO: inicio de transacao bd aqui(?)
		CommitsAndCommittersUpdater.save(repositorySystem, repository);
		TreeUpdater.save(repositorySystem, repository);
		FilesUpdater.save(repositorySystem, commits, repository);
		CommitedFilesUpdater.save(repositorySystem, commits);
		// TODO: tratamento commit/rollback aqui(?)

		repositorySystem.close();
	}

	public void analyze() throws Exception {
		if (commits == null) {
			throw new Exception("Save(..) must be executed prior to this operation");
		}

		// percorre toda a lista de commits
		// para cada commit, percorre toda a lista de arquivos presentes no
		// commit
		// com o byte[] do arquivo, monta a AST
		// para cada metrica passa a AST para calculo
		// depois do calculo, a metrica devolve uma colecao de software units +
		// valor de metrica
		// verifica se a unit jah nao foi adicionada com o mesmo nome
		// se jah foi adicionada, adiciona mais uma metrica
		// se nao foi adicionada, adiciona o software unit + metrica

	}

}