package DAO;

import Classes.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
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
    
    public int contProblemas(int id) {
        conectaBanco();
        String sql = "SELECT COUNT(*) AS ans FROM problem WHERE userid_user = ?;";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next())
                return rs.getInt("ans");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return 0;
    }
    
    public int contSubmissoes(int id) {
        conectaBanco();
        String sql = "SELECT COUNT(*) AS ans FROM submission WHERE userid_user = ?;";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next())
                return rs.getInt("ans");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return 0;
    }
    
    public int consultaLogin(String nick, String senha) {        
        conectaBanco();
        String sql = "SELECT * FROM user WHERE nick_name = ? AND password = MD5(?);";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nick);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            if(rs.next())
                return rs.getInt("id");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return -1;
    }
    
    public void edita(Usuario novo, String nick) {
        conectaBanco();
        
        String sql = "UPDATE user SET nick_name = ?, name = ?, email = ? WHERE nick_name = ?;";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novo.getNick());
            pst.setString(2, novo.getNome());
            pst.setString(3, novo.getEmail());
            pst.setString(4, nick);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleta(String nick) {
        conectaBanco();
        
        String sql = "DELETE FROM user WHERE nick_name = ? ;";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nick);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Usuario> buscaSemFiltro() {        
        conectaBanco();
        ArrayList<Usuario> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM user;";
        
        
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Usuario aux = new Usuario(
                                            rs.getString("name"),
                                            rs.getString("nick_name"),
                                            rs.getString("email"),
                                            rs.getInt("id")
                                        );
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
    public Usuario buscaNick(String nick) {        
        conectaBanco();
        Usuario resposta = null;
        
        String sql = "SELECT * FROM user WHERE nick_name = '" + nick + "';";
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            rs.next();
            resposta = new Usuario(
                                        rs.getString("name"),
                                        rs.getString("nick_name"),
                                        rs.getString("email"),
                                        rs.getInt("id")
                                    );
       
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return resposta;
    }
    
    public void cadastraUsuario(Usuario novo) {
        conectaBanco();
        String sql = "INSERT INTO user (nick_name, name, email, password) VALUES(?, ?, ?, MD5(?));";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, novo.getNick());
            pst.setString(2, novo.getNome());
            pst.setString(3, novo.getEmail());
            pst.setString(4, novo.getSenha());
            
            pst.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
