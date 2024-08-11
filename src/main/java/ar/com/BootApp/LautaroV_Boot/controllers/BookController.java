package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.exceptions.book.types.DuplicatedBookException;
import ar.com.BootApp.LautaroV_Boot.exceptions.book.types.BookEmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.book.types.NullBookException;
import ar.com.BootApp.LautaroV_Boot.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private BookService service;
    /*----------------Default methods---------------*/
    @GetMapping("/all")
    public List<Book> getAllBooks() throws BookEmptyDataBaseException {
        return service.findAllBooks();
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id){
        Optional<Book> book = service.findByBookID(id);
        if (book.isPresent()){
            return ResponseEntity.ok().body(book);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        boolean result = service.deleteBookById(id);
        if (result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@PathVariable Book book) throws DuplicatedBookException, NullBookException {
        if (service.validateBook(book)) {
            service.saveBook(book);
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.badRequest().build();

    }
    /*--------------------------Custom Methods--------------------------*/

    @GetMapping("/by-title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title){
        List<Book> result = service.findByTitle(title);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author){
        List<Book> result = service.findByAuthor(author);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-gender/BookGenders.{gender}")
    public ResponseEntity<List<Book>> getBookByGender(@PathVariable BookGenders gender){
        List<Book> result = service.findByGender(gender);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pages-between/{min}/{max}")
    public ResponseEntity<List<Book>> getBookByPagesBetween(@PathVariable int min,@PathVariable int max){
        List<Book> result = service.findByPagesBetween(min, max);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);    }

    @GetMapping("/price-between/{min}/{max}")
    public ResponseEntity<List<Book>> getBookByPriceBetween(@PathVariable int min, @PathVariable int max){
        List<Book> result = service.findByPriceBetween(min, max);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/title-author/{title}/{author}")
    public ResponseEntity<Optional<Book>> findByTitleAndAuthor(@PathVariable String title,@PathVariable String author){
        Optional<Book> result = service.findByTitleAndAuthor(title, author);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author-gender/{author}/{gender}")
    public ResponseEntity<List<Book>> findByAuthorAndGender(@PathVariable String author,@PathVariable BookGenders gender){
        List<Book> result = service.findByAuthorAndGender(author, gender);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/title-gender/{title}/{gender}")
    public ResponseEntity<List<Book>> findByTitleAndGender(Pageable pageable, @PathVariable String title, @PathVariable BookGenders gender){
        List<Book> result = service.findByTitleAndGender(title, gender);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author-available/{author}")
    public ResponseEntity<List<Book>>findByAuthorAndAvailableTrue(@PathVariable String author){
        List<Book> result = service.findByAuthorAndAvailableTrue(author);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author-not-available/{author}")
    public ResponseEntity<List<Book>> findByAuthorAndAvailableFalse(@PathVariable String author){
        List<Book> result = service.findByAuthorAndAvailableFalse(author);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}