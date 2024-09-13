package ar.com.BootApp.LautaroV_Boot.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ExceptionDTO {
    String message;
    HttpStatus code;
}
