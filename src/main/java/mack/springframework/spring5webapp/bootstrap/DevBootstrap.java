package mack.springframework.spring5webapp.bootstrap;

import mack.springframework.spring5webapp.model.Author;
import mack.springframework.spring5webapp.model.Book;
import mack.springframework.spring5webapp.model.Publisher;
import mack.springframework.spring5webapp.repositories.AuthorRepository;
import mack.springframework.spring5webapp.repositories.BookRepository;
import mack.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override//alt+insert override methods
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();

    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("Penguin");
        publisher.setAddress("LA");

        publisherRepository.save(publisher);

        Author james = new Author("James","Allen");
        Book amt = new Book("As A Man Thinketh","1234", publisher);
        james.getBooks().add(amt);
        amt.getAuthors().add(james);

        authorRepository.save(james);
        bookRepository.save(amt);

        Author sun = new Author("Sun","Tzu");
        Book taow = new Book("The Art of War","5678", publisher);
        sun.getBooks().add(taow);

        authorRepository.save(sun);
        bookRepository.save(taow);
    }
}
