package br.com.appfastfood.cliente.exceptions;

public enum ExceptionsMessages {
    CLIENTE_NAO_ENCONTRADO("Não foi possível encontrar cliente!");

    private final String value;

    ExceptionsMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
