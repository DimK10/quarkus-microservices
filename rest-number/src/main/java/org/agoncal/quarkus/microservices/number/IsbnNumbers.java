package org.agoncal.quarkus.microservices.number;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
@Schema(description = "Several ISBN numbers for books")
public class IsbnNumbers {

    @Schema(required = true)
    @JsonbProperty("isbn_13")
    private String isbn13;

    @Schema(required = true)
    @JsonbProperty("isbn_10")
    private String isbn10;

    @JsonbTransient
    private Instant generationTime;

}
