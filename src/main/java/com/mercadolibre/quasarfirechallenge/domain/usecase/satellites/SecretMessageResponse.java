package com.mercadolibre.quasarfirechallenge.domain.usecase.satellites;

import lombok.Data;

@Data
public class SecretMessageResponse {

    private DataPosition dataPosition;
    private String message;

}
