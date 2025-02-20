package com.splitwise.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "Users")
public class User extends Base{
    private String name;
    private String password;
    private String phoneNo;
    @ManyToMany
    private List<Group> groups;
}
