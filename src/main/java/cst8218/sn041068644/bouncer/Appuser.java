/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.sn041068644.bouncer;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mrfar
 */
@Entity
@Table(name = "appuser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a"),
    @NamedQuery(name = "Appuser.findByUserid", query = "SELECT a FROM Appuser a WHERE a.userid = :userid")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private Long userid;
    @Size(max = 255)
    @Column(name = "`GROUP`")
    private String group;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;

    public Appuser() {
    }

    public Appuser(Long userid) {
        this.userid = userid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String passwordEntry) {
        Pbkdf2PasswordHash passwordHash = CDI.current().select(Pbkdf2PasswordHash.class).get();
        Map<String, String> parameters = new HashMap<>();
        passwordHash.initialize(parameters);
    this.password = passwordHash.generate(passwordEntry.toCharArray());
}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.sn041068644.bouncer.Appuser[ userid=" + userid + " ]";
    }
    
}
