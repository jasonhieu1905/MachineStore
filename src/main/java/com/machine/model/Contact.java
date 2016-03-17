package com.machine.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
    @NamedQuery(name = "Contact.findByAddress", query = "SELECT c FROM Contact c WHERE c.address = :address"),
    @NamedQuery(name = "Contact.findByPhone", query = "SELECT c FROM Contact c WHERE c.phone = :phone"),
    @NamedQuery(name = "Contact.findByEmail", query = "SELECT c FROM Contact c WHERE c.email = :email"),
    @NamedQuery(name = "Contact.findByMapX", query = "SELECT c FROM Contact c WHERE c.mapX = :mapX"),
    @NamedQuery(name = "Contact.findByMapY", query = "SELECT c FROM Contact c WHERE c.mapY = :mapY"),
    @NamedQuery(name = "Contact.findByWorkingtime", query = "SELECT c FROM Contact c WHERE c.workingtime = :workingtime"),
    @NamedQuery(name = "Contact.findByIsoimage", query = "SELECT c FROM Contact c WHERE c.isoimage = :isoimage")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    
    @Basic(optional = false)
    @Column(name = "email")
    private int email;
    
    @Basic(optional = false)
    @Column(name = "map_x")
    private String mapX;
    
    @Basic(optional = false)
    @Column(name = "map_y")
    private String mapY;
    
    @Basic(optional = false)
    @Column(name = "workingtime")
    private String workingtime;
    
    @Basic(optional = false)
    @Column(name = "isoimage")
    private String isoimage;

    public Contact() {
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Contact(Integer id, String name, String address, String phone, int email, String mapX, String mapY, String workingtime, String isoimage) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.mapX = mapX;
        this.mapY = mapY;
        this.workingtime = workingtime;
        this.isoimage = isoimage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public String getWorkingtime() {
        return workingtime;
    }

    public void setWorkingtime(String workingtime) {
        this.workingtime = workingtime;
    }

    public String getIsoimage() {
        return isoimage;
    }

    public void setIsoimage(String isoimage) {
        this.isoimage = isoimage;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication2.Contact[ id=" + id + " ]";
    }
    
}
