package com.bolehunt.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private Integer roleId;
    private String roleName;

}
