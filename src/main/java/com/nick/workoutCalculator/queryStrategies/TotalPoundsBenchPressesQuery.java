package com.nick.workoutCalculator.queryStrategies;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.domain.exercises.ExerciseType;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Block;
import com.nick.workoutCalculator.domain.workouts.Set;
import com.nick.workoutCalculator.domain.workouts.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Strategy pattern that will query the total amount of pounds that all of the
 * athletes have bench pressed.
 * 
 * @author Nick Gilas
 *
 */
public class TotalPoundsBenchPressesQuery implements Query<Integer> {

	public Integer execute(WorkoutDataStore store) {

		Map<User, List<Workout>> workoutData = store.getData();

		// get the bench press object type
		ExerciseType type = store.getExerciseTypeByName(WorkoutDataStore.BENCH_PRESS_NAME);
		
		// this stores the total weights for all block sets
		List<Integer> totalWeight = new ArrayList<>();

		// iterate the workouts and if the block is the exercise type we are interested
		// in then sum up all of the weights in the set
		workoutData.values().forEach(workoutDataList -> {
			workoutDataList.forEach(workout -> {
				workout.getBlocks().forEach(block -> {

					// is the the exercise we are interested in?
					if (block.getExerciseId().equals(type.getId())) {
						totalWeight.add(store.getSumOfWeightByBlocks(block));
					}
				});
			});
		});

		// sum up all of the weights
		return totalWeight.stream().mapToInt(Integer::intValue).sum();
	}

	@Override
	public String getQuery() {
		return "In total, how many pounds have these athletes Bench Pressed?";
	}

}
