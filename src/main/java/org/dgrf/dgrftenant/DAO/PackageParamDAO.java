/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.dgrftenant.DAO;

import javax.persistence.EntityManagerFactory;
import org.dgrf.dgrftenant.JPA.PackageparamJpaController;

/**
 *
 * @author dgrf
 */
public class PackageParamDAO extends PackageparamJpaController{
    
    public PackageParamDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
}
