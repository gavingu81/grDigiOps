package com.grt.digiops.account.service;

import com.grt.digiops.account.domain.AppUser;
import com.grt.digiops.account.domain.Role;
import com.grt.digiops.account.repo.AppUserRepo;
import com.grt.digiops.account.repo.RoleRepo;
import lombok.RequiredArgsConstructor;

//import org.apache.log4j.Logger;
import lombok.extern.slf4j.Slf4j;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service @Transactional @RequiredArgsConstructor @Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepo appUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String appUsername) throws UsernameNotFoundException {
        AppUser appUser = appUserRepo.findByUsername(appUsername);
        if (appUser == null){
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", appUsername);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
       // return new User("gavin","$2a$10$zMrjDiYY15ZDsX98g11cru3MC/VPexmwPjo/Oee/pz8ZCJ3FFILmy",authorities);

    }


    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("saving new user {} to the database",appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving the role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToAppUser(String appUserName, String roleName) {
        log.info("Adding role {} to user {}",roleName,appUserName);
        AppUser appUser= appUserRepo.findByUsername(appUserName);
        Role role = roleRepo.findByName(roleName);
        appUser.getRoles().add(role);

    }

    @Override
    public AppUser getAppUser(String appUserName) {
        log.info("Fetching app user {}", appUserName);
        return appUserRepo.findByUsername(appUserName);
    }

    @Override
    public List<AppUser> getAppUsers() {
        log.info("Fetching all users");
        return appUserRepo.findAll();
    }



}