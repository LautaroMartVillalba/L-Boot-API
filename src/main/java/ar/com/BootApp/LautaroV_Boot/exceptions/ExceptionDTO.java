package ar.com.BootApp.LautaroV_Boot.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionDTO {
    String message;
}
