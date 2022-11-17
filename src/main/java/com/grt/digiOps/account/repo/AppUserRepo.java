package com.grt.digiOps.account.repo;

import com.grt.digiOps.account.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String appUserName);
}
