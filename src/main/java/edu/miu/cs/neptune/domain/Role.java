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

    public Role() {
    }

    public Role(RoleCode code, String name) {
        this.code = code;
        this.name = name;
    }

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

}
