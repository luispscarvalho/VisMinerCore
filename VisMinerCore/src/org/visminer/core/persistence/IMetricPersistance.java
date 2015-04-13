package org.visminer.core.persistence;

import org.visminer.core.model.bean.SoftwareUnit;

public interface IMetricPersistance {

	public void save(SoftwareUnit unit, Integer value);

}
