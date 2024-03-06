package com.ism.commande.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
@Setter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private String message;
}
