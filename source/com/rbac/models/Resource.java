package com.rbac.models;

import java.util.*;

public class Resource {
    private String id;
    private String name;
    private Map<String, List<ActionType>> roleActionMapping;

    public Resource(String id, String name) {
        this.id = id;
        this.name = name;
        this.roleActionMapping = new HashMap<String, List<ActionType>>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<ActionType>> getRoleActionMapping() {
        return roleActionMapping;
    }

    public void addRoleActions(String roleId, List<ActionType> actions) {
        if(this.roleActionMapping.get(roleId) == null) {
            this.roleActionMapping.put(roleId, new ArrayList<ActionType>());
        }
        this.roleActionMapping.get(roleId).addAll(actions);
    }
}
