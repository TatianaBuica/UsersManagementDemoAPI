package main.beans.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name = "User")
@Entity(name = "User")
public class User {
    @Id
    @Column(unique = true, name = "cnp", precision = 14, nullable = false)
    @Size(max = 14)
    private String cnp;
    @Column(unique = true, name = "email", nullable = false)
    @Size(min = 5)
    private String email;
    private String fName;
    private String lName;
    private String address;
    private String password;

    public User() {}

    public User(String cnp, String email, String fName, String lName, String address, String password) {
        this.cnp = cnp;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
