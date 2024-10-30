package org.acme;
import java.time.Instant;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;

public class Book {

    @JsonbProperty("ISBN_13")
    public String isbn13;
    public String title;
    public String author;
    @JsonbProperty("year_of_publication")
    public int yearOfPublication;
    public String genre;

    @JsonbDateFormat("yyy/MM/dd") // format the date to a specific date format
    @JsonbProperty("creation_date")
    public Instant creationDate;

    @Override
    public String toString() {
        return "Book [isbn13=" + isbn13 + ", title=" + title + ", author=" + author + ", yearOfPublication="
                + yearOfPublication + ", genre=" + genre + ", creationDate=" + creationDate + "]";
    }
}
