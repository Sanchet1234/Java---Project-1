package edu.ccrm.domain;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String fullName;
    private String email;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private boolean active;

    public Person(String id, String fullName, String email) {
        assert id != null : "ID must not be null";
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
        this.active = true;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }
    public boolean isActive() { return active; }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        this.updatedDate = LocalDate.now();
    }
    public void setEmail(String email) {
        this.email = email;
        this.updatedDate = LocalDate.now();
    }
    public void deactivate() {
        this.active = false;
        this.updatedDate = LocalDate.now();
    }

    public abstract String getProfile();

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", getClass().getSimpleName(), fullName, email);
    }
}
