package exceptions;

public class NotFoundExceptions extends Exception{
    
    private Integer statusCode;
    private String mensagem;

    public NotFoundExceptions(Integer statusCode, String mensagem) {
        this.statusCode = statusCode;
        this.mensagem = mensagem;
    }
    
    public Integer getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    

}
