package com.grt.digiOps.account.service;

import com.grt.digiOps.account.domain.AppUser;
import com.grt.digiOps.account.domain.Role;
import com.grt.digiOps.account.repo.AppUserRepo;
import com.grt.digiOps.account.repo.RoleRepo;
import lombok.RequiredArgsConstructor;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service @Transactional @RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    final static Logger logger = LoggerFactory.getLogger(AppUserService.class);
    @Override
    public AppUser saveAppUser(AppUser appUser) {
        logger.info("saving new user {} to the database",appUser.getName());
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        logger.info("Saving the role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToAppUser(String appUserName, String roleName) {
        logger.info("Adding role {} to user {}",roleName,appUserName);
        AppUser appUser= appUserRepo.findByUsername(appUserName);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);

    }

    @Override
    public AppUser getAppUser(String appUserName) {
        logger.info("Fetching app user {}", appUserName);
        return appUserRepo.findByUsername(appUserName);
    }

    @Override
    public List<AppUser> getAppUsers() {
        logger.info("Fetching all users");
        return appUserRepo.findAll();
    }
}