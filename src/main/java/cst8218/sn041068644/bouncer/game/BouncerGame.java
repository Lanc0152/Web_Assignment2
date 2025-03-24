/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.sn041068644.bouncer.game;

import cst8218.sn041068644.bouncer.BouncerController;
import cst8218.sn041068644.bouncer.ejb.BouncerFacade;
import cst8218.sn041068644.bouncer.entity.Bouncer;
import cst8218.sn041068644.bouncer.util.JsfUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Startup;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.model.SelectItem;
import java.util.List;

/**
 *
 * @author sean lancaster 
 * 041068644
 * 
 * This class is used to simulate the passing of time in a bouncer game
 */
@Startup
@Singleton
@LocalBean
public class BouncerGame {
// this is a bouncerfacade instance used for transactions with the database
    @EJB private BouncerFacade bouncerFacade;
    //this effects how often the games calculations will be made
    private static final int CHANGE_RATE = 10;
    //this is a list of all bouncer objects in the database
    private List<Bouncer> bouncers;
    
    
    @PostConstruct
    public void go() {
    new Thread(new Runnable() {
        public void run() {
        // the game runs indefinitely
            while (true) {
            //update all the bouncers and save changes to the database
            bouncers = bouncerFacade.findAll();
                for (Bouncer bouncer : bouncers) {
                    bouncer.timeStep();
                    bouncerFacade.edit(bouncer);
                }
                //sleep while waiting to process the next frame of the animation
                try {
                // wake up roughly CHANGE_RATE times per second
                    Thread.sleep((long)(1.0/CHANGE_RATE*1000));
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }).start();
}
    
     
}
