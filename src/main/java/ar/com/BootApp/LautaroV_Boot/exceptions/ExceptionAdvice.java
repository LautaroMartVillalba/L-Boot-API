package ar.com.BootApp.LautaroV_Boot.exceptions;

import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NeedMoreDataException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = EmptyDataBaseException.class)
    public ResponseEntity<ExceptionDTO> emptyDataBaseHandler(){
        ExceptionDTO dto = ExceptionDTO.builder().message("Has no registers in the DataBase.").code(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(dto, dto.getCode());
    }

    @ExceptionHandler(value = ExistingObjectException.class)
    public ResponseEntity<ExceptionDTO> existingObjectHandler(){
        ExceptionDTO dto = ExceptionDTO.builder().message("A register with this data already exists in the DataBase.").code(HttpStatus.CONFLICT).build();
        return new ResponseEntity<>(dto, dto.getCode());
    }

    @ExceptionHandler(value = NeedMoreDataException.class)
    public ResponseEntity<ExceptionDTO> needMoreDataHandler(){
        ExceptionDTO dto = ExceptionDTO.builder().message("Please complete all fields to save a record").code(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(dto, dto.getCode());
    }

    @ExceptionHandler(value = NullObjectException.class)
    public ResponseEntity<ExceptionDTO> NullObjectHandler(){
        ExceptionDTO dto = ExceptionDTO.builder().message("You are trying to insert a null record.").code(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(dto, dto.getCode());
    }

}
