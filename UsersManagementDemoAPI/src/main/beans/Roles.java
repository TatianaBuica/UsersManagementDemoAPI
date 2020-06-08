package main.beans;

public enum Roles {
    ADMIN("admin"),
    USER("user");

    String role;
    Roles(String role) {
        this.role = role;
    }
}
