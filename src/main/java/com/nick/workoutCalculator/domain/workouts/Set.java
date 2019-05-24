package com.nick.workoutCalculator.domain.workouts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reps",
    "weight"
})
public class Set {

    @JsonProperty("reps")
	private Integer reps = 0;

    @JsonProperty("weight")
	private Integer weight = 0;

    @JsonProperty("reps")
    public Integer getReps() {
		if (reps == null) {
			return 0;
		}
        return reps;
    }

    @JsonProperty("reps")
    public void setReps(Integer reps) {
        this.reps = reps;
    }

    @JsonProperty("weight")
    public Integer getWeight() {
		if (weight == null) {
			return 0;
		}
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
		return new ToStringBuilder(this).append("reps", reps).append("weight", weight).toString();
    }

    @Override
    public int hashCode() {
		return new HashCodeBuilder().append(reps).append(weight).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Set) == false) {
            return false;
        }
        Set rhs = ((Set) other);
		return new EqualsBuilder().append(reps, rhs.reps).append(weight, rhs.weight).isEquals();
    }

}
