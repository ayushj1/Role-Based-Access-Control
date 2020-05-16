package com.rbac.services;

import java.util.*;
import com.rbac.models.*;

public class AccessController {
    private Map<String, User> users;
    private Map<String, Role> roles;
    private Map<String, Resource> resources;

    public AccessController() {
        this.users = new HashMap<User>();
        this.roles = new HashMap<Role>();
        this.resources = new HashMap<Resource>();
    }

    public void addUser(String id, String name, String email, String phone) {
        users.put(id, new User(id, name, email, phone));
    }
    
    public void addRole(String id, String name, String description) {
        roles.put(id, new Role(id, name, description));
    }
    
    public void addResource(String id, String name) {
        resources.put(id, new Resource(id, name));
    }

    public void addResourceRoleAction(String resourceId, String roleId, List<ActionType> actions) {
        Resource res = resources.get(resourceId);
        res.addRoleActions(roleId, actions);
    }

    public boolean assignRole(String userId, String roleId) {
        User user = users.get(userId);
        return user.addRole(roleId);
    }

    public boolean removeRole(String userId, String roleId) {
        User user = users.get(userId);
        return user.removeRole(roleId);
    }

    public boolean checkAccess(String userId, String resourceId, String actionStr) {
        boolean retVal = false;
        ActionType actype = ActionType.READ;
        switch(actionStr) {
            case "READ" : 
                        break;
            case "WRITE" : actype = ActionType.WRITE;
                        break;
            case "DELETE" : actype = ActionType.DELETE;
                        break;
        }

        User user = users.get(userId);
        List<String> userRoles = user.getRoles();
        
        Resource res = resources.get(resourceId);
        Map<String, List<ActionType>> roleActionMapping = res.getRoleActionMapping();

        for(String roleId : userRoles) {
            if(roleActionMapping.get(roleId) != null) {
                List<ActionType> actionList = roleActionMapping.get(roleId);
                if(actionList.indexOf(actype) > -1) {
                    retVal = true;
                    break;
                }
            }
        }
        return retVal;
    }
}