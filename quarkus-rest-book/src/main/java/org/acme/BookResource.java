package org.acme;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Path("/api/books")
public class BookResource {

    @RestClient NumberProxy proxy;
    @Inject Logger logger;

    @Operation(
        summary = "Creates a new book",
        description = "Creates a book with an ISBN number"    
    ) // for documentation
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // takes form data
    @Retry(
        //retryOn = ,
        maxRetries = 3,
        delay = 500 // wait 500ms between each try
        //maxDuration = ,
        //abortOn = 
    )
    @Fallback(
        fallbackMethod = "fallingbackOnCreateBook"
        //applyOn = ,
        //skipOn = 
    )
    public Response createBook(
            @FormParam("title") String title, 
            @FormParam("author") String author, 
            @FormParam("year") int yearOfPublication, 
            @FormParam("genre") String genre) {

        Book book = new Book();
        book.isbn13 = proxy.generateIsbnNumbers().isbn; //"13-we get it later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        
        logger.info("book created: " + book);

        // 201 means created!
        return Response.status(201).entity(book).build();
    }

    public Response fallingbackOnCreateBook (
        String title, 
        String author, 
        int yearOfPublication, 
        String genre) throws FileNotFoundException {

    Book book = new Book();
    book.isbn13 = "Will be set later";
    book.title = title;
    book.author = author;
    book.yearOfPublication = yearOfPublication;
    book.genre = genre;
    book.creationDate = Instant.now();

    saveBookOnDisk(book);
        
        logger.warn("book saved on disk: " + book);
    
        // 206 means partially created!
        return Response.status(206).entity(book).build();
    }
    
    private void saveBookOnDisk(Book book) throws FileNotFoundException {
        String bookJson = JsonbBuilder.create().toJson(book);

        try (PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(bookJson);
        };
    }
}
