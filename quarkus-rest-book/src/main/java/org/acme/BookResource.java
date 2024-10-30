package org.acme;



import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.Instant;

import org.jboss.logging.Logger;

@Path("/api/books")
public class BookResource {

    @Inject Logger logger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // takes form data
    public Response createBook(
            @FormParam("title") String title, 
            @FormParam("author") String author, 
            @FormParam("year") int yearOfPublication, 
            @FormParam("genre") String genre) {

        Book book = new Book();
        book.isbn13 = "we get it later";
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
