/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.dao;

import com.eportal.entities.WorkOrderComment;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class WorkOrderCommentDao {

    HibernateTemplate template;

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public int saveComment(WorkOrderComment comment) {
        int id = (int) this.template.save(comment);
        return id;
    }

    public void updateComment(WorkOrderComment comment) {
        this.template.update(comment);
    }

    public void deleteComment(WorkOrderComment comment) {
        this.template.delete(comment);
    }

    public WorkOrderComment getCommentById(int id) {
        WorkOrderComment comment = this.template.get(WorkOrderComment.class, id);
        return comment;
    }

    public List<?> getComments() {
        List<WorkOrderComment> comment = new ArrayList<>();
        comment = this.template.loadAll(WorkOrderComment.class);
        return comment;
    }

    public List<?> findLatestCommentByApprovalId(int approvalId) {

        System.out.println(approvalId);

        List<Object> results = this.template.findByCriteria(
                DetachedCriteria.forClass(WorkOrderComment.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.groupProperty("bpaasWorkorderselectedapproverId"))
                        .add(Projections.max("id")))
                .add(Restrictions.eq("bpaasWorkorderselectedapproverId.id", approvalId)));

//        System.out.println("list size: " + results.size());
//        System.out.println(results);
        
        List<?> list = null;
        
        if(!results.isEmpty())
        {
            Object[] row = (Object[]) results.get(0);
//            System.out.println(row.length);
//            System.out.println(row[0] + " : " + row[1]);
            list = this.template.findByNamedQueryAndNamedParam("WorkOrderComment.findById", "id", row[1]);
//            System.out.println("list size: " + list.size());
            
        }
        return list;
    }
}
