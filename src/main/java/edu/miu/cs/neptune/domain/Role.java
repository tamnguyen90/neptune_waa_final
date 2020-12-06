package edu.miu.cs.neptune.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleCode code;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
