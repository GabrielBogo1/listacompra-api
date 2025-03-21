package com.listadecompras.listacompras.payment;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Amount {
    private float value;
    private String currency_code;
}
