package pl.sztuczkap.ksiegarnia.catalog.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sztuczkap.ksiegarnia.catalog.application.port.CatalogUseCase;
import pl.sztuczkap.ksiegarnia.catalog.domain.Book;
import pl.sztuczkap.ksiegarnia.catalog.domain.CatalogRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class CatalogService implements CatalogUseCase {

    private CatalogRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .filter(book -> book.getAuthor().startsWith(author))
                .findFirst();
    }

    public void addBook(CreateBookCommand command) {
        Book book = new Book(command.getTitle(), command.getAuthor(), command.getYear());
        repository.save(book);
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookCommand command) {
        return repository
                .findById(command.getId())
                .map(book -> {
                    Book updatedBook = command.updateFields(book);
                    repository.save(book);
                    return UpdateBookResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateBookResponse(false, Arrays.asList("Book not found with id " + command.getId())));

    }

    @Override
    public void removeBook(Long id) {

    }



}
