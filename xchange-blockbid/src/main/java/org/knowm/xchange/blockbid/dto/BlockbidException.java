package org.knowm.xchange.blockbid.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockbidException extends RuntimeException {

    @JsonProperty("message")
    private String message;

    public BlockbidException(@JsonProperty("message") String message) {

        super();
        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}
