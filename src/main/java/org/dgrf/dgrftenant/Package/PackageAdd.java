/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.dgrftenant.Package;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dgrf.cloud.response.DGRFResponseCode;
import org.dgrf.cloud.response.DGRFResponseMessage;

import org.dgrf.dgrftenant.entities.Packageparam;
import org.dgrf.dgrftenant.entities.Prodpackage;
import org.dgrf.dgrftenant.entities.ProdpackagePK;
import org.dgrf.dgrftenant.entities.Product;
import org.dgrf.dgrftenant.service.MasterDataService;

/**
 *
 * @author dgrf-iv
 */
@Named(value = "packageAdd")
@ViewScoped
public class PackageAdd implements Serializable {

    private int productId;
    private String productName;
    private Prodpackage packageBeanAdd;
    private List<Prodpackage> prodpackages;
    private HashMap<String, Object> packageParmaMap;

    public PackageAdd() {
    }

    public void fillAddScreen() {
        MasterDataService mds = new MasterDataService();
        Product product = mds.getProduct(productId);
        List<Prodpackage> prodPackageList = product.getProdpackageList();
        int maxPackageId = 0;

        for (Prodpackage prodpackage : prodPackageList) {
            if (prodpackage.getProdpackagePK().getId() > maxPackageId) {
                maxPackageId = prodpackage.getProdpackagePK().getId();
            }
        }

        ProdpackagePK prodpackagePK = new ProdpackagePK(maxPackageId + 1, productId);
        packageBeanAdd = new Prodpackage(prodpackagePK);
        packageParmaMap = new HashMap<>();
    }

    public String add() {
        
        MasterDataService mds = new MasterDataService();        
        int response = mds.insertIntoPackage(packageBeanAdd, productId);
        
        for (int i = 0; i < packageParmaMap.size(); i++) {
            if (i == 0) {
                Packageparam packageparamAdd = new Packageparam(packageBeanAdd.getProdpackagePK().getId(), productId, "userno");
                packageparamAdd.setParamDescription("Number of Users");
                packageparamAdd.setParamValue((String) packageParmaMap.get("userno"));
                mds.insertIntoParam(packageparamAdd);
            }
            if (i == 1) {
                Packageparam packageparamAdd = new Packageparam(packageBeanAdd.getProdpackagePK().getId(), productId, "dbsize");
                packageparamAdd.setParamDescription("Database size");
                packageparamAdd.setParamValue((String) packageParmaMap.get("dbsize"));
                mds.insertIntoParam(packageparamAdd);
            }
            if (i == 2) {
                Packageparam packageparamAdd = new Packageparam(packageBeanAdd.getProdpackagePK().getId(), productId, "traceduserno");
                packageparamAdd.setParamDescription("Number of logged in users");
                packageparamAdd.setParamValue((String) packageParmaMap.get("traceduserno"));
                mds.insertIntoParam(packageparamAdd);
            }
            if (i == 3) {
                Packageparam packageparamAdd = new Packageparam(packageBeanAdd.getProdpackagePK().getId(), productId, "wrongattemptno");
                packageparamAdd.setParamDescription("Number of incorrect password");
                packageparamAdd.setParamValue((String) packageParmaMap.get("wrongattempt"));
                mds.insertIntoParam(packageparamAdd);
            }
        }
        DGRFResponseMessage responseMessage = new DGRFResponseMessage();
        FacesMessage message;
        String redirectUrl;
        
        if (response != DGRFResponseCode.SUCCESS) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", responseMessage.getResponseMessage(response));
            redirectUrl = "PackageAdd";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", responseMessage.getResponseMessage(response));
            redirectUrl = "PackageList";
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

    public Prodpackage getPackageBeanAdd() {
        return packageBeanAdd;
    }

    public void setPackageBeanAdd(Prodpackage packageBeanAdd) {
        this.packageBeanAdd = packageBeanAdd;
    }

    public String getProductName() {
        MasterDataService mds = new MasterDataService();
        productName = mds.getProductName(productId);
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Prodpackage> getProdpackages() {
        return prodpackages;
    }

    public void setProdpackages(List<Prodpackage> prodpackages) {
        this.prodpackages = prodpackages;
    }

    public HashMap<String, Object> getPackageParmaMap() {
        return packageParmaMap;
    }

    public void setPackageParmaMap(HashMap<String, Object> packageParmaMap) {
        this.packageParmaMap = packageParmaMap;
    }

}
