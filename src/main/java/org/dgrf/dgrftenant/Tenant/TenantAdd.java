/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.dgrftenant.Tenant;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dgrf.cloud.response.DGRFResponseCode;
import org.dgrf.cloud.response.DGRFResponseMessage;
import org.dgrf.dgrftenant.entities.Tenant;
import org.dgrf.dgrftenant.service.MasterDataService;

/**
 *
 * @author dgrf-iv
 */
@Named(value = "tenantAdd")
@ViewScoped
public class TenantAdd implements Serializable{

    private Tenant tenant;
    private int tenantId;
    
    public TenantAdd() {
        tenant = new Tenant();
    }
    
    public String addTenant() {
        MasterDataService mds = new MasterDataService();
        int response = mds.createTenant(tenant);
        DGRFResponseMessage responseMessage = new DGRFResponseMessage();
        FacesMessage message;
        String redirectUrl;
        
        if ( response != DGRFResponseCode.SUCCESS ) {
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", responseMessage.getResponseMessage(response));
            redirectUrl = "/Tenant/TenantAdd";
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", responseMessage.getResponseMessage(response));
            List<Tenant> tenants = mds.findTenant(tenant);
            tenant = tenants.get(0);
            redirectUrl = "/Tenant/TenantRegisterSuccess?faces-redirect=true&tenantid=" +tenant.getId();
        }
        FacesContext f = FacesContext.getCurrentInstance();
        f.getExternalContext().getFlash().setKeepMessages(true);
        f.addMessage(null, message);
        return redirectUrl;
    }
    
    public void fillTenantValues() {
       MasterDataService mds = new MasterDataService();
       tenant = mds.getTenantValues(tenantId);
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
    
    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
    
}
