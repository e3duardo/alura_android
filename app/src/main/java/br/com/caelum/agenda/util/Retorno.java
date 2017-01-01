package br.com.caelum.agenda.util;

/**
 * Created by eduardo on 31/12/16.
 */

public class Retorno<T,R> {
    private final T chave;
    private final R valor;

    public Retorno(T chave, R valor){
        this.chave = chave;
        this.valor = valor;
    }

    public T getChave() {
        return chave;
    }

    public R getValor() {
        return valor;
    }
}
