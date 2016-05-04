package com.machine.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByCreateddate", query = "SELECT p FROM Product p WHERE p.createddate = :createddate"),
    @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image"),
    @NamedQuery(name = "Product.findByZoomImage", query = "SELECT p FROM Product p WHERE p.zoomImage = :zoomImage"),
    @NamedQuery(name = "Product.findByCategoryId", query = "SELECT p FROM Product p WHERE p.categoryId.id = :cateId"),
    @NamedQuery(name = "Product.searchProduct", query = "SELECT p FROM Product p WHERE p.name like :keyword"),
    @NamedQuery(name = "Product.findByPriorityOrder", query = "SELECT p FROM Product p WHERE p.priorityOrder = :priorityOrder")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "zoomImage")
    private String zoomImage;
    
    @Column(name = "priorityOrder")
    private int priorityOrder;
    
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @ManyToOne(optional = false,fetch=FetchType.EAGER)
    private Category categoryId;
    
    

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }


    public Product(Integer id, String name, String description, Date createddate, String image, String zoomImage,
			int priorityOrder, Category categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createddate = createddate;
		this.image = image;
		this.zoomImage = zoomImage;
		this.priorityOrder = priorityOrder;
		this.categoryId = categoryId;
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
    

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
        this.name = name;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getZoomImage() {
        return zoomImage;
    }

    public void setZoomImage(String zoomImage) {
        this.zoomImage = zoomImage;
    }

    public int getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(int priorityOrder) {
        this.priorityOrder = priorityOrder;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Product[ id=" + id + " ]";
    }
    
}
