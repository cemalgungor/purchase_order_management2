package com.cemal.purchase_order_menagement.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundAuthorityException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NotFoundAuthorityException(String msg) {
        super(msg);
    }
}
