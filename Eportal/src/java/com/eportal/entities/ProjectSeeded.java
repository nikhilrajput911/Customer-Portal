/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eportal.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "NG_CP_projectseeded")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectSeeded.findAll", query = "SELECT p FROM ProjectSeeded p"),
    @NamedQuery(name = "ProjectSeeded.findByPid", query = "SELECT p FROM ProjectSeeded p WHERE p.pid = :pid"),
    @NamedQuery(name = "ProjectSeeded.findByProjectname", query = "SELECT p FROM ProjectSeeded p WHERE p.projectname = :projectname"),
    @NamedQuery(name = "ProjectSeeded.findByCustomerId", query = "SELECT p FROM ProjectSeeded p WHERE p.bpaasCustomerseededCid.cid = :cid and p.projectstatus = 'Active'"),
    @NamedQuery(name = "ProjectSeeded.findByProjectcode", query = "SELECT p FROM ProjectSeeded p WHERE p.projectcode = :projectcode")})
public class ProjectSeeded implements Serializable {

    @JoinColumn(name = "NG_CP_customerseeded_cid", referencedColumnName = "cid")
    @ManyToOne
    private CustomerSeeded bpaasCustomerseededCid;
    @OneToMany(mappedBy = "bpaasProjectseededPid")
    private Collection<CustomerProjectMapping> customerProjectMappingCollection;
//    @OneToMany(mappedBy = "bpaasProjectseededPid")
//    private Collection<CustomerSubUser> customerSubUserCollection;
    private static final long serialVersionUID = 1L;
    @Lob
    @Size(max = 65535)
    @Column(name = "projectdescription")
    private String projectdescription;
    @Size(max = 20)
    @Column(name = "projectstatus")
    private String projectstatus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pid")
    private Integer pid;
    @Size(max = 100)
    @Column(name = "projectname")
    private String projectname;
    @Size(max = 50)
    @Column(name = "projectcode")
    private String projectcode;

    public ProjectSeeded() {
    }

    public ProjectSeeded(Integer pid) {
        this.pid = pid;
    }

    public String getProjectdescription() {
        return projectdescription;
    }

    public void setProjectdescription(String projectdescription) {
        this.projectdescription = projectdescription;
    }

    public String getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(String projectstatus) {
        this.projectstatus = projectstatus;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectSeeded)) {
            return false;
        }
        ProjectSeeded other = (ProjectSeeded) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eportal.entities.ProjectSeeded[ pid=" + pid + " ]";
    }

//    @XmlTransient
//    public Collection<CustomerSubUser> getCustomerSubUserCollection() {
//        return customerSubUserCollection;
//    }
//
//    public void setCustomerSubUserCollection(Collection<CustomerSubUser> customerSubUserCollection) {
//        this.customerSubUserCollection = customerSubUserCollection;
//    }
    @XmlTransient
    public Collection<CustomerProjectMapping> getCustomerProjectMappingCollection() {
        return customerProjectMappingCollection;
    }

    public void setCustomerProjectMappingCollection(Collection<CustomerProjectMapping> customerProjectMappingCollection) {
        this.customerProjectMappingCollection = customerProjectMappingCollection;
    }

    public CustomerSeeded getBpaasCustomerseededCid() {
        return bpaasCustomerseededCid;
    }

    public void setBpaasCustomerseededCid(CustomerSeeded bpaasCustomerseededCid) {
        this.bpaasCustomerseededCid = bpaasCustomerseededCid;
    }
}
