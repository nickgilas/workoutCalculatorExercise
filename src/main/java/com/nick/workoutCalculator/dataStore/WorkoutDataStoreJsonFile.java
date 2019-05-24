package com.nick.workoutCalculator.dataStore;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.nick.workoutCalculator.FileLoader;
import com.nick.workoutCalculator.domain.exercises.ExerciseType;
import com.nick.workoutCalculator.domain.users.User;
import com.nick.workoutCalculator.domain.workouts.Block;
import com.nick.workoutCalculator.domain.workouts.Set;
import com.nick.workoutCalculator.domain.workouts.Workout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is a repository of workout data which is loaded from json files
 * and converted into domain objects. It also contains query based methods for
 * common retrieval of data as a convenience for callers.
 * 
 * @author Nick Gilas
 *
 */
public class WorkoutDataStoreJsonFile implements WorkoutDataStore {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkoutDataStoreJsonFile.class);

	private static final String EXERCISES = "exercises.json";
	private static final String USERS = "users.json";
	private static final String WORKOUTS = "workouts.json";
	
	// holds all workout data grouped by user
	private Map<User, List<Workout>> data;

	// mapping between userId and user object
	private Map<Integer, User> userMapping;

	// holds all of the distinct exercise types
	private List<ExerciseType> allExerciseTypes;
	private FileLoader fileLoader = new FileLoader();

	public WorkoutDataStoreJsonFile() {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JodaModule());
		objectMapper.setDateFormat(df);
		
		List<User> users = null;
		List<Workout> workouts = null;

		/*
		 * Load all data from json files
		 */
		try {
			String usersJson = fileLoader.getResourceFileContents(USERS);
		
			users = objectMapper.readValue(usersJson, new TypeReference<List<User>>() {
			});
			LOGGER.debug("Loaded users json, count: + " + users.size());

			String workoutsJson = fileLoader.getResourceFileContents(WORKOUTS);
			workouts = objectMapper.readValue(workoutsJson, new TypeReference<List<Workout>>() {
			});
			LOGGER.debug("Loaded workouts json, count: + " + workouts.size());

			String exerciseJson = fileLoader.getResourceFileContents(EXERCISES);
			allExerciseTypes = objectMapper.readValue(exerciseJson, new TypeReference<List<ExerciseType>>() {
			});
			LOGGER.debug("Loaded exercises json, count: + " + allExerciseTypes.size());

		} catch (Exception e) {
			String message = "Exception enountered while loading json data: " + e.getMessage();
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
		
		userMapping = new HashMap<>();

		data = new HashMap<>();
		
		// initialize the internal data map with user keys and empty array of workouts
		users.forEach(user -> {
			data.put(user, new ArrayList<>());
			userMapping.put(user.getId(), user);
		});

		// iterate all workouts and add user workouts data to their bucket in the map
		workouts.forEach(workout -> {
			int userId = workout.getUserId();
			User user = userMapping.get(userId);
			data.get(user).add(workout);
		});

	}

	/* (non-Javadoc)
	 * @see com.nick.workoutCalculator.WorkoutDataStore#getData()
	 */
	@Override
	public Map<User, List<Workout>> getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see com.nick.workoutCalculator.WorkoutDataStore#getExerciseTypeByName(java.lang.String)
	 */
	@Override
	public ExerciseType getExerciseTypeByName(String name) {
		return allExerciseTypes.stream().filter(t -> t.getTitle().equals(name)).findFirst().get();
	}

	/* (non-Javadoc)
	 * @see com.nick.workoutCalculator.WorkoutDataStore#getAllExerciseTypes()
	 */
	@Override
	public List<ExerciseType> getAllExerciseTypes() {
		return allExerciseTypes;
	}

	/* (non-Javadoc)
	 * @see com.nick.workoutCalculator.WorkoutDataStore#getUserById(int)
	 */
	@Override
	public User getUserById(int userId) {
		return userMapping.get(userId);
	}

	/* (non-Javadoc)
	 * @see com.nick.workoutCalculator.WorkoutDataStore#getSumOfWeightByBlocks(com.nick.workoutCalculator.domain.workouts.Block)
	 */
	@Override
	public Integer getSumOfWeightByBlocks(Block block) {
		List<Integer> totals = new ArrayList<>();
		block.getSets().forEach(set -> {
			totals.add(set.getReps() * set.getWeight());
		});
		return totals.stream().mapToInt(Integer::intValue).sum();
	}

}
