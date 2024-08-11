package ar.com.BootApp.LautaroV_Boot.exceptions.book.advice;

import ar.com.BootApp.LautaroV_Boot.exceptions.ExceptionDTO;
import ar.com.BootApp.LautaroV_Boot.exceptions.book.types.BookEmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.book.types.NullBookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExceptionsAdvice {

    @ExceptionHandler(value = BookEmptyDataBaseException.class)
    public ResponseEntity<ExceptionDTO> bookEmptyDataBaseEXHandler(BookEmptyDataBaseException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("Has no books in the DataBase.").build();
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullBookException.class)
    public ResponseEntity<ExceptionDTO> nullBookEXHandler(NullBookException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("You are trying to insert a null book.").build();
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }


}
