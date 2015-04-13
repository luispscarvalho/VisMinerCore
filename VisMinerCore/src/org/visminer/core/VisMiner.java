package org.visminer.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.jpa.vendor.Database;
import org.visminer.core.analyzer.Analyzer;
import org.visminer.core.config.DBConfig;
import org.visminer.core.config.MetricsConfig;
import org.visminer.core.metric.IMetric;
import org.visminer.core.model.bean.Committer;
import org.visminer.core.model.bean.Repository;

public class VisMiner {

	private Analyzer analyzer;

	public VisMiner() {
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	public void setDbConfig(DBConfig config) {
		Database.setDBConfig(config);
	}

	public void setMetricsClasspath(List<String> classPaths) {
		MetricsConfig.setMetricsClasspath(classPaths);
	}

	public Repository.Business analyze() {

		Repository.Business repository = analyzer.analyzeRepository();
		CalculateMetrics.calculate(repository);
		repository.start();

		return repository;

	}

	public List<Committer.Business> getCommitters() {

		List<Committer.Business> committers = new ArrayList<Committer.Business>();

		for (Committer.Database c : persistenceFacade.getAllCommitters()) {
			Committer.Business committer = (new Committer()).new Business(
					c.getId(), c.getName(), c.getEmail());
			committers.add(committer);
		}
		return committers;

	}

	public List<Repository.Business> getRepositories() {

		List<Repository.Business> repositories = new ArrayList<Repository.Business>();

		for (Repository.Database repo : persistenceFacade.getAllRepositories()) {

			Repository.Business repository = (new Repository()).new Business(
					repo.getId(), repo.getPath(), repo.getName(),
					repo.getRemoteOwner(), repo.getRemoteName(),
					repo.getType(), repo.getRemoteService());

			repositories.add(repository);
		}
		return repositories;

	}

	public List<IMetric.Business> getMetrics() {

		List<IMetric.Business> metrics = new ArrayList<IMetric.Business>();

		for (IMetric.Database m : persistenceFacade.getAllMetrics()) {
			IMetric.Business metric = (new AMetric()).new Business(m.getName(),
					m.getDescription(), m.getId());
			metrics.add(metric);
		}
		return metrics;

	}

	public List<Repository.Business> getRepositoriesByCommitter(
			Committer.Business committer) {

		List<Repository.Business> repositories = new ArrayList<Repository.Business>();

		for (Repository.Database repo : persistenceFacade
				.getRepositoriesByCommitter(committer.getId())) {
			Repository.Business repository = (new Repository()).new Business(
					repo.getId(), repo.getPath(), repo.getName(),
					repo.getRemoteOwner(), repo.getRemoteName(),
					repo.getType(), repo.getRemoteService());

			repositories.add(repository);
		}
		return repositories;

	}

	public Repository.Business getRepository(String path) {

		Repository.Database repo = persistenceFacade.getRepositoryByPath(path);
		Repository.Business repository = (new Repository()).new Business(
				repo.getId(), repo.getPath(), repo.getName(),
				repo.getRemoteOwner(), repo.getRemoteName(), repo.getType(),
				repo.getRemoteService());

		return repository;

	}

}