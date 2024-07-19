package com.kapasiya.springsecurity.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Book Api",
                description = "CRUD Operation",
                summary = "This book-api will 1. Create, 2. Read, 3. Update, 4. Delete ",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "KapasiyaG1",
                        email = "support.kapasiya@gmail.com"
                ),
                version = "1.0.1"
        ),
        servers = {
                @Server(
                        description = "Development",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Testing",
                        url = "http://localhost:8081"
                )
        }
)
public class OpenApiConfig
{

}
