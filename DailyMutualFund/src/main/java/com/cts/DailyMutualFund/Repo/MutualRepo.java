package com.cts.DailyMutualFund.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.cts.DailyMutualFund.Entity.Mutual;

public interface MutualRepo extends CrudRepository<Mutual,Integer> {
	Mutual findByMfName(String name);
}
