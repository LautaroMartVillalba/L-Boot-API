package ar.com.BootApp.LautaroV_Boot.service;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.repositories.book.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    public boolean validateBook(Book book){
        if(book.getAuthor() == null || book.getTitle() == null || book.getPrice() <= 0 || book.getPages() <= 0 || book.getGender() == null ){
            return false;
        }
        return true;
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

    //This method also can be used to UpDate
    public void saveBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("You are trying to insert a null book.");
        }
        repository.save(book);
    }

    public void deleteBookById(Long id){
        if (id != 0){
            repository.deleteById(id);
        }
    }

    //Custom methods
    public List<Book> findByTitle(String title){
        if (title != null){
            return repository.findByTitleContaining(title);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByAuthor(String author){
        if (author != null){
            return repository.findByAuthorContaining(author);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByGender(BookGenders gender){
        if (gender != null){
            return repository.findByGender(gender);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByPagesBetween(int min, int max){
        if (min != 0 && max != 0){
            return repository.findByPagesBetween(min, max);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByPriceBetween(int min, int max){
        if(min != 0 && max != 0){
            return repository.findByPriceBetween(min,max);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByTitleAndAuthor(String title, String author){
        if(title != null && author != null){
            return repository.findByTitleContainingAndAuthorContaining(title, author);
        }
        return new ArrayList<>();
    }

    public List<Book> findByTitleAndGender(String title, BookGenders gender){
        if (title != null && gender != null){
            return repository.findByTitleContainingAndGender(title, gender);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByAuthorAndGender(String author, BookGenders gender){
        if (author != null && gender != null){
            return repository.findByAuthorContainingAndGender(author, gender);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByAuthorAndAvailableTrue(String author){
        if (author != null){
            return repository.findByAuthorContainingAndAvailableTrue(author);
        }
        return new ArrayList<Book>();
    }

    public List<Book> findByAuthorAndAvailableFalse(String author){
        if (author != null){
            return repository.findByAuthorContainingAndAvailableFalse(author);
        }
        return new ArrayList<Book>();
    }

}
