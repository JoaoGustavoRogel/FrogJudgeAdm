package Classes;

public class Administrador {
    private String nome;
    private String senha;
    private int id;

    public Administrador() {
        
    }
    
    public Administrador(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public Administrador(String nome) {
        this.nome = nome;
    }

    public Administrador(String nome, String senha, int id) {
        this.nome = nome;
        this.senha = senha;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
