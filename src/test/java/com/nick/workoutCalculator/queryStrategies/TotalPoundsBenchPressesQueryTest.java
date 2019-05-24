package com.nick.workoutCalculator.queryStrategies;

import static org.junit.jupiter.api.Assertions.*;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalPoundsBenchPressesQueryTest {

	private TotalPoundsBenchPressesQuery query;
	private static WorkoutDataStore store;

	@BeforeAll
	public static void beforeAll() throws Exception {
		store = new WorkoutDataStoreJsonFile();
	}

	@BeforeEach
	void setUp() throws Exception {
		query = new TotalPoundsBenchPressesQuery();
	}

	@Test
	void testExecute() {
		int result = query.execute(store);
		assertEquals(5548625, result, "Incorrect number of total pounds that were bench pressed by all athletes");
	}

}
