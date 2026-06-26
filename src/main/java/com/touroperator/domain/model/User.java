package com.touroperator.domain.model;

public class User {

    private Long id;
    private String email;
    private String passwordHash;
    private Role role;
    private boolean active;

    public User(Long id, String email, String passwordHash, Role role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = true;
    }

    public boolean isAdmin() {
        return role == Role.ROLE_ADMIN;
    }

    public void deactivate() {
        this.active = false;
    }

    public void changeEmail(String newEmail) {
        if (newEmail == null || newEmail.isBlank()) {
            throw new IllegalArgumentException("Email не может быть пустым");
        }
        this.email = newEmail;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
    public boolean isActive() { return active; }
}