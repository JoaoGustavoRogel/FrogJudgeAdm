package DAO;

import Classes.Submissao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubmissaoDAO {
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String banco = "frog_judge";
    String url = "jdbc:mysql://localhost:3306/" + banco + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    String usuario = "root";
    String senha = "root";
    
    public void conectaBanco() {
        try {
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public ArrayList<Submissao> buscaIdProblema(int id) {     
        conectaBanco();
        ArrayList<Submissao> lista = new ArrayList<>();
        String sql = "SELECT * FROM problem_submission WHERE p_id = " + id + ";"; //View
        
       
        try {
            st = con.prepareStatement(sql);
            
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Submissao aux = new Submissao(
                                            rs.getInt("id"),
                                            rs.getString("name"),
                                            rs.getString("state"),
                                            rs.getString("date")
                                        );
                
                lista.add(aux);
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public ArrayList<Submissao> buscaIdUsuario(int id) {     
        conectaBanco();
        ArrayList<Submissao> lista = new ArrayList<>();
        String sql = "SELECT * FROM submission WHERE userid_user = " + id + ";";
        
        try {
            st = con.prepareStatement(sql);
            
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Submissao aux = new Submissao(
                                            rs.getInt("id"),
                                            rs.getString("state"),
                                            rs.getString("send_datetime")
                                        );
                lista.add(aux);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    
    }
}
