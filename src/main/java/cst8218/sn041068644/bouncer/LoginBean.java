/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.sn041068644.bouncer;

import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Stateless
@Named("LoginBean")
@NamedQueries({
    @NamedQuery(name = "LoginBean.getPassword", query = "select password from appuser where userid = :username")})
public class LoginBean {

    private Long username;
    private String password;

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager entityManager;

    public LoginBean() {

    }

    public Long getUsername() {
        return this.username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String passwordEntry) {
        this.password = passwordEntry;  // Store the password directly entered by the user
    }
 
    public String login() {
        // Get password from DB
        String storedHashedPassword = getStoredHashedPassword(username);
        Pbkdf2PasswordHash passwordHash = CDI.current().select(Pbkdf2PasswordHash.class).get();
        if (storedHashedPassword != null && passwordHash.verify((this.password).toCharArray(), storedHashedPassword)) { 
            return "/bouncer/List";  // Send user to correct page
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid username or password"));
            return "/login/error.xhtml";  // send user to error page
        }
    }

    private String getStoredHashedPassword(Long username) {
        TypedQuery<String> query = entityManager.createQuery("SELECT u.password FROM Appuser u WHERE u.userid = :username", String.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();  // Get the stored hashed password
        } catch (Exception e) {
            return null;  // If the user does not exist, return null
        }
    }
}