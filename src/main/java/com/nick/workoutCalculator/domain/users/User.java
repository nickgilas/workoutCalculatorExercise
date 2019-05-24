package com.nick.workoutCalculator.domain.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name_first",
    "name_last"
})
public class User {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name_first")
    private String nameFirst;

    @JsonProperty("name_last")
    private String nameLast;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name_first")
    public String getNameFirst() {
        return nameFirst;
    }

    @JsonProperty("name_first")
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    @JsonProperty("name_last")
    public String getNameLast() {
        return nameLast;
    }

    @JsonProperty("name_last")
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("nameFirst", nameFirst).append("nameLast", nameLast).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(nameLast).append(nameFirst).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(id, rhs.id).append(nameLast, rhs.nameLast).append(nameFirst, rhs.nameFirst).isEquals();
    }

}
