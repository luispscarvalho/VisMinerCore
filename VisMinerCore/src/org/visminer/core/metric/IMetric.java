package org.visminer.core.metric;

import org.visminer.core.model.bean.SoftwareUnit;
import org.visminer.core.persistence.IMetricPersistance;

public interface IMetric<Target> {

	public void calculate(Target target, SoftwareUnit parentUnit,
			IMetricPersistance persistance);
}
