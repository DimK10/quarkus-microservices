package org.agoncal.quarkus.microservices.book;

import jakarta.json.bind.annotation.JsonbProperty;
import lombok.Data;

@Data
public class IsbnThirteen {
    @JsonbProperty("isbn_13")
    private String isbn13;
}
