package DAO;

import Classes.Problema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProblemaDAO {
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String banco = "frog_judge";
    String url = "jdbc:mysql://localhost:3306/" + banco + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    String usuario = "root";
    String senha = "root";
    
    public Problema buscaId(int idP, int idU) {        
        conectaBanco();
        Problema resposta = null;
        
        String sql = "SELECT * FROM problem WHERE id = '" + idP + "' AND userid_user = '" + idU + "' ;";
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            rs.next();
            resposta = new Problema(
                                            rs.getString("name"),
                                            rs.getString("input"),
                                            rs.getString("output"),
                                            "",
                                            rs.getString("description"),
                                            rs.getBoolean("public"),
                                            rs.getInt("id")
                                    );
       
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return resposta;
    }
    
    public void conectaBanco() {
        try {
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    public void cadastraProblema(Problema novo, int id) {
        conectaBanco();
        String sql = "INSERT INTO problem (name, description, input, output, userid_user, public) VALUES(?, ?, ?, ?, ?, ?);";
        
        try {
            pst = con.prepareStatement(sql);
            
            pst.setString(1, novo.getNome());
            pst.setString(2, novo.getDescricao());
            pst.setString(3, novo.getInput());
            pst.setString(4, novo.getOutput());
            pst.setInt(5, id);
            pst.setBoolean(6, novo.getPublico().equals("Sim"));
            pst.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Problema> buscaSemFiltro() {        
        conectaBanco();
        ArrayList<Problema> lista = new ArrayList<>();
        
        String sql = "SELECT p.name AS 'name', p.input AS 'input', p.output AS 'output', u.name AS 'owner' , p.description AS 'description', p.public AS 'public', p.id AS 'id' "
                + " FROM problem AS p, user AS u "
                + " WHERE u.id = p.userid_user;";
        
       
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Problema aux = new Problema(
                                            rs.getString("name"),
                                            rs.getString("input"),
                                            rs.getString("output"),
                                            rs.getString("owner"),
                                            rs.getString("description"),
                                            rs.getBoolean("public"),
                                            rs.getInt("id")
                                        );
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
        public ArrayList<Problema> buscaUsuario(int id) {        
        conectaBanco();
        ArrayList<Problema> lista = new ArrayList<>();
        
        String sql = "SELECT p.name AS 'name', p.input AS 'input', p.output AS 'output', u.name AS 'owner' , p.description AS 'description', p.public AS 'public', p.id AS 'id' "
                + " FROM problem AS p, user AS u "
                + " WHERE (u.id = " + id + " OR p.public = 1 ) AND (u.id = p.userid_user);";
        
       
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Problema aux = new Problema(
                                            rs.getString("name"),
                                            rs.getString("input"),
                                            rs.getString("output"),
                                            rs.getString("owner"),
                                            rs.getString("description"),
                                            rs.getBoolean("public"),
                                            rs.getInt("id")
                                        );
                
                lista.add(aux);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return lista;
    }
    
     public Problema buscaId(int id) {        
        conectaBanco();
        Problema resposta = null;
        String sql = "SELECT p.name AS 'name', p.input AS 'input', p.output AS 'output', u.name AS 'owner' , p.description AS 'description', p.public AS 'public', p.id AS 'id' "
                + " FROM problem AS p, user AS u "
                + " WHERE (u.id = p.userid_user) AND (p.id = " + id + ");";
        
       
        try {
            st = con.prepareStatement(sql);
            
            rs = st.executeQuery(sql);
            
            if(rs.next()) {
                resposta = new Problema(
                                            rs.getString("name"),
                                            rs.getString("input"),
                                            rs.getString("output"),
                                            rs.getString("owner"),
                                            rs.getString("description"),
                                            rs.getBoolean("public"),
                                            rs.getInt("id")
                                        );
                
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return resposta;
    }
    
    public void edita(Problema novo, int id) {
        conectaBanco();
        
        String sql = "UPDATE problem SET name = ?, description = ?, input = ?, output = ?, public = ? WHERE id = ?;";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novo.getNome());
            pst.setString(2, novo.getDescricao());
            pst.setString(3, novo.getInput());
            pst.setString(4, novo.getOutput());
            pst.setBoolean(5, novo.getPublico().equals("Sim"));
            pst.setInt(6, id);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleta(int id) {
        conectaBanco();
        
        String sql = "DELETE FROM problem WHERE id = ? ;";
        
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
