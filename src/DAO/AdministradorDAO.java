package DAO;

import Classes.Administrador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class AdministradorDAO {
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
    
    public boolean consultaLogin(String nome, String senha) {        
        conectaBanco();
        String sql = "SELECT * FROM administrator WHERE name = ? AND password = MD5(?);";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nome);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if(rs.next())
                return true;    
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public void cadastra(Administrador adm) {        
        conectaBanco();
        String sql = "INSERT INTO administrator VALUES(null, ?, MD5(?), ?);";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, adm.getNome());
            pst.setString(2, adm.getSenha());
            pst.setInt(3, adm.getId());
            pst.execute();  
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
    }
    
     public ArrayList<Administrador> buscaSemFiltro() {        
        conectaBanco();
        ArrayList<Administrador> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM administrator;";
       
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Administrador aux = new Administrador(
                                            rs.getString("name"),
                                            rs.getInt("id")
                                        );    
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
     
     public Administrador buscaId(int id) {        
        conectaBanco();
        Administrador resposta = null;
        
        String sql = "SELECT * FROM administrator WHERE id = '" + id + "';";
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            rs.next();
            resposta = new Administrador(
                                        rs.getString("name")
                                    );
       
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return resposta;
    }
     
    public void deleta(int id) {
        conectaBanco();
        
        String sql = "DELETE FROM administrator WHERE id = ? ;";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void edita(Administrador novo, int id) {
        conectaBanco();
        
        String sql = "UPDATE administrator SET name = ?, password = MD5(?) WHERE id = ?;";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novo.getNome());
            pst.setString(2, novo.getSenha());
            pst.setInt(3, id);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
