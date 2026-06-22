package dados;

public class ArquivoInvalidoException extends RuntimeException{
    public ArquivoInvalidoException(String mensagem){
        super(mensagem);
    }
}