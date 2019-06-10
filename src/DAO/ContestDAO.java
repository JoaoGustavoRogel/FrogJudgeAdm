package DAO;

import Classes.Contest;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContestDAO {
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
    
    public ArrayList<Contest> buscaFinalizados() {        
        conectaBanco();
        ArrayList<Contest> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM ended_contest;"; //View;
        
        
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Contest aux = new Contest(rs.getString("name"),
                                          rs.getString("winner"),
                                          rs.getString("owner"),
                                          rs.getInt("id")
                                        );  
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public ArrayList<Contest> buscaSemFiltro() {        
        conectaBanco();
        ArrayList<Contest> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM contest WHERE end >= SYSDATE();";

        
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Contest aux = new Contest(rs.getString("name"),
                                          rs.getString("start"),
                                          rs.getString("end"),
                                          rs.getInt("id"),
                                          rs.getInt("userid_owner"),
                                          rs.getInt("userid_winner")
                                        );
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public void cadastraUsuarioContest(int idU, int idC) {
        conectaBanco();
        String sql = "INSERT INTO contest_has_user (userid_user, contestid_contest) VALUES(?, ?);";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, idU);
            pst.setInt(2, idC);
            
            
            pst.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Contest> contestUsuario(int idU) {        
        conectaBanco();
        ArrayList<Contest> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM user_contest WHERE u_id = " + idU + ";"; //View;
        
        
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Contest aux = new Contest(rs.getString("name"),
                                          rs.getString("start"),
                                          rs.getString("end"),
                                          rs.getInt("c_id"),
                                          0, 0
                                        );  
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
}
