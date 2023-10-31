package org.agoncal.quarkus.microservices.number;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class IsbnNumbers {

    private String isbn13;

    private String isbn10;

    private Instant generationTime;

}
