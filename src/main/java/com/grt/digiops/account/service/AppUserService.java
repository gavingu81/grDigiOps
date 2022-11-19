package com.grt.digiops.account.service;

import com.grt.digiops.account.domain.AppUser;
import com.grt.digiops.account.domain.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String appUserName, String roleName);
    AppUser getAppUser(String appUserName);
    List<AppUser> getAppUsers();

}
