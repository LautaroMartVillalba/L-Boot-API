package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
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
    public boolean validateBook(BookEntity book){
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
    public Page<BookEntity> findAllBooks(int page, int size) throws EmptyDataBaseException {
        PageRequest request = PageRequest.of(page, size);
        Page<BookEntity> entity = repository.findAll(request);
        if(!entity.isEmpty()){
            return entity;
        }
        throw new EmptyDataBaseException();
    }

    /**
     *  Search and return a book by his ID number.
     * @param id Long type variable.
     * @return A book Optional object if it can. Optional.empty() if it can't.
     */
    public Optional<BookEntity> findByBookID(Long id){
        if(id != null){
            Optional<BookEntity> result = repository.findById(id);
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
    public void saveBook(BookEntity book)  {
        if (!validateBook(book)) {
            throw new NullObjectException();
        }
        Optional<BookEntity> bookRepo = repository.findByTitleContainingAndAuthorContaining(book.getTitle(), book.getAuthor());
        if (bookRepo.isPresent()) {
            BookEntity bookOb = bookRepo.get();
            if (Objects.equals(book.getTitle(), bookOb.getTitle()) && Objects.equals(book.getAuthor(), bookOb.getAuthor()) && Objects.equals(book.getPublisher(), bookOb.getPublisher())) {
                throw new ExistingObjectException();
            }
        }
        repository.save(book);
    }

    /**
     * Delete a book from DataBase by his ID number.
     *
     * @param id Long type variable to identify the book.
     * @return True if it can delete book. False if it can't.
     */
    public boolean deleteBookById(Long id){
        Optional<BookEntity> findBook = repository.findById(id);
        if (findBook.isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Custom methods

    /**
     * Search books by his title.
     * @param title String that represent the title of the Book.
     * @return Empty list if it can't find. A List if it can.
     */
    public Page<BookEntity> findByTitle(String title, int page, int size){
        if (!Objects.equals(title, "")){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByTitleContaining(title, request);
        }
        return (Page<BookEntity>) new ArrayList<BookEntity>();
    }

    /**
     * Search books by his author.
     * @param author Author's name of the book.
     * @return Empty list if it can't find. A List if it can.
     */
    public Page<BookEntity> findByAuthor(String author, int page, int size){
        if (!Objects.equals(author, "")){
            PageRequest request = PageRequest.of(page, size);
            Page<BookEntity> result = repository.findByAuthorContaining(author, request);
            return repository.findByAuthorContaining(author, request);
        }
        return Page.empty();
    }

    /**
     * Search and return the books matches with the gender.
     * @param gender Gender's name.
     * @return A List of matched with gender if it can find. Empty List if it can't.
     */
    public Page<BookEntity> findByGender(BookGenders gender, int page, int size){
        if (gender != null){
            PageRequest request = PageRequest.of(page, size);
            Page<BookEntity> result = repository.findByGender(gender, request);
            return result;
        }
        return Page.empty();
    }

    /**
     * Search a book with pages quantity by the {min} and {max} values.
     * @param min Minimum int.
     * @param max Maximum int.
     * @return If values aren't null return a List. If are null (or can't find a book by pages) return an empty list.
     */
    public Page<BookEntity> findByPagesBetween(int min, int max, int page, int size){
        if (min > 0 && max > min){
            PageRequest request = PageRequest.of(page, size);
            Page<BookEntity> result = repository.findByPagesBetween(min, max, request);
            return result;
        }
        return Page.empty();
    }

    /**
     * Search a book with price quantity by the {min} and {max} values.
     * @param min Minimum int.
     * @param max Maximum int.
     * @return If values aren't null return a List. If are null (or can't find a book by price) return an empty list.
     */
    public Page<BookEntity> findByPriceBetween(int min, int max, int page, int size){
        if(min > 0 && max > min){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByPriceBetween(min,max, request);
        }
        throw new NullObjectException();
    }

    /**
     *  Search a book by his author and title.
     * @param title Book's title.
     * @param author Book's author.
     * @return A book's List if author and title matches in DataBase. Else return an empty list.
     */
    public Page<BookEntity> findByTitleAndAuthor(String title, String author, int page, int size){
        if(!Objects.equals(title, "") && !Objects.equals(author,"")){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByTitleContainingAndAuthorContaining(title, author, request);
        }
        return Page.empty();
    }

    /**
     *  Search a book by his author and gender (only one gender per query).
     * @param title Book's title.
     * @param gender Book's gender
     * @return A book's List if title and gender matches in DataBase. Else return an empty list.
     */
    public Page<BookEntity> findByTitleAndGender(String title, BookGenders gender, int page, int size){
        if (!Objects.equals(title, "") && gender != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByTitleContainingAndGender(title, gender, request);
        }
        return Page.empty();
    }

    /**
     *  Search a book by his author and gender.
     * @param author Book's author.
     * @param gender Book's gender.
     * @return A book's List if author and gender matches in DataBase. Else return an empty List.
     */
    public Page<BookEntity> findByAuthorAndGender(String author, BookGenders gender, int page, int size){
        if (!Objects.equals(author, "") && gender != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByAuthorContainingAndGender(author, gender, request);
        }
        return Page.empty();
    }

    /**
     *  Search a book by his author and if the book is available in DataBase.
     * @param author Book's author.
     * @return A List of the author's books only if they are available. Else return an empty list.
     */
    public Page<BookEntity> findByAuthorAndAvailableTrue(String author, int page, int size){
        if (!Objects.equals(author, "")){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByAuthorContainingAndAvailableTrue(author, request);
        }
        return Page.empty();
    }

    /**
     *  Search a book by his author and if the book is available in DataBase.
     * @param author Book's author.
     * @return A List of the author's books only if they are available. Else return an empty list.
     */
    public Page<BookEntity> findByAuthorAndAvailableFalse(String author, int page, int size){
        if (!Objects.equals(author, "")){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByAuthorContainingAndAvailableFalse(author, request);
        }
        return Page.empty();
    }

}
