package org.agoncal.quarkus.microservices.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

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
}
