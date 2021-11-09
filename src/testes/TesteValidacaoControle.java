package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TesteValidacaoControle {

    Contato contato;
    String msg = "";
    @BeforeEach
    void setUp() throws Exception {
        this.contato = new Contato("welbber", "vital", "1234-5678", 5);
        this.contato.insereTag("teste 1", 0);
        this.contato.insereTag("teste 2", 4);
    }
}
