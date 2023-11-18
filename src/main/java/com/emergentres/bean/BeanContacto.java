package com.emergentres.bean;

import com.emergentres.entidades.Contacto;
import com.emergentres.jpa.ContactoJpaController;
import com.emergentres.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanContacto {
    private EntityManagerFactory efm;
    private ContactoJpaController jpaContacto;
    
    public BeanContacto() {   efm = Persistence.createEntityManagerFactory("UPagenda");
    jpaContacto = new ContactoJpaController(efm);
    }
    public List<Contacto> listarTodos(){
        return jpaContacto.findContactoEntities();
    }
    public void insertar(Contacto c){
        jpaContacto.create(c);
    }

    public void editar(Contacto c){
        try {
            jpaContacto.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void eliminar(Integer id) {
        try {
            jpaContacto.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Contacto buscar(Integer id){
        return jpaContacto.findContacto(id);
    }
}
