package com.nick.workoutCalculator.queryStrategies;

import com.nick.workoutCalculator.dataStore.WorkoutDataStore;

public abstract class AbstractUserQuery<T> implements Query<T> {

	public T execute(WorkoutDataStore store, int userId) {
		// get user data from store 
		
		return filterUserData(userSpecificData);
	}

	public abstract T filterUserData(userSpecificData);


}
