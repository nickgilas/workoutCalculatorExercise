package com.nick.workoutCalculator.queryStrategies;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Workout;

import java.util.List;
import java.util.Map;

public class AbbySmithsPersonalRecordBenchPressQuery implements Query<Integer> {

	@Override
	public Integer execute(WorkoutDataStore store) {

		User abby = store.getUserById(5101);

		int exerciseId = store.getExerciseTypeByName(WorkoutDataStore.BACK_SQUAT_NAME).getId();

		// store the highest weight value - this is a way around Java 8 lambda scopes
		Integer[] highestWeight = { 0 };

		Map<User, List<Workout>> data = store.getData();

		data.get(abby).forEach(workout -> {
			workout.getBlocks().forEach(block -> {
				if (exerciseId == block.getExerciseId()) {
					block.getSets().forEach(set -> {
						if (set.getWeight() > highestWeight[0]) {
							highestWeight[0] = set.getWeight();
						}
					});
				}
			});
		});
		return highestWeight[0];
	}

	@Override
	public String getQuery() {
		return "What is Abby Smith's Bench Press PR weight?";
	}

}
