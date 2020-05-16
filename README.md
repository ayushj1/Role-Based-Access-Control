# Role-Based-Access-Control
Role Bases Access Control

Commands Example:

  CHECK u1 re1 READ
  User u1 doesn't have access to resource re1 using Action Type READ
  ASSIGN u1 ma
  Role ma assigned to User u1
  CHECK u1 re1 READ
  User u1 has access to resource re1 using Action Type READ
  REMOVE u1 ma
  Role ma removed from User u1
  CHECK u1 re1 READ
  User u1 doesn't have access to resource re1 using Action Type READ
  
Role Based Access Control Problem Statement:

Implement a role based auth system. System should be able to assign a role to user and remove a user from the role.

Entities are USER, ACTION TYPE, RESOURCE, ROLE
ACTION TYPE defines the access level(Ex: READ, WRITE, DELETE)

Access to resources for users are controlled strictly by the role.One user can have multiple roles. Given a user, action type and resource system should be able to tell whether user has access or not.
