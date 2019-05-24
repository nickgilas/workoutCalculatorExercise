package com.nick.workoutCalculator.queryStrategies;

import static org.junit.jupiter.api.Assertions.*;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BerryMoreTotalBackSquatPoundsIn2016QueryTest {

	private BerryMooreTotalBackSquatPoundsIn2016Query query;
	private WorkoutDataStore store;

	@BeforeEach
	void setUp() throws Exception {
		query = new BerryMooreTotalBackSquatPoundsIn2016Query();
		store = new WorkoutDataStoreJsonFile();
	}

	@Test
	void testExecute() {
		int result = query.execute(store);
		assertEquals(512935, result, "Incorrect number of pounds returned for query");
	}

}
