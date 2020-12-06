package edu.miu.cs.neptune.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {

    @Id
    @Enumerated(EnumType.STRING)
    private RoleCode code;
    private String name;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable
    private List<User> users = new ArrayList<>();
}
