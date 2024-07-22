package ar.com.BootApp.LautaroV_Boot.controller.book;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    private BookService service;
    /*----------------Default methods---------------*/
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return service.findAllBooks();
    }

    @GetMapping("/by-id/{id}")
    public Optional<Book> getBookById(@PathVariable Long id){
        return service.findByBookID(id);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public boolean deleteBookByID(@PathVariable Long id){
        return service.deleteBook(id);
    }

    @PostMapping("/save/{book}")
    public ResponseEntity<Book> savebook(@PathVariable Book book){
        service.saveBook(book);
        return ResponseEntity.ok(book);
    }
    /*--------------------------Custom Methods--------------------------*/

    @GetMapping("/by-title/{title}")
    public List<Book> getBookByTitle(@PathVariable String title){
        return service.findByTitle(title);
    }

    @GetMapping("/by-author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author){
        return service.findByAuthor(author);
    }

    @GetMapping("/by-gender/{gender}")
    public List<Book> getBookByGender(@PathVariable String gender){
        return service.findByGender(gender);
    }

    @GetMapping("/pages-between/{min}/{max}")
    public List<Book> getBookByPagesBetween(@PathVariable int min,@PathVariable int max){
        return service.findByPagesBetween(min, max);
    }

    @GetMapping("/price-between/{min}/{max}")
    public List<Book> getbookByPriceBetween(@PathVariable int min,@PathVariable int max){
        return service.findByPriceBetween(min, max);
    }

    @GetMapping("/title-author/{title}/{author}")
    public Optional<Book> findByTitleAndAuthor(@PathVariable String title,@PathVariable String author){
        return service.findByTitleAndAuthor(title, author);
    }

    @GetMapping("/author-gender/{author}/{gender}")
    public List<Book> findByAuthorAndGender(@PathVariable String author,@PathVariable String gender){
        return service.findByAuthorAndGender(author, gender);
    }

    @GetMapping("/title-gender/{title}/{gender}")
    public List<Book> findByTitleAndGender(@PathVariable String title,@PathVariable String gender){
        return service.findByTitleAndGender(title, gender);
    }

    @GetMapping("/author-available/{author}")
    public List<Book> findByAuthorAndAvailableTrue(@PathVariable String author){
        return service.findByAuthorAndAvailableTrue(author);
    }

    @GetMapping("/author-not-available/{author}")
    public List<Book> fndByAuthorAndAvailableFalse(@PathVariable String author){
        return service.findByAuthorAndAvailableFalse(author);
    }

}