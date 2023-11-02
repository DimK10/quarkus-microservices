package org.agoncal.quarkus.microservices.book;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Builder;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Data
@Builder
@Schema(description = "This is a book")
public class Book {

    @JsonbProperty("isbn_13")
    @Schema(required = true)
    private String isbn13;
    @Schema(required = true)
    private String title;
    private String author;
    @JsonbProperty("year_of_publication")
    private int yearOfPublication;
    private String genre;
    @JsonbDateFormat("yyyy-MM-dd")
    @JsonbProperty("creation_date")
    @Schema(implementation = String.class, format = "date")
    private Instant creationDate;
}
