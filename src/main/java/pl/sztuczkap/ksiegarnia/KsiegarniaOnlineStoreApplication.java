package pl.sztuczkap.ksiegarnia;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sztuczkap.ksiegarnia.catalog.application.CatalogController;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;
import pl.sztuczkap.ksiegarnia.catalog.domain.CatalogService;

import java.util.List;

@SpringBootApplication
public class KsiegarniaOnlineStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsiegarniaOnlineStoreApplication.class, args);
    }
}
