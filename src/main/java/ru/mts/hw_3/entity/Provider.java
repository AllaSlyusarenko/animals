package ru.mts.hw_3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "provider")
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idProvider;
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "phone")
    private String phone;
    @JoinColumn(name = "created")
    private LocalDate created;
    @JoinColumn(name = "updated")
    private LocalDate updated;

    @ManyToMany
    @JoinTable(name = "animals_providers",
            joinColumns = @JoinColumn(name = "id_provider"),
            inverseJoinColumns = @JoinColumn(name = "id_animal_type"))
    private Set<AnimalType> animalTypes;

    public Provider(int idProvider, String name, String phone, LocalDate created, LocalDate updated) {
        this.idProvider = idProvider;
        this.name = name;
        this.phone = phone;
        this.created = created;
        this.updated = updated;
    }

    public Provider() {

    }

    public int getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(int idProvider) {
        this.idProvider = idProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;

        Provider provider = (Provider) o;
        return idProvider == provider.idProvider && Objects.equals(name, provider.name) && Objects.equals(phone, provider.phone)
                && Objects.equals(created, provider.created) && Objects.equals(updated, provider.updated);
    }

    @Override
    public int hashCode() {
        int result = idProvider;
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(phone);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        return result;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}