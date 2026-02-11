package backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.bookstore.domain.Book;
import backend.bookstore.domain.BookRepository;
import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("save a couple of books");

			Category category1 = new Category("Horror");
			Category category2 = new Category("Romantic");
			Category category3 = new Category("History");

			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);

			bookRepository.save(new Book("Kasvoton kuolema", "Henning Mankell", "123456-22", 45.60, 1995, category1));
			bookRepository
					.save(new Book("A Farewell to Arms", "Ernest Hemingway", "112233-55", 81.00, 1929, category2));
			bookRepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 75.00, 1945, category3));

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};

	}
}
