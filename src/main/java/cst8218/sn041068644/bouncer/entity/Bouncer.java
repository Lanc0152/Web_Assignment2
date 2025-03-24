/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.sn041068644.bouncer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;

/**
 *
 * @author sean lancaster 
 * 041068644
 * 
 * This class represents the entity being stored in our database
 */
@Entity
@Table(name = "bouncer")
public class Bouncer implements Serializable {

    private static final int X_LIMIT = 500;
    private static final int Y_LIMIT = 500;
    
    private static final int SIZE_LIMIT = 100;
    private static final int INITIAL_SIZE = 50;
    
    private static final int MAX_TRAVEL_LIMIT = 100;
    private static final int TRAVEL_SPEED = 10;
    
    private static final int MAX_DIR_CHANGES = 25;
    private static final int DECREASE_RATE = 5;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        
    @Min(0)
    @Max(X_LIMIT)
    private Integer x = 0;
    @Min(0)
    @Max(Y_LIMIT)
    private Integer y = 0;
    @Min(1)
    @Max(SIZE_LIMIT)
    private Integer size = INITIAL_SIZE;
    @Min(1)
//    @Max(MAX_TRAVEL_LIMIT)
    private Integer maxTravel = 0;
    
    private Integer currentTravel = INITIAL_SIZE;
    
    private Integer mvtDirection = 1;
    
    private Integer dirChangeCount = 0;
    
   
    public void setId(Long id) {
        this.id = id;
    }
    
    public void Bouncer(){
    
    }
    //this is the constructor for creating the bouncer object
    public void Bouncer(Integer x, Integer y, Integer size, Integer maxTravel, Integer currentTravel){
        
        
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxTravel = maxTravel;
        this.currentTravel = currentTravel;
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
    
    /**
    * Updates the properties to simulate the passing of one unit of time.
    */
    public void timeStep() {
        if (maxTravel > 0){
        currentTravel += mvtDirection * TRAVEL_SPEED;
            if (Math.abs(currentTravel) >= maxTravel){
            mvtDirection = -mvtDirection;
            dirChangeCount++;
                if (dirChangeCount > MAX_DIR_CHANGES){
                maxTravel -= DECREASE_RATE;
                dirChangeCount = 0;
                }
            }
        }
    }
    // this is a method used by the restful interface to easily update existing entities
    public void updates(Bouncer oldbouncer){
        if(this.getX() != null){
            oldbouncer.setX(this.x);
        }
        if(this.getY() != null){
            oldbouncer.setX(this.y);
        }
        if(this.getSize() != null){
            oldbouncer.setSize(this.size);
        }
        if(this.getCurrentTravel() != null){
            oldbouncer.setCurrentTravel(this.currentTravel);
        }
        if(this.getMaxTravel() != null){
            oldbouncer.setMaxTravel(this.maxTravel);
        }
    }

    @Override
    public String toString() {
        return "cst8218.sn041068644.bouncer.entity.Bouncer[ id=" + id + " ]";
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getMaxTravel() {
        return maxTravel;
    }

    public void setMaxTravel(Integer maxTravel) {
        this.maxTravel = maxTravel;
    }

    public Integer getCurrentTravel() {
        return currentTravel;
    }

    public void setCurrentTravel(Integer currentTravel) {
        this.currentTravel = currentTravel;
    }

    public Integer getMvtDirection() {
        return mvtDirection;
    }

    public void setMvtDirection(Integer mvtDirection) {
        this.mvtDirection = mvtDirection;
    }

    public Integer getDirChangeCount() {
        return dirChangeCount;
    }

    public void setDirChangeCount(Integer dirChangeCount) {
        this.dirChangeCount = dirChangeCount;
    }

    public Long getId() {
        return id;
    }
    
    
}
