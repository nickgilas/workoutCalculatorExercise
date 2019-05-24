package com.nick.workoutCalculator.queryStrategies;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Workout;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthOf2017BarryMooreBackSquatMostWeightQuery implements Query<Integer> {

	private static final int TARGET_YEAR = 2017;

	@Override
	public Integer execute(WorkoutDataStore store) {
		// key = month #, value = total weight
		Map<Integer, Integer> monthWeightMap = new HashMap<>();

		Map<User, List<Workout>> workoutData = store.getData();

		User berry = store.getUserById(22677);

		int exerciseId = store.getExerciseTypeByName(WorkoutDataStore.BACK_SQUAT_NAME).getId();

		List<Workout> workouts = workoutData.get(berry);

		// iterate the workouts and parse out data for target year and exercise
		workouts.forEach(workout -> {
			if (isYear2017(workout.getDatetimeCompleted())) {
				// matching year, check the exercise
				Integer month = workout.getDatetimeCompleted().getMonthOfYear();
				workout.getBlocks().forEach(block -> {
					if (exerciseId == block.getExerciseId()) {
						if (monthWeightMap.containsKey(month)) {
							Integer total = monthWeightMap.get(month) + store.getSumOfWeightByBlocks(block);
							monthWeightMap.put(month, total);
						} else {
							monthWeightMap.put(month, store.getSumOfWeightByBlocks(block));
						}
					}
				});
			}
		});

		// get the largest value (weight) in the map
		Integer maxEntry = monthWeightMap.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
		return maxEntry;
	}

	private boolean isYear2017(DateTime datetimeCompleted) {
		return TARGET_YEAR == datetimeCompleted.getYear();
	}

	@Override
	public String getQuery() {
		return "In what month of 2017 did Barry Moore Back Squat the most total weight?";
	}

}
