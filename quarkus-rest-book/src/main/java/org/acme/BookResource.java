package org.acme;



import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.Instant;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/api/books")
public class BookResource {

    @RestClient NumberProxy proxy;
    @Inject Logger logger;

    @Operation(summary = "Creates a new book") // for documentation
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // takes form data
    public Response createBook(
            @FormParam("title") String title, 
            @FormParam("author") String author, 
            @FormParam("year") int yearOfPublication, 
            @FormParam("genre") String genre) 
    {

        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumbers().isbn;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        
        logger.info("book created: " + book);

        // 201 means created!
        return Response.status(201).entity(book).build();
    }
}
