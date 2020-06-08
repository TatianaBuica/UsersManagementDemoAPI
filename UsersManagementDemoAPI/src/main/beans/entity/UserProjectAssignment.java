package main.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "UserProjectAssignment")
@Entity(name = "UserProjectAssignment")
public class UserProjectAssignment {
    @Id
    @Column(unique = true, name = "cnp", nullable = false)
    private String cnp;
    @Column(name = "partyName", nullable = false)
    private String partyName;
    @Column(name = "role", nullable = false)
    private String role;

    public UserProjectAssignment() {}

    public UserProjectAssignment(String cnp, String partyName, String role) {
        this.cnp = cnp;
        this.partyName = partyName;
        this.role = role;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
