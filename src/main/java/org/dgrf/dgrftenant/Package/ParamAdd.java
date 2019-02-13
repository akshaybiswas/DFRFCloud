/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.dgrftenant.Package;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dgrf.cloud.response.DGRFResponseCode;
import org.dgrf.cloud.response.DGRFResponseMessage;
import org.dgrf.dgrftenant.entities.Packageparam;
import org.dgrf.dgrftenant.entities.PackageparamPK;
import org.dgrf.dgrftenant.service.MasterDataService;

/**
 *
 * @author dgrf
 */
@Named(value = "paramAdd")
@ViewScoped
public class ParamAdd implements Serializable{

    private int productId;
    private int packageId;
    private String productName;
    private String packageName;
    private Packageparam packageparamAdd;
    private PackageparamPK ppk;
    
    public ParamAdd() {
    }
    
    public void fillAddScreen() {
        MasterDataService mds = new MasterDataService();
        ppk = new PackageparamPK(packageId, productId, "Param Key");
        packageparamAdd = new Packageparam(ppk);
        productName = mds.getProductName(productId);
        packageName = mds.getPackageName(packageId, productId);
    }
    
     public String add() {
         DGRFResponseMessage responseMessage = new DGRFResponseMessage();
        MasterDataService mds = new MasterDataService();
        packageparamAdd.setPackageparamPK(ppk);
        int response = mds.insertIntoParam(packageparamAdd);
        FacesMessage message;
        String redirectUrl;
        
        if ( response != DGRFResponseCode.SUCCESS ) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", responseMessage.getResponseMessage(response));
            redirectUrl = "ParamAdd";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Success", responseMessage.getResponseMessage(response));
            redirectUrl = "ParamList";
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

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Packageparam getPackageparamAdd() {
        return packageparamAdd;
    }

    public void setPackageparamAdd(Packageparam packageparamAdd) {
        this.packageparamAdd = packageparamAdd;
    }

    public PackageparamPK getPpk() {
        return ppk;
    }

    public void setPpk(PackageparamPK ppk) {
        this.ppk = ppk;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
     
}
