package com.bolehunt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class User implements Serializable {
    
    private Integer userId;

    private String username;
    
    private String password;

    private String name;
    
    private boolean enabled;

    private List<Role> roles = new ArrayList<>();

}
