package com.apisec.userdetail;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Document
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id= UUID.randomUUID().toString();
    private Collection<User> users;
    private String name;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public String getRoleId() {
        return id;
    }

    public void setRoleId(String roleId) {
        this.id = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
