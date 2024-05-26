package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "animals")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_id_role_sq", allocationSize = 1, initialValue = 1)
    private Integer idRole;

    @Enumerated(EnumType.STRING)
    private ERole roleName;

    public Role(Integer idRole, ERole roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }
}