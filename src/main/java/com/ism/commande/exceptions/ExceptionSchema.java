package com.ism.commande.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionSchema {

    private String message;

    protected ExceptionSchema() {}

    public ExceptionSchema(String message) {
        this.message = message;
    }
}
