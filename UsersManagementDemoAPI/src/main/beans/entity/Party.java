package main.beans.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "Party")
@Entity
public class Party {
    @Id
    @Column(unique = true, name = "name", nullable = false)
    private String name;
    @OneToOne(targetEntity = Address.class, fetch = FetchType.EAGER)
    private Address mainAddress;
    @OneToMany(targetEntity = Address.class, fetch = FetchType.LAZY)
    private List<Address> secondaryAddresses;
    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    private List<User> users;

    public Party() {}

    public Party(String name, Address mainAddress, List<Address> secondaryAddresses, List<User> users) {
        this.name = name;
        this.mainAddress = mainAddress;
        this.secondaryAddresses = secondaryAddresses;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    public List<Address> getSecondaryAddresses() {
        return secondaryAddresses;
    }

    public void setSecondaryAddresses(List<Address> secondaryAddresses) {
        this.secondaryAddresses = secondaryAddresses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
