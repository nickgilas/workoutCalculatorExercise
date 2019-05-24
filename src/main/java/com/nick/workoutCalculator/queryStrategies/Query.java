package com.nick.workoutCalculator.queryStrategies;

import com.nick.workoutCalculator.WorkoutCalculatorCLI;
import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;

/**
 * Interface that defines methods which all concrete implementations need for
 * query and answer output.
 * 
 * The design is based on the strategy pattern where this framework can execute
 * any query in the same generic way and output different results based on the
 * individual implementations. New queries can be easily added simply by
 * creating new concrete class and providing custom query logic.
 * 
 * There is a factory method <code>getAllQueries</code>found in the
 * {@link WorkoutCalculatorCLI} which new implementations can be added to which
 * will get automatically executed by the framework.
 * 
 * @author Nick Gilas
 *
 * @param <T>
 *            represents the generic return data type for the
 *            {@link Query#execute(WorkoutDataStoreJsonFile)} method
 */
public interface Query<T> {

	T execute(WorkoutDataStore store);

	String getQuery();
}
