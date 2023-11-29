package com.example.bilbackend.ChatGptDTO;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "index",
        "message",
        "finish_reason"
})
@Generated("jsonschema2pojo")
public class Choice {

    @JsonProperty("index")
    private Integer index;
    @JsonProperty("message")
    private ChatMessage message;
    @JsonProperty("finish_reason")
    private String finishReason;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("index")
    public Integer getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(Integer index) {
        this.index = index;
    }

    @JsonProperty("message")
    public ChatMessage getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    @JsonProperty("finish_reason")
    public String getFinishReason() {
        return finishReason;
    }

    @JsonProperty("finish_reason")
    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Choice{" +
                "index=" + index +
                ", message=" + message +
                ", finishReason='" + finishReason + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

