/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.sn041068644.bouncer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;

/**
 *
 * @author seans
 */
@Entity
public class Bouncer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Min(0)
    @Max(X_LIMIT)
    private int x;
    @Min(0)
    @Max(y_LIMIT)
    private int y;
    @Min(1)
    @Max(size_LIMIT)
    private int size;
    @Min(1)
    @Max(maxTravel_LIMIT)
    private int maxTravel;
    
    private final int X_LIMIT = 50;
    private final int y_LIMIT = 50;
    private final int size_LIMIT = 100;
    private final int maxTravel_LIMIT = 100;

    public void setId(Long id) {
        this.id = id;
    }
    
    public void Bouncer(int x, int y, int size, int maxTravel){
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxTravel = maxTravel;
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
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.sn041068644.bouncer.entity.Bouncer[ id=" + id + " ]";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxTravel() {
        return maxTravel;
    }

    public void setMaxTravel(int maxTravel) {
        this.maxTravel = maxTravel;
    }
    
    
}
