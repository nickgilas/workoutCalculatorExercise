package com.nick.workoutCalculator.queryStrategies;

import static org.junit.jupiter.api.Assertions.*;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbbySmithsPersonalRecordBenchPressQueryTest {

	private AbbySmithsPersonalRecordBenchPressQuery query;
	private WorkoutDataStore store;

	@BeforeEach
	void setUp() throws Exception {
		store = new WorkoutDataStoreJsonFile();
		query = new AbbySmithsPersonalRecordBenchPressQuery();
	}

	@Test
	void testExecute() {
		int result = query.execute(store);
		assertEquals(350, result);
	}

}
