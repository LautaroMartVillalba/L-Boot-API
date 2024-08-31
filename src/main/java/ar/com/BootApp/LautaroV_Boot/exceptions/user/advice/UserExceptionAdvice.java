package ar.com.BootApp.LautaroV_Boot.exceptions.user.advice;

import ar.com.BootApp.LautaroV_Boot.exceptions.ExceptionDTO;
import ar.com.BootApp.LautaroV_Boot.exceptions.user.types.DuplicatedUserException;
import ar.com.BootApp.LautaroV_Boot.exceptions.user.types.NullUserException;
import ar.com.BootApp.LautaroV_Boot.exceptions.user.types.UserEmptyDataBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionAdvice {

        @ExceptionHandler(value = DuplicatedUserException.class)
        public ResponseEntity<ExceptionDTO> duplicatedUserEXHandler(DuplicatedUserException e){
            ExceptionDTO dto = ExceptionDTO.builder().message("This user already exists in the DataBase.").build();
            return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(value = NullUserException.class)
        public ResponseEntity<ExceptionDTO> nullUserEXHandler(NullUserException e){
            ExceptionDTO dto = ExceptionDTO.builder().message("You are trying to insert a null User. Check your data.").build();
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = UserEmptyDataBaseException.class)
        public ResponseEntity<ExceptionDTO> emptyDataBaseEXHandler(UserEmptyDataBaseException e){
            ExceptionDTO dto = ExceptionDTO.builder().message("Has no Users in the DataBase.").build();
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }

}
