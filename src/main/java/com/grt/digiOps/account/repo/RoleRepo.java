package com.grt.digiOps.account.repo;

import com.grt.digiOps.account.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
