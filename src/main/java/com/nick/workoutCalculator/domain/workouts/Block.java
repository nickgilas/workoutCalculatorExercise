package com.nick.workoutCalculator.domain.workouts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.nick.workoutCalculator.domain.exercises.ExerciseType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "exercise_id", "sets" })
public class Block {

	@JsonProperty("exercise_id")
	private Integer exerciseId;

	@JsonProperty("sets")
	private List<Set> sets = null;

	@JsonProperty("exercise_id")
	public Integer getExerciseId() {
		return exerciseId;
	}

	@JsonProperty("exercise_id")
	public void setExerciseId(Integer exerciseId) {
		this.exerciseId = exerciseId;
	}

	@JsonProperty("sets")
	public List<Set> getSets() {
		return sets;
	}

	@JsonProperty("sets")
	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("exerciseId", exerciseId).append("sets", sets).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sets).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Block) == false) {
			return false;
		}
		Block rhs = ((Block) other);
		return new EqualsBuilder().append(exerciseId, rhs.exerciseId).append(sets, rhs.sets).isEquals();
	}

}
