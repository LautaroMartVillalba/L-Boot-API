package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
@PreAuthorize("hasAnyRole('ROLE_READER', 'ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class BookController {

    private BookService service;

    /*----------------Default methods---------------*/
    @GetMapping("/all")
    public Page<BookEntity> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "20") int size) throws EmptyDataBaseException {
        return service.findAllBooks(page, size);
    }


    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<BookEntity>> getBookById(@PathVariable Long id) {
        Optional<BookEntity> book = service.findByBookID(id);
        if (book.isPresent()) {
            return ResponseEntity.ok().body(book);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    public ResponseEntity<BookEntity> deleteBook(@PathVariable Long id) {
        boolean result = service.deleteBookById(id);
        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<BookEntity> saveBook(@PathVariable BookEntity book) throws ExistingObjectException, NullObjectException {
        if (service.validateBook(book)) {
            service.saveBook(book);
            return ResponseEntity.ok(book);
        }
        return ResponseEntity.badRequest().build();

    }
    /*--------------------------Custom Methods--------------------------*/

    @GetMapping("/by-title/{title}")
    public Page<BookEntity> getBookByTitle(@PathVariable String title,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "20") int size) {
        Page<BookEntity> result = service.findByTitle(title, page, size);
        return result;
    }

    @GetMapping("/by-author/{author}")
    public Page<BookEntity> getBookByAuthor(@PathVariable String author,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByAuthor(author, page, size);
        return result;
    }

    @GetMapping("/by-gender/BookGenders.{gender}")
    public Page<BookEntity> getBookByGender(@PathVariable BookGenders gender,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByGender(gender, page, size);
        return result;
    }

    @GetMapping("/pages-between/{min}/{max}")
    public Page<BookEntity> getBookByPagesBetween(@PathVariable int min,@PathVariable int max,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByPagesBetween(min, max, page, size);
        return result;
    }

    @GetMapping("/price-between/{min}/{max}")
    public Page<BookEntity> getBookByPriceBetween(@PathVariable int min,
                                                                  @PathVariable int max,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByPriceBetween(min, max, page, size);
        return result;
    }

    @GetMapping("/title-author/{title}/{author}")
    public Page<BookEntity> findByTitleAndAuthor(@PathVariable String title,
                                                                     @PathVariable String author,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByTitleAndAuthor(title, author, page, size);
        return result;
    }

    @GetMapping("/author-gender/{author}/{gender}")
    public Page<BookEntity> findByAuthorAndGender(@PathVariable String author,
                                                                  @PathVariable BookGenders gender,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByAuthorAndGender(author, gender, page, size);
        return result;
    }

    @GetMapping("/title-gender/{title}/{gender}")
    public Page<BookEntity> findByTitleAndGender(@PathVariable String title,
                                                                 @PathVariable BookGenders gender,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByTitleAndGender(title, gender, page, size);
        return result;
    }

    @GetMapping("/author-available/{author}")
    public Page<BookEntity>findByAuthorAndAvailableTrue(@PathVariable String author,
                                                                        @RequestParam(defaultValue = "0") int page,
                                                                        @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByAuthorAndAvailableTrue(author, page, size);
        return result;
    }

    @GetMapping("/author-not-available/{author}")
    public Page<BookEntity> findByAuthorAndAvailableFalse(@PathVariable String author,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "20") int size){
        Page<BookEntity> result = service.findByAuthorAndAvailableFalse(author, page, size);
        return result;
    }
}