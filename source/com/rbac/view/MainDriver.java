package com.rbac.view;

import com.rbac.services.AccessController;
import com.rbac.models.ActionType;
import java.util.*;

public class MainDriver {
    public static void main(String[] args) {
        try {
            AccessController accessCtrlr = new AccessController();

            accessCtrlr.addUser("u1", "Ayush", "Ayush@test.co", "9292929292");
            accessCtrlr.addUser("u2", "Shubha", "Shubha@test.co", "91919191");
            accessCtrlr.addUser("u3", "Aarju", "Aarju@test.co", "9090909090");
            accessCtrlr.addUser("u4", "Amresh", "Amresh@test.co", "9393939393");
            accessCtrlr.addUser("u5", "Sushama", "Sushama@test.co", "9494949494");

            accessCtrlr.addRole("sa", "Super Admin", "Super Administrator - generally has all access");
            accessCtrlr.addRole("ad", "Admin", "Administrator - generally has all except delete access");
            accessCtrlr.addRole("ma", "Manager", "Manager - generally only has read access");

            accessCtrlr.addResource("re1", "Resource 1");
            accessCtrlr.addResource("re2", "Resource 2");
            accessCtrlr.addResource("re3", "Resource 3");
            accessCtrlr.addResource("re4", "Resource 4");

            accessCtrlr.addResourceRoleAction("re1", "sa", new ArrayList<ActionType>(Arrays.asList(ActionType.READ, ActionType.WRITE, ActionType.DELETE)));
            accessCtrlr.addResourceRoleAction("re1", "ad", new ArrayList<ActionType>(Arrays.asList(ActionType.READ, ActionType.WRITE)));
            accessCtrlr.addResourceRoleAction("re1", "ma", new ArrayList<ActionType>(Arrays.asList(ActionType.READ)));
            accessCtrlr.addResourceRoleAction("re2", "sa", new ArrayList<ActionType>(Arrays.asList(ActionType.READ, ActionType.WRITE)));
            accessCtrlr.addResourceRoleAction("re2", "ad", new ArrayList<ActionType>(Arrays.asList(ActionType.READ)));
            accessCtrlr.addResourceRoleAction("re2", "ma", new ArrayList<ActionType>());
            accessCtrlr.addResourceRoleAction("re3", "sa", new ArrayList<ActionType>(Arrays.asList(ActionType.READ, ActionType.DELETE)));
            accessCtrlr.addResourceRoleAction("re3", "ad", new ArrayList<ActionType>(Arrays.asList(ActionType.READ, ActionType.WRITE)));
            accessCtrlr.addResourceRoleAction("re3", "ma", new ArrayList<ActionType>(Arrays.asList(ActionType.READ)));
            accessCtrlr.addResourceRoleAction("re4", "sa", new ArrayList<ActionType>(Arrays.asList(ActionType.WRITE, ActionType.DELETE)));
            accessCtrlr.addResourceRoleAction("re4", "ad", new ArrayList<ActionType>(Arrays.asList(ActionType.WRITE)));
            accessCtrlr.addResourceRoleAction("re4", "ma", new ArrayList<ActionType>(Arrays.asList(ActionType.READ)));

            Scanner scanner = new Scanner(System.in);
            while(true) {
                String command = scanner.nextLine();
                String[] commands = command.split(" ");
                String commandType = commands[0];
                boolean retVal = false;
                switch(commandType) {
                    case "ASSIGN":
                        retVal = accessCtrlr.assignRole(commands[1], commands[2]);
                        if(retVal)
                            System.out.println("Role " + commands[2] + " assigned to User " + commands[1]);
                        else 
                            System.out.println("Role Assignment failed");
                        break;
                    case "REMOVE":
                        retVal = accessCtrlr.removeRole(commands[1], commands[2]);
                        if(retVal)
                            System.out.println("Role " + commands[2] + " removed from User " + commands[1]);
                        else 
                            System.out.println("Role Removal failed");
                        break;
                    case "CHECK":
                        retVal = accessCtrlr.checkAccess(commands[1], commands[2], commands[3]);
                        if(retVal)
                            System.out.println("User " + commands[1] + " has access to resource " + commands[2] + " using Action Type " + commands[3]);
                        else 
                            System.out.println("User " + commands[1] + " doesn't have access to resource " + commands[2] + " using Action Type " + commands[3]);
                        break;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}