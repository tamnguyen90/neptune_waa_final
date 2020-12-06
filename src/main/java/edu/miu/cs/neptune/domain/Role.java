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

    public RoleCode getCode() {
        return code;
    }

    public Role setCode(RoleCode code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Role setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
