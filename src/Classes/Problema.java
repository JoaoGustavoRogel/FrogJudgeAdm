package Classes;

public class Problema {
    private int id;
    private String nome;
    private String input;
    private String output;
    private String autor;
    private String descricao;
    private boolean publico;

    public Problema(String nome, String input, String output, String autor, String descricao, boolean publico, int id) {
        this.nome = nome;
        this.input = input;
        this.output = output;
        this.autor = autor;
        this.descricao = descricao;
        this.publico = publico;
        this.id = id;
    }

    public Problema(String nome, String input, String output, String descricao, boolean publico) {
        this.nome = nome;
        this.input = input;
        this.output = output;
        this.descricao = descricao;
        this.publico = publico;
    }
    
    
    
    public String getPublico() {
        if(publico) return "Sim";
        else return "Não";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

   
}
