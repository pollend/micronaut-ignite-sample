package org.pollend.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

public class Pet implements Serializable {
    @JsonProperty("name")
    @QuerySqlField
    public String name;

    @JsonProperty("breed")
    @QuerySqlField
    public String breed;

    @JsonProperty("id")
    @QuerySqlField
    public long id;
}
