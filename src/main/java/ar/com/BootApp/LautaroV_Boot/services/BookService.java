package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    /**
     * Verify the book's data aren't null.
     * @param book The Object to validate the data.
     * @return False if one (or more) of the data are null. Return True if not are null.
     */
    public boolean validateBook(Book book){
        if(book.getAuthor() == null || book.getTitle() == null || book.getPrice() <= 0 || book.getPages() <= 0 || book.getGender() == null ){
            return false;
        }
        return true;
    }

    //Default methods

    /**
     *  Search and return all books in the DataBase.
     * @return List
     */
    public List<Book> findAllBooks(){
        return repository.findAll();
    }

    /**
     *  Search and return a book by his ID number.
     * @param id Long type variable.
     * @return A book Optional object if it can. Optional.empty() if it can't.
     */
    public Optional<Book> findByBookID(Long id){
        if(id != null && repository.findById(id).isPresent()){
            return repository.findById(id);
        }
        return Optional.empty();
    }

    //This method also can be used to UpDate

    /**
     * Save or update a book in the DataBase.
     *
     * @param book Book object to be saved.
     */
    public void saveBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("You are trying to insert a null book or one that already exists in database.");
        }
        repository.save(book);
    }

    /**
     * Delete a book from DataBase by his ID number.
     *
     * @param id Long type variable to identify the book.
     */
    public void deleteBookById(Long id){
        if (id != 0 || repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
    }

    //Custom methods

    /**
     * Search books by his title.
     * @param title String that represent the title of the Book.
     * @return Empty list if it can't find. A List if it can.
     */
    public List<Book> findByTitle(String title){
        if (title != null){
            return repository.findByTitleContaining(title);
        }
        return new ArrayList<Book>();
    }

    /**
     * Search books by his author.
     * @param author Author's name of the book.
     * @return Empty list if it can't find. A List if it can.
     */
    public List<Book> findByAuthor(String author){
        if (author != null){
            return repository.findByAuthorContaining(author);
        }
        return new ArrayList<Book>();
    }

    /**
     * Search and return the books matches with the gender.
     * @param gender Gender's name.
     * @return A List of matched with gender if it can find. Empty List if it can't.
     */
    public List<Book> findByGender(BookGenders gender){
        if (gender != null){
            return repository.findByGender(gender);
        }
        return new ArrayList<Book>();
    }

    /**
     * Search a book with pages quantity by the {min} and {max} values.
     * @param min Minimum int.
     * @param max Maximum int.
     * @return If values aren't null return a List. If are null (or can't find a book by pages) return an empty list.
     */
    public List<Book> findByPagesBetween(int min, int max){
        if (min != 0 && max != 0){
            return repository.findByPagesBetween(min, max);
        }
        return new ArrayList<Book>();
    }

    /**
     * Search a book with price quantity by the {min} and {max} values.
     * @param min Minimum int.
     * @param max Maximum int.
     * @return If values aren't null return a List. If are null (or can't find a book by price) return an empty list.
     */
    public List<Book> findByPriceBetween(int min, int max){
        if(min != 0 && max != 0){
            return repository.findByPriceBetween(min,max);
        }
        return new ArrayList<Book>();
    }

    /**
     *  Search a book by his author and title.
     * @param title Book's title.
     * @param author Book's author.
     * @return A book's List if author and title matches in DataBase. Else return an empty list.
     */
    public List<Book> findByTitleAndAuthor(String title, String author){
        if(title != null && author != null){
            return repository.findByTitleContainingAndAuthorContaining(title, author);
        }
        return new ArrayList<>();
    }

    /**
     *  Search a book by his author and gender (only one gender per query).
     * @param title Book's title.
     * @param gender Book's gender
     * @return A book's List if title and gender matches in DataBase. Else return an empty list.
     */
    public List<Book> findByTitleAndGender(String title, BookGenders gender){
        if (title != null && gender != null){
            return repository.findByTitleContainingAndGender(title, gender);
        }
        return new ArrayList<Book>();
    }

    /**
     *  Search a book by his author and gender.
     * @param author Book's author.
     * @param gender Book's gender.
     * @return A book's List if author and gender matches in DataBase. Else return an empty List.
     */
    public List<Book> findByAuthorAndGender(String author, BookGenders gender){
        if (author != null && gender != null){
            return repository.findByAuthorContainingAndGender(author, gender);
        }
        return new ArrayList<Book>();
    }

    /**
     *  Search a book by his author and if the book is available in DataBase.
     * @param author Book's author.
     * @return A List of the author's books only if they are available. Else return an empty list.
     */
    public List<Book> findByAuthorAndAvailableTrue(String author){
        if (author != null){
            return repository.findByAuthorContainingAndAvailableTrue(author);
        }
        return new ArrayList<Book>();
    }

    /**
     *  Search a book by his author and if the book is available in DataBase.
     * @param author Book's author.
     * @return A List of the author's books only if they are available. Else return an empty list.
     */
    public List<Book> findByAuthorAndAvailableFalse(String author){
        if (author != null){
            return repository.findByAuthorContainingAndAvailableFalse(author);
        }
        return new ArrayList<Book>();
    }

}
