package com.nick.workoutCalculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nick.workoutCalculator.dataStore.WorkoutDataStore;
import com.nick.workoutCalculator.dataStore.WorkoutDataStoreJsonFile;
import com.nick.workoutCalculator.queryStrategies.AbbySmithsPersonalRecordBenchPressQuery;
import com.nick.workoutCalculator.queryStrategies.BerryMooreTotalBackSquatPoundsIn2016Query;
import com.nick.workoutCalculator.queryStrategies.MonthOf2017BarryMooreBackSquatMostWeightQuery;
import com.nick.workoutCalculator.queryStrategies.Query;
import com.nick.workoutCalculator.queryStrategies.TotalPoundsBenchPressesQuery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command Line Interface - the main executable class for querying workout data
 * and outputting the results in json.
 * 
 * @author Nick Gilas
 *
 */
public class WorkoutCalculatorCLI {

	private WorkoutDataStore dataStore;
	private ObjectMapper mapper;

	public WorkoutCalculatorCLI() throws Exception {
		dataStore = new WorkoutDataStoreJsonFile();
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	/**
	 * Calculates and captures all queries and their answers and outputs to the
	 * console in json format
	 * 
	 * @throws Exception
	 *             if there are any exceptions encountered during processing
	 */
	public void executeQueries() throws Exception {
		// key = question, value = answer
		Map<String, Object> results = new HashMap<>();

		getAllQueries().forEach(query -> {
			results.put(query.getQuery(), query.execute(dataStore));
		});

		// output the results in json format to the console/standard out
		System.out.println(mapper.writeValueAsString(results));
	}

	/**
	 * Factory method to retrieve all queries
	 * 
	 * @return a listing of {@link Query} implementations.
	 */
	protected List<Query<?>> getAllQueries() {
		return Arrays.asList(
				new TotalPoundsBenchPressesQuery(), 
				new BerryMooreTotalBackSquatPoundsIn2016Query(), 
				new MonthOf2017BarryMooreBackSquatMostWeightQuery(),
				new AbbySmithsPersonalRecordBenchPressQuery());
	}

	/**
	 * Main entry point to the application. No arguments are required.
	 * 
	 * @param args
	 *            - none
	 * @throws Exception
	 *             if there are any errors while processing
	 */
	public static void main(String[] args) throws Exception {

		WorkoutCalculatorCLI cli = new WorkoutCalculatorCLI();
		cli.executeQueries();

	}
}
