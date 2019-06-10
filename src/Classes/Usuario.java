package Classes;

public class Usuario {
    private String nome;
    private String nick;
    private String email;
    private String senha;
    
    private int id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setSenha(String senha) {
        this.senha = nome;
    }

    public String getNick() {
        return nick;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario() {
        
    }
    
    public Usuario(String nome, String nick, String email, int id) {
        this.nome = nome;
        this.nick = nick;
        this.email = email;
        this.id = id;
    }
    
    public Usuario(String nome, String nick, String email, String senha) {
        this.nome = nome;
        this.nick = nick;
        this.email = email;
        this.senha = senha;
    }
    
    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

  
}
