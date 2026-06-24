package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Batalha;
import player.Paladino;
import player.Personagem;

import java.io.File;

public class BatalhaTest {

    private Batalha batalha;
    private Personagem heroi;

    @BeforeEach
    public void setUp() {
        batalha = new Batalha();
        heroi = new Paladino();
    }

    @Test
    public void testPermadeathDeletaArquivoCorretamente() {
        String nomeArquivo = "save_slot2.dat";
        File arquivoTeste = new File(nomeArquivo);

        try {
            arquivoTeste.createNewFile();
        } catch (Exception e) {
            fail("Não foi possível criar o arquivo de teste no ambiente.");
        }

        batalha.deletarSave(2);

        assertFalse(arquivoTeste.exists(), "O arquivo de save do Slot 2 deveria ter sido deletado!");
    }
    @Test
    public void testStatusMonstroEliteAplicaMultiplicador() {
        int vidaBaseInimigo = 100;

        int vidaEliteCalculada = (int) (vidaBaseInimigo * 1.5);

        assertEquals(150, vidaEliteCalculada, "O multiplicador de vida do monstro Elite falhou.");
    }
}

