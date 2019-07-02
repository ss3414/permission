package com.model;

import lombok.Data;

@Data
public class Permission {

    private Integer id;

    private Integer permissionStatus;

    private String permissionName;

    private String permissionUrl;

}
