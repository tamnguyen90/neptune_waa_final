package edu.miu.cs.neptune.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleCode code;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
