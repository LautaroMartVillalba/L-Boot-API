package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
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
    public List<BookEntity> getAllBooks() throws BookEmptyDataBaseException {
        return service.findAllBooks();
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<BookEntity>> getBookById(@PathVariable Long id){
        Optional<BookEntity> book = service.findByBookID(id);
        if (book.isPresent()){
            return ResponseEntity.ok().body(book);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookEntity> deleteBook(@PathVariable Long id){
        boolean result = service.deleteBookById(id);
        if (result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<BookEntity> saveBook(@PathVariable BookEntity book) throws DuplicatedBookException, NullBookException {
        if (service.validateBook(book)) {
            service.saveBook(book);
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.badRequest().build();

    }
    /*--------------------------Custom Methods--------------------------*/

    @GetMapping("/by-title/{title}")
    public ResponseEntity<List<BookEntity>> getBookByTitle(@PathVariable String title){
        List<BookEntity> result = service.findByTitle(title);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-author/{author}")
    public ResponseEntity<List<BookEntity>> getBookByAuthor(@PathVariable String author){
        List<BookEntity> result = service.findByAuthor(author);
        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-gender/BookGenders.{gender}")
    public ResponseEntity<List<BookEntity>> getBookByGender(@PathVariable BookGenders gender){
        List<BookEntity> result = service.findByGender(gender);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/pages-between/{min}/{max}")
    public ResponseEntity<List<BookEntity>> getBookByPagesBetween(@PathVariable int min,@PathVariable int max){
        List<BookEntity> result = service.findByPagesBetween(min, max);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);    }

    @GetMapping("/price-between/{min}/{max}")
    public ResponseEntity<List<BookEntity>> getBookByPriceBetween(@PathVariable int min, @PathVariable int max){
        List<BookEntity> result = service.findByPriceBetween(min, max);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/title-author/{title}/{author}")
    public ResponseEntity<Optional<BookEntity>> findByTitleAndAuthor(@PathVariable String title,@PathVariable String author){
        Optional<BookEntity> result = service.findByTitleAndAuthor(title, author);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author-gender/{author}/{gender}")
    public ResponseEntity<List<BookEntity>> findByAuthorAndGender(@PathVariable String author,@PathVariable BookGenders gender){
        List<BookEntity> result = service.findByAuthorAndGender(author, gender);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/title-gender/{title}/{gender}")
    public ResponseEntity<List<BookEntity>> findByTitleAndGender(Pageable pageable, @PathVariable String title, @PathVariable BookGenders gender){
        List<BookEntity> result = service.findByTitleAndGender(title, gender);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author-available/{author}")
    public ResponseEntity<List<BookEntity>>findByAuthorAndAvailableTrue(@PathVariable String author){
        List<BookEntity> result = service.findByAuthorAndAvailableTrue(author);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/author-not-available/{author}")
    public ResponseEntity<List<BookEntity>> findByAuthorAndAvailableFalse(@PathVariable String author){
        List<BookEntity> result = service.findByAuthorAndAvailableFalse(author);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}