package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Outros;
import bd.OutrosBD;
import java.sql.SQLException;
import static javax.accessibility.AccessibleRole.LIST;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estudos
 */
@ManagedBean
@SessionScoped
public class OutrosBean {

    private Outros objOutros;
    private OutrosBD objOutrosBD;

    public OutrosBean() {
        this.objOutros = new Outros();
        this.objOutrosBD = new OutrosBD();

    }
    
    public Outros getOutros(){
        return objOutros;
    }
    
    public void setObjOutros(Outros objOutros){
        this.objOutros = objOutros;
    }

    public String salvaOutros() throws SQLException {
        if (this.objOutros.getId() != 0) {
            try {
                this.objOutrosBD.alterarOutros(this.objOutros);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM ALTERADOS COM SUCESSO!"));
                
            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO ALTERADOS!"));
                e.printStackTrace();
            }

            this.objOutros = new Outros();
        } else {
            try {
                this.objOutrosBD.inserirOutros(this.objOutros);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM INSERIDOS COM SUCESSO!"));
            } catch (SQLException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
                e.printStackTrace();
            }

        }

        return "outros.xhtml";
    }

    public String removerOutros(Outros outro) throws SQLException {
        try {
            this.objOutrosBD.removerOutros(outro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM REMOVIDOS COM SUCESSO!"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO INSERIDOS!"));
            e.printStackTrace();
        }
        this.objOutros = new Outros();
        return "outros.xhtml";
    }
    
    public List<Outros> getListarOutros(){
        List<Outros> lstOutros = new ArrayList<Outros>(); 
        try{
            lstOutros = objOutrosBD.listarOutros();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", "OS DADOS FORAM LISTADOS COM SUCESSO!"));
        }catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FRACASSO!", "OS DADOS FORAM NAO LISTADOS!"));
            e.printStackTrace();
            
        }
        return lstOutros;
    }
    public String preparaEditar(Outros outro){
        this.objOutros = outro;
        return  "outros.xhtml";
    }
}
