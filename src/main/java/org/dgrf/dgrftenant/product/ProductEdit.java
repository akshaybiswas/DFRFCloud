/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.dgrftenant.product;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dgrf.cloud.response.DGRFResponseCode;
import org.dgrf.cloud.response.DGRFResponseMessage;
import org.dgrf.dgrftenant.entities.Product;
import org.dgrf.dgrftenant.service.MasterDataService;

/**
 *
 * @author dgrf-iv
 */
@Named(value = "productEdit")
@ViewScoped
public class ProductEdit implements Serializable {

    private int productId;
    private Product productBeanEdit;

    public ProductEdit() {
    }

    public void fillProductValues() {
        MasterDataService mds = new MasterDataService();
        productBeanEdit = mds.getProduct(productId);
    }
    
    public String saveProduct(){
        MasterDataService mds = new MasterDataService();
        int response = mds.editProduct(productBeanEdit);
        FacesMessage message;
        String redirectUrl;
        if (response != DGRFResponseCode.SUCCESS) {
            redirectUrl = "ProductEdit";
            DGRFResponseMessage responseMessage = new DGRFResponseMessage();
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", responseMessage.getResponseMessage(response));
        } else {
            DGRFResponseMessage responseMessage = new DGRFResponseMessage();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", responseMessage.getResponseMessage(response));
            redirectUrl = "ProductList";
            
        }
        FacesContext f = FacesContext.getCurrentInstance();
        f.getExternalContext().getFlash().setKeepMessages(true);
        f.addMessage(null, message);
        return redirectUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProductBeanEdit() {
        return productBeanEdit;
    }

    public void setProductBeanEdit(Product productBeanEdit) {
        this.productBeanEdit = productBeanEdit;
    }
    
}
