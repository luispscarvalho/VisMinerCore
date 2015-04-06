package org.visminer.core.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.visminer.core.annotations.VisMinerMetric;
import org.visminer.core.metric.IMetric;
import org.visminer.core.model.bean.Metric;
import org.visminer.core.model.database.MetricDB;
import org.visminer.core.persistence.dao.MetricDAO;

public class MetricsConfig {

	private static Map<Metric, IMetric<?>> metrics = new HashMap<Metric, IMetric<?>>();

	public static void setMetricsClasspath(List<String> classPaths) {
		for (String classPath : classPaths) {
			try {
				IMetric<?> metric = (IMetric<?>) Class.forName(classPath)
						.newInstance();
				metrics.put(getMetric(metric), metric);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static Metric getMetric(IMetric<?> metric) {
		MetricDAO dao = new MetricDAO();

		VisMinerMetric annotations = metric.getClass().getAnnotation(
				VisMinerMetric.class);

		if (annotations.on()) {

			MetricDB metricDb = dao.getByName(annotations.name());
			if (metricDb == null) {
				metricDb = new MetricDB();
				metricDb.setDescription(annotations.description());
				metricDb.setName(annotations.name());

				metricDb = dao.save(metricDb);
			}

			Metric metricBiz = new Metric(metricDb.getName(),
					metricDb.getDescription(), metricDb.getId());

			return metricBiz;
		}

		return null;
	}

	public static Map<Metric, IMetric<?>> getMetrics() {
		return metrics;
	}

}
