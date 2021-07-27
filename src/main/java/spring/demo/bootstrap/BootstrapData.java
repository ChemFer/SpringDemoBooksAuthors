package spring.demo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.demo.domain.Publisher;
import spring.demo.repositories.PublisherRepository;
import spring.demo.domain.Author;
import spring.demo.domain.Book;
import spring.demo.repositories.AuthorRepository;
import spring.demo.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisherKing = new Publisher();
        publisherKing.setName("Doubleday");
        publisherKing.setCity("New York City");
        publisherKing.setPostalCode("NY 10019");
        publisherKing.setAddressLine("1745 Broadway");

        Publisher publisherOrwell = new Publisher();
        publisherOrwell.setName("Harvill Secker");
        publisherOrwell.setCity("London");

        Author stephen = new Author("Stephen", "King");
        Book shining = new Book("The Shining", "978-0-385-12167-5");
        stephen.getBooks().add(shining);
        shining.getAuthor().add(stephen);

        Author george = new Author("George", "Orwell");
        Book animalFarm = new Book("Animal Farm", "978-1412811903");
        george.getBooks().add(animalFarm);
        animalFarm.getAuthor().add(george);

        authorRepository.save(stephen);
        bookRepository.save(shining);

        authorRepository.save(george);
        bookRepository.save(animalFarm);

        animalFarm.setPublisher(publisherOrwell);
        shining.setPublisher(publisherKing);
        publisherOrwell.getBooks().add(animalFarm);
        publisherKing.getBooks().add(shining);

        publisherRepository.save(publisherKing);
        publisherRepository.save(publisherOrwell);

        System.out.println("Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher: " + animalFarm.getPublisher().getName() +"\nNumber of books: " + publisherOrwell.getBooks().size());
        System.out.println("Animal Farm publisher: " + animalFarm.getPublisher().getName() + ", " + animalFarm.getPublisher().getCity());
        System.out.println("The Shining publisher: " + shining.getPublisher().getName() + ", " + publisherKing.getCity());

    }
}
