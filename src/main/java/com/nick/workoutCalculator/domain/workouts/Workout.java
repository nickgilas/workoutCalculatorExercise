package com.nick.workoutCalculator.domain.workouts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nick.workoutCalculator.CustomDateDeserializer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "user_id", "datetime_completed", "blocks" })
public class Workout {

	@JsonProperty("user_id")
	private Integer userId;

	@JsonProperty("datetime_completed")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private DateTime datetimeCompleted;

	@JsonProperty("blocks")
	private List<Block> blocks = null;

	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@JsonProperty("datetime_completed")
	public DateTime getDatetimeCompleted() {
		return datetimeCompleted;
	}

	@JsonProperty("datetime_completed")
	public void setDatetimeCompleted(DateTime datetimeCompleted) {
		this.datetimeCompleted = datetimeCompleted;
	}

	@JsonProperty("blocks")
	public List<Block> getBlocks() {
		return blocks;
	}

	@JsonProperty("blocks")
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("userId", userId).append("datetimeCompleted", datetimeCompleted).append("blocks", blocks).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(datetimeCompleted).append(userId).append(blocks).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Workout) == false) {
			return false;
		}
		Workout rhs = ((Workout) other);
		return new EqualsBuilder().append(datetimeCompleted, rhs.datetimeCompleted).append(userId, rhs.userId).append(blocks, rhs.blocks).isEquals();
	}

}
