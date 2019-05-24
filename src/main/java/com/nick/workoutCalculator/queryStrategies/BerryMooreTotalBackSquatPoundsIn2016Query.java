package com.nick.workoutCalculator.queryStrategies;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Workout;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Query to calculate the total number of pounds back squat by Berry Moore Back
 * back in the year 2016.
 * 
 * @author Nick Gilas
 *
 */
public class BerryMooreTotalBackSquatPoundsIn2016Query implements Query<Integer> {

	private static int TARGET_YEAR = 2016;

	@Override
	public Integer execute(WorkoutDataStore store) {

		Map<User, List<Workout>> workoutData = store.getData();

		User berry = store.getUserById(22677);
		
		int exerciseId = store.getExerciseTypeByName(WorkoutDataStore.BACK_SQUAT_NAME).getId();

		List<Workout> workouts = workoutData.get(berry);

		List<Integer> totalWeights = new ArrayList<>();

		// iterate the workouts and parse out data for target year and exercise
		workouts.forEach(workout -> {
			if (isYear2016(workout.getDatetimeCompleted())) {
				// matching year, check the exercise
				workout.getBlocks().forEach(block -> {
					if (exerciseId == block.getExerciseId()) {
						totalWeights.add(store.getSumOfWeightByBlocks(block));
					}
				});
			}
		});

		// sum up all of the weights
		int total = totalWeights.stream().mapToInt(Integer::intValue).sum();

		return total;
	}

	private boolean isYear2016(DateTime datetimeCompleted) {
		return TARGET_YEAR == datetimeCompleted.getYear();
	}

	@Override
	public String getQuery() {
		return "How many pounds did Barry Moore Back Squat in 2016?";
	}

}
