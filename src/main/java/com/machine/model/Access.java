package com.machine.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "access")
@NamedQueries({
    @NamedQuery(name = "Access.findAll", query = "SELECT a FROM Access a"),
    @NamedQuery(name = "Access.findById", query = "SELECT a FROM Access a WHERE a.id = :id"),
    @NamedQuery(name = "Access.findByAccessdate", query = "SELECT a FROM Access a WHERE a.accessdate = :accessdate"),
    @NamedQuery(name = "Access.findByPerioudTime", query = "SELECT a FROM Access a WHERE a.accessdate >= :from and a.accessdate <= :end"),
    @NamedQuery(name = "Access.findByAccessnumber", query = "SELECT a FROM Access a WHERE a.accessnumber = :accessnumber")})
public class Access implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "accessdate")
    @Temporal(TemporalType.DATE)
    private Date accessdate;
    
    @Basic(optional = false)
    @Column(name = "accessnumber")
    private int accessnumber;

    public Access() {
    }

    public Access(Integer id) {
        this.id = id;
    }

    public Access(Integer id, Date accessdate, int accessnumber) {
        this.id = id;
        this.accessdate = accessdate;
        this.accessnumber = accessnumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAccessdate() {
        return accessdate;
    }

    public void setAccessdate(Date accessdate) {
        this.accessdate = accessdate;
    }

    public int getAccessnumber() {
        return accessnumber;
    }

    public void setAccessnumber(int accessnumber) {
        this.accessnumber = accessnumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Access)) {
            return false;
        }
        Access other = (Access) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Access[ id=" + id + " ]";
    }
    
}

