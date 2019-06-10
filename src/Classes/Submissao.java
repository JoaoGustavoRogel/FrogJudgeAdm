package Classes;

public class Submissao {
    private int id;
    private String dono;
    private String resposta;
    private String data;

    public Submissao(int id, String dono, String resposta, String data) {
        this.id = id;
        this.dono = dono;
        this.resposta = resposta;
        this.data = data;
    }

    public Submissao(int id, String resposta, String data) {
        this.id = id;
        this.resposta = resposta;
        this.data = data;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
    
    
}
