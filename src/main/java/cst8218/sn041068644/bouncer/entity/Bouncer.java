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
    @Max(Y_LIMIT)
    private int y;
    @Min(1)
    @Max(SIZE_LIMIT)
    private int size;
    @Min(1)
    @Max(MAX_TRAVEL_LIMIT)
    private int maxTravel;
    
    private int currentTravel;
    
    private int mvtDirection;
    
    private int dirChangeCount;
    
    private final int X_LIMIT = 50;
    private final int Y_LIMIT = 50;
    
    private final int SIZE_LIMIT = 100;
    private final int INITIAL_SIZE = 50;
    
    private final int MAX_TRAVEL_LIMIT = 100;
    private final int TRAVEL_SPEED = 1;
    
    private final int MAX_DIR_CHANGES = 25;
    private final int DECREASE_RATE = 1;

    public void setId(Long id) {
        this.id = id;
    }
    
    public void Bouncer(){
    
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

    public int getCurrentTravel() {
        return currentTravel;
    }

    public void setCurrentTravel(int currentTravel) {
        this.currentTravel = currentTravel;
    }

    public int getMvtDirection() {
        return mvtDirection;
    }

    public void setMvtDirection(int mvtDirection) {
        this.mvtDirection = mvtDirection;
    }

    public int getDirChangeCount() {
        return dirChangeCount;
    }

    public void setDirChangeCount(int dirChangeCount) {
        this.dirChangeCount = dirChangeCount;
    }
    
    
}
