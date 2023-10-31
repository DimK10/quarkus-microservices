package org.agoncal.quarkus.microservices.number;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class IsbnNumbers {

    @JsonbProperty("isbn_13")
    private String isbn13;

    @JsonbProperty("isbn_10")
    private String isbn10;

    @JsonbTransient
    private Instant generationTime;

}
