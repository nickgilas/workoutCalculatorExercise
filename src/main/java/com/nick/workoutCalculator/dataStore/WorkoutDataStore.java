package com.nick.workoutCalculator.dataStore;

import com.nick.workoutCalculator.domain.exercises.ExerciseType;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Block;
import com.nick.workoutCalculator.domain.workouts.Workout;

import java.util.List;
import java.util.Map;

/**
 * Interface to abstract away the true back data store. Defines the common query
 * methods and access to the backing data in object form.
 * 
 * @author Nick Gilas
 *
 */
public interface WorkoutDataStore {

	public static final String BACK_SQUAT_NAME = "Back Squat";
	public static final String BENCH_PRESS_NAME = "Bench Press";

	Map<User, List<Workout>> getData();

	ExerciseType getExerciseTypeByName(String name);

	List<ExerciseType> getAllExerciseTypes();

	User getUserById(int userId);

	Integer getSumOfWeightByBlocks(Block block);

}