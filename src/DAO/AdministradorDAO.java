package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



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
}
