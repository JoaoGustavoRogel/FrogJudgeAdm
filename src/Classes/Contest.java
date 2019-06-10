package Classes;

public class Contest {
    private String nome;
    private String inicio;
    private String fim;
    private String ganhador;
    private String dono;
    
    private int idContest;
    private int idDono;
    private int idGanhador;
    
    public Contest(String nome, String ganhador, String dono, int idContest) {
        this.nome = nome;
        this.ganhador = ganhador;
        this.dono = dono;
        this.idContest = idContest;
    }
    
    
    

    public Contest(String nome, String inicio, String fim, int idContest, int idDono, int idGanhador) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.idContest = idContest;
        this.idDono = idDono;
        this.idGanhador = idGanhador;
    }


    

    public Contest(String nome, String inicio, String fim, int dono, int ganhador) {
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.idDono = dono;
        this.idGanhador = ganhador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getGanhador() {
        return ganhador;
    }

    public void setGanhador(String ganhador) {
        this.ganhador = ganhador;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public int getIdContest() {
        return idContest;
    }

    public void setIdContest(int idContest) {
        this.idContest = idContest;
    }

    public int getIdDono() {
        return idDono;
    }

    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }

    public int getIdGanhador() {
        return idGanhador;
    }

    public void setIdGanhador(int idGanhador) {
        this.idGanhador = idGanhador;
    }
    
    
    
}
