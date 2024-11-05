package org.acme;

import java.time.Instant;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

@Schema(description = "Several ISBN numbers for books") // for the documentation
public class IsbnNumbers {
    
    @Schema(required=true) // for documentation
    @JsonbProperty("ISBN_13") // override the property name in the json
    public String isbn13;

    @Schema(required=true) // for documentation
    @JsonbProperty("ISBN_10")
    public String isbn10;

    @JsonbTransient // does not include the property in the json
    public Instant generationDate;

    public IsbnNumbers(String isbn13, String isbn10) {
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
        this.generationDate = Instant.now();
    }

    public IsbnNumbers(String isbn13, String isbn10, Instant generationDate) {
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
        this.generationDate = generationDate;
    }

    @Override
    public String toString() {
        return "IsbnNumbers {ISBN_13=" + isbn13 + ", ISBN_10=" + isbn10 + ", DATE=" + generationDate + "}";
    }    
}
