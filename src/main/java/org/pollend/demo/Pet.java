package org.pollend.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Pet implements Serializable {
    @JsonProperty("name")
    public String name;
    @JsonProperty("breed")
    public String breed;
    @JsonProperty("id")
    public long id;
}
