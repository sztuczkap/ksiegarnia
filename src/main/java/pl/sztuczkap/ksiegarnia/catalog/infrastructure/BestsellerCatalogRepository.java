package pl.sztuczkap.ksiegarnia.catalog.infrastructure;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;
import pl.sztuczkap.ksiegarnia.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class BestsellerCatalogRepository implements CatalogRepository {
    private final Map<Long, Book> storage = new ConcurrentHashMap<>();

    public BestsellerCatalogRepository() {
        storage.put(1L, new Book(1L, "Harry Poter i Komnata Tajemnic", "JK Rowling", 1998));
        storage.put(2L, new Book(2L, "Władca pierścieni: Dwie Wieże", "JRR Tolkien", 1954));
        storage.put(3L, new Book(3L, "Mężczyźni, którzy nienawidza kobiet", "Stieg Larson", 2005));
        storage.put(4L, new Book(4L, "Sezon Burz", "Andrzej Sapkowski", 2013));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
}
