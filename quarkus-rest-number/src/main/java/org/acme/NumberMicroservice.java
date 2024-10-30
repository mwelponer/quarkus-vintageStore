package org.acme;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
    info = @Info(
        title = "Number Microservice",
        description = "This microservice generates book ISBN numbers.",
        version = "1.0",
        contact = @Contact(name = "@acme", url = "http://www.bla")
    ),
    tags = {
        @Tag(name= "api", description = "Public API that can be ..."),
        @Tag(name = "numbers", description = "Anybody interested in .. ")
    }
)
public class NumberMicroservice extends Application {
    
}
