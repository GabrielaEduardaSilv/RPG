package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Paladino;
import player.Personagem;

public class PersonagemTest {

    private Personagem heroi;

    @BeforeEach
    public void setUp() {
        heroi = new Paladino();
        heroi.setVida(100);
    }

    @Test
    public void testTomarDanoReduzVidaCorretamente() {
        int danoGeral = 30;
        heroi.setVida(heroi.getVida() - danoGeral);

        assertEquals(70, heroi.getVida(), "A vida do personagem não reduziu corretamente após o dano.");
    }

    @Test
    public void testPersonagemEstaMortoComVidaZeradaOuNegativa() {
        heroi.setVida(-5);

        boolean estaMorto = (heroi.getVida() <= 0);

        assertTrue(estaMorto, "O personagem deveria ser considerado morto com vida menor ou igual a zero.");
    }
}
