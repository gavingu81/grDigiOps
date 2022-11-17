package com.grt.digiOps.account.api;

import com.grt.digiOps.account.domain.AppUser;
import com.grt.digiOps.account.domain.Role;
import com.grt.digiOps.account.service.AppUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserResource {
    private final AppUserService appUserService;
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getAppUsers(){
        return ResponseEntity.ok().body(appUserService.getAppUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser>saveAppUsers(@RequestBody AppUser appUser){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveAppUser(appUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
        appUserService.addRoleToAppUser(form.getAppUserName(),form.getRoleName());
        return ResponseEntity.ok().build();
    }



}

@Data
class RoleToUserForm{
    private String appUserName;
    private String roleName;
}