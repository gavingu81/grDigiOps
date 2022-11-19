package com.grt.digiops.account.repo;

import com.grt.digiops.account.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String appUserName);
}
