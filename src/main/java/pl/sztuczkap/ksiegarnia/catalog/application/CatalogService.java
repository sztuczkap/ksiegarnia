package pl.sztuczkap.ksiegarnia.catalog.application;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;
import pl.sztuczkap.ksiegarnia.catalog.domain.CatalogRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class CatalogService implements CatalogUseCase {

    private CatalogRepository repository;

    public CatalogService(@Qualifier("schoolCatalogRepository") CatalogRepository repository) {
        this.repository = repository;
    }

    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }

    public List<Book> findByAuthor(String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getAuthor().startsWith(author))
                .collect(Collectors.toList());
    }

    public List<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return Optional.empty();
    }

    public void addBook(){

    }

    @Override
    public void removeBook(Long id) {

    }

    public void removeById(Long id){

    }

    public void updateBook(){

    }

}
