package com.nick.workoutCalculator.queryStrategies;

import static org.junit.jupiter.api.Assertions.*;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonthOf2017BarryMooreBackSquatMostWeightQueryTest {

	private MonthOf2017BarryMooreBackSquatMostWeightQuery query;
	private WorkoutDataStore store;

	@BeforeEach
	void setUp() throws Exception {
		store = new WorkoutDataStoreJsonFile();
		query = new MonthOf2017BarryMooreBackSquatMostWeightQuery();
	}

	@Test
	void testExecute() {
		int month = query.execute(store);
		assertEquals(3, month, "Incorrect month returned from query");
	}

}
