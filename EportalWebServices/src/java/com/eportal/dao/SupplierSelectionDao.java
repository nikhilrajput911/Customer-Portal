/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.dao;

import com.eportal.entities.SupplierSelection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SupplierSelectionDao {

    HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public int saveSupplierSelection(SupplierSelection supplierSelection) {
        int id = (int) this.template.save(supplierSelection);
        return id;
    }

    public void updateSupplierSelection(SupplierSelection supplierSelection) {
        this.template.update(supplierSelection);
    }

    public void deleteSupplierSelection(SupplierSelection supplierSelection) {
        this.template.delete(supplierSelection);
    }

    public SupplierSelection getSupplierSelectionById(int id) {
        SupplierSelection supplierSelection = this.template.get(SupplierSelection.class, id);
        return supplierSelection;
    }

    public List<?> getSupplierSelection() {
        List<SupplierSelection> supplierSelection = new ArrayList<>();
        supplierSelection = this.template.loadAll(SupplierSelection.class);
        return supplierSelection;
    }

    public List<?> findByRfqId(int rfqId) {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findByRfqId", "rfqid", rfqId);
        return list;
    }

    public List<?> findBySupplierId(int id) {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findBySupplierId", "id", id);
        return list;
    }

    public List<?> findBySupplierIdAndRfpId(int rfpId, int userId) {
        String[] params = new String[2];
        params[0] = "rfqid";
        params[1] = "supplierId";

        Object[] values = new Object[2];
        values[0] = rfpId;
        values[1] = userId;

        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findBySupplierIdAndRfpId", params, values);
        return list;
    }

    public List<?> findByCreatedByAndRfpId(int rfpId, String createdby) {
        String[] params = new String[2];
        params[0] = "rfpId";
        params[1] = "createdby";

        Object[] values = new Object[2];
        values[0] = rfpId;
        values[1] = createdby;

        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findByCreatedByAndRfpId", params, values);
        return list;
    }

    public List<?> findBySupplierStatus(String status) {
        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findBySupplierWFstatus", "supplierWFstatus", status);
        return list;
    }

    public List<?> findBySupplierStatusAndSupplierId(String status, int id) {
        String[] params = new String[2];
        params[0] = "id";
        params[1] = "status";

        Object[] values = new Object[2];
        values[0] = id;
        values[1] = status;

        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findBySupplierStatusAndSupplierId", params, values);
        return list;
    }
    public List<?> findBySupplierStatusAndRfqId(String status, int id) {
        String[] params = new String[2];
        params[0] = "rfqid";
        params[1] = "status";

        Object[] values = new Object[2];
        values[0] = id;
        values[1] = status;

        List<?> list = this.template.findByNamedQueryAndNamedParam("SupplierSelection.findBySupplierStatusAndRfqId", params, values);
        return list;
    }
    public List<?> findBySupplierStatusDistinctRfqId(String status)
    {
        List<?> list = this.template.findByCriteria(
                DetachedCriteria.forClass(SupplierSelection.class)
                .add(Restrictions.eq("supplierWFstatus", status))
                .setProjection(Projections.distinct(Projections.property("bPaasWorkOrderRFQHeaderRFQID")))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY));
        
        return list;
    }
    public List<?> findBySupplierStatusAndSupplierIdDistinctRfqid(String status, int supplierId)
    {
        List<?> list = this.template.findByCriteria(
                DetachedCriteria.forClass(SupplierSelection.class)
                .add(Restrictions.eq("supplierWFstatus", status))
                .add(Restrictions.eq("bPaasSupplierUserTableid.id", supplierId))
                .setProjection(Projections.property("bPaasWorkOrderRFQHeaderRFQID")));
        
        return list;
    }
    public List<?> findDistinctRfqId()
    {
        List<?> list = this.template.findByCriteria(
                DetachedCriteria.forClass(SupplierSelection.class)
                        .setProjection(Projections.distinct(Projections.property("bPaasWorkOrderRFQHeaderRFQID"))));
        
        return list;
    }
    public List<?> findByLeftJoinOnSupplierSelection()
    {
        List<?> list = this.template.find("from SupplierSelection supplier RIGHT JOIN supplier.bPaasWorkOrderRFQHeaderRFQID");
        
        return list;
    }
}
