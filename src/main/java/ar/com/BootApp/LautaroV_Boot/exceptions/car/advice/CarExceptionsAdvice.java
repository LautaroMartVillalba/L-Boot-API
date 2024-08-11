package ar.com.BootApp.LautaroV_Boot.exceptions.car.advice;

import ar.com.BootApp.LautaroV_Boot.exceptions.ExceptionDTO;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.AddMoreDataException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.DuplicatedCarException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.NullCarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CarExceptionsAdvice {

    @ExceptionHandler(value = DuplicatedCarException.class)
    public ResponseEntity<ExceptionDTO> duplicatedCarEXCHandler(DuplicatedCarException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("This car already exists in DataBase.").build();
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NullCarException.class)
    public ResponseEntity<ExceptionDTO> nullCarEXCHandler(NullCarException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("You are trying to insert a null car.").build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmptyDataBaseException.class)
    public ResponseEntity<ExceptionDTO> emptyDataBaseEXCHandler(EmptyDataBaseException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("Has no cars in the DataBase.").build();
        return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = AddMoreDataException.class)
    public ResponseEntity<ExceptionDTO> addMoreDataEXCHandler(AddMoreDataException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("Insert all required data, please.").build();
        return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
    }
}
