package org.agoncal.quarkus.microservices.number;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.Random;

@Path("/api/numbers")
public class NumberResource {

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IsbnNumbers generateIsbnNumbers() {

        IsbnNumbers isbnNumbers =
                IsbnNumbers.builder()
                .isbn13("13-" + new Random().nextInt(100_000_000))
                .isbn10("10-" + new Random().nextInt(100_000_000))
                .generationTime(Instant.now())
                .build();

        logger.info("Numbers generated " + isbnNumbers);

        return isbnNumbers;
    }
}
