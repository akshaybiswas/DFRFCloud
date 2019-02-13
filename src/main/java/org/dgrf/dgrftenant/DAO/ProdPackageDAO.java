/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.dgrftenant.DAO;

import javax.persistence.EntityManagerFactory;
import org.dgrf.dgrftenant.JPA.ProdpackageJpaController;

/**
 *
 * @author dgrf-iv
 */
public class ProdPackageDAO extends ProdpackageJpaController{
    
    public ProdPackageDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
}
