package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Random;

import org.jboss.logging.Logger;

@Path("/api/numbers")
public class NumberResource {

    @Inject Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public IsbnNumbers generateIsbnNumbers() {
        IsbnNumbers isbnNumbers = new IsbnNumbers(
            "13-" + new Random().nextInt(100_000_000), 
            "10-" + new Random().nextInt(100_000));

        logger.info("Numbers generated: " + isbnNumbers);
        return isbnNumbers;
    }
}
