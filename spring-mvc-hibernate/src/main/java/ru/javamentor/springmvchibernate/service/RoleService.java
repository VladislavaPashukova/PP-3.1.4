package ru.javamentor.springmvchibernate.service;

import ru.javamentor.springmvchibernate.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role>  getAllRole();
    void addRole(Role role);
    Set<Role> findRolesByName(String roleName);
}
