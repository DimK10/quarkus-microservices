package org.agoncal.quarkus.microservices.book;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST endpoint")
public class BookResource {

    @RestClient
    NumberProxy numberProxy;

    @Inject
    Logger logger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(
            summary = "Creates a Book",
            description = "Creates a Book with an ISBN number")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallingBackOnCreatingABook")
    public Response createABook(@FormParam("title") String title,
                                @FormParam("author") String author,
                                @FormParam("year") int yearOfPublication,
                                @FormParam("genre") String genre) {

        Book book = Book.builder()
                .isbn13(numberProxy.generateIsbnNumbers().getIsbn13())
                .title(title)
                .author(author)
                .yearOfPublication(yearOfPublication)
                .genre(genre)
                .creationDate(Instant.now())
                .build();

        logger.info("Book created: " + book);

        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    public Response fallingBackOnCreatingABook(String title,
                                               String author,
                                               int yearOfPublication,
                                               String genre) throws FileNotFoundException {

        Book book = Book.builder()
                .isbn13("Will be set later")
                .title(title)
                .author(author)
                .yearOfPublication(yearOfPublication)
                .genre(genre)
                .creationDate(Instant.now())
                .build();

        saveBookOnDisk(book);
        logger.warn("Book saved on disk : " + book);

        return Response.status(Response.Status.PARTIAL_CONTENT).entity(book).build();
    }

    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);
        try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        }
    }
}
