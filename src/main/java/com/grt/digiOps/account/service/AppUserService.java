package com.grt.digiOps.account.service;

import com.grt.digiOps.account.domain.AppUser;
import com.grt.digiOps.account.domain.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String appUserName, String roleName);
    AppUser getAppUser(String appUserName);
    List<AppUser> getAppUsers();

}
