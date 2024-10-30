package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Random;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

@Path("/api/numbers")
@Tag(name = "Number REST Endpoint") // for the documentation
public class NumberResource {

    @Inject Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation( // for the documentation
        summary = "Generates book numbers",
        description = "ISBN 13 and ISBN 10 numbers"
    )
    public IsbnNumbers generateIsbnNumbers() {
        IsbnNumbers isbnNumbers = new IsbnNumbers(
            "13-" + new Random().nextInt(100_000_000), 
            "10-" + new Random().nextInt(100_000));

        logger.info("Numbers generated: " + isbnNumbers);
        return isbnNumbers;
    }
}
