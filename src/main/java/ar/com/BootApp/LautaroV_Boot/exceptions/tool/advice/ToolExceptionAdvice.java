package ar.com.BootApp.LautaroV_Boot.exceptions.tool.advice;

import ar.com.BootApp.LautaroV_Boot.exceptions.ExceptionDTO;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.AddMoreDataException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.DuplicatedToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.ToolEmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.NullToolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ToolExceptionAdvice {

    @ExceptionHandler(value = AddMoreDataException.class)
    public ResponseEntity<ExceptionDTO> addMoreDataEXHandler(AddMoreDataException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("Insert all required data, please.").build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicatedToolException.class)
    public ResponseEntity<ExceptionDTO> duplicatedToolEXHandler(DuplicatedToolException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("This tool already exist in the DataBase.").build();
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ToolEmptyDataBaseException.class)
    public ResponseEntity<ExceptionDTO> emptyDataBaseEXHandler(ToolEmptyDataBaseException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("Has no tools in the DataBase.").build();
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NullToolException.class)
    public ResponseEntity<ExceptionDTO> nullToolEXHandler(NullToolException e){
        ExceptionDTO dto = ExceptionDTO.builder().message("You are trying to insert a null Tool.").build();
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
