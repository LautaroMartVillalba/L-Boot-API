package ar.com.BootApp.LautaroV_Boot.service;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.repositories.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    /**
     * This method validate the information by a book.
     * @param book
     */
    public void validateBook(Book book){
        if(book.getAuthor() != null && book.getTitle() != null && book.getGender() != null && book.getPages() > 0 && book.getPrice() > 0){
            throw new IllegalArgumentException("Please, insert all book's info.");
        }
    }

    //Default methods
    public List<Book> findAllBooks(){
        return repository.findAll();
    }

    public Optional<Book> findByBookID(Long id){
        if(id != null && repository.findById(id).isPresent()){
            return repository.findById(id);
        }
        return Optional.empty();
    }

    public boolean deleteBook(Long id){
        if(id == null ) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public void saveBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("You trying to upload a null book.");
        }
        validateBook(book);
        repository.save(book);
    }

    //Custom methods
    public List<Book> findByTitle(String title){
        if (title != null){
            return repository.findByTitle(title);
        }
        return Collections.emptyList();
    }

    public List<Book> findByAuthor(String author){
        if (author != null){
            return repository.findByAuthorContaining(author);
        }
        return Collections.emptyList();
    }

    public List<Book> findByGender(String gender){
        if (gender != null){
            return repository.findByGender(gender.toUpperCase());
        }
        return Collections.emptyList();
    }

    public List<Book> findByPagesBetween(int min, int max){
        if (min != 0 && max != 0){
            return repository.findByPagesBetween(min, max);
        }
        return null;
    }

    public List<Book> findByPriceBetween(int min, int max){
        if(min != 0 && max != 0){
            return repository.findByPriceBetween(min,max);
        }
        return null;
    }

    public Optional<Book> findByTitleAndAuthor(String title, String author){
        if(title != null && author != null){
            return repository.findByTitleAndAuthor(title, author);
        }
        return null;
    }

    public List<Book> findByTitleAndGender(String title, String gender){
        if (title != null && gender != null){
            return repository.findByTitleAndGender(title, gender);
        }
        return null;
    }

    public List<Book> findByAuthorAndGender(String author, String gender){
        if (author != null && gender != null){
            return repository.findByAuthorAndGender(author, gender);
        }
        return null;
    }

    public List<Book> findByAuthorAndAvailableTrue(String author){
        if (author != null){
            return repository.findByAuthorAndAvailableTrue(author);
        }
        return null;
    }

    public List<Book> findByAuthorAndAvailableFalse(String author){
        if (author != null){
            return repository.findByAuthorAndAvailableFalse(author);
        }
        return null;
    }

}
