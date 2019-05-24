package com.nick;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Workout;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class WorkoutDataStoreTest {

	private static WorkoutDataStore store;

	@BeforeAll
	public static void beforeAll() throws Exception {
		store = new WorkoutDataStoreJsonFile();
	}

	@Test
	void testWorkoutDataStoreGetData() {

		// EXECUTE TEST
		Map<User, List<Workout>> data = store.getData();

		// ASSERT RESULTS
		assertNotNull(data);

		assertEquals(5, data.size(), "Data store has incorrect number of users");

		// get all of the workouts into a single list
		List<Workout> allExercisesWithBlocks = new ArrayList<>();
		for (List<Workout> bs : data.values()) {
			allExercisesWithBlocks.addAll(bs);
		}

		assertEquals(1200, allExercisesWithBlocks.size(), "Data store has incorrect number of exercises with blocks");
	}

}
