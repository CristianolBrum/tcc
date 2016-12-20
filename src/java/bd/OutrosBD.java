/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Outros;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author Estudos
 */
public class OutrosBD {
    public void inserirOutros(Outros objOutros) throws  SQLException{
         
        try {
            PreparedStatement pstm;
            Connection con = ConnBD.getConnection();

            con.setAutoCommit(false);
            pstm = con.prepareStatement("INSERT INTO outros (nome,cpf,email,senha) VALUES (?, ?, ?, ?)");
            pstm.setString(1, objOutros.getNome());
            pstm.setString(2, objOutros.getCpf());
            pstm.setString(3, objOutros.getEmail());
            pstm.setString(4, objOutros.getSenha());
            pstm.executeUpdate();
            
            
            con.commit();
            
    
    }catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public void removerOutros(Outros objOutros) throws  SQLException{
        String sql = "DELETE FROM outros WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setInt(1, objOutros.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
     }
    
    public void alterarOutros(Outros objOutros) throws  SQLException{
        String sql = "UPDATE outros SET nome = ?, cpf = ?, email = ?, senha = ? WHERE id = ?";
        PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
        ps.setString(1, objOutros.getNome());
        ps.setString(2, objOutros.getCpf());
        ps.setString(3, objOutros.getEmail());
        ps.setString(4, objOutros.getSenha());
        ps.setInt(5, objOutros.getId());
        ps.execute();
        ps.close();
        ConnBD.getConnection().close();
    }
    public List<Outros> listarOutros() throws  SQLException{
    List<Outros> lstOutros = new ArrayList<Outros>();       
    String sql = "SELECT * FROM outros";
    PreparedStatement ps = ConnBD.getConnection().prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        Outros objOutros = new Outros();
        objOutros.setId(rs.getInt("id"));
        objOutros.setNome(rs.getString("nome"));
        objOutros.setCpf(rs.getString("cpf"));
        objOutros.setEmail(rs.getString("email"));
        objOutros.setSenha(rs.getString("senha"));
        lstOutros.add(objOutros);
    }
    ps.close();
    ConnBD.getConnection().close();
    return lstOutros;
    }
    
}
