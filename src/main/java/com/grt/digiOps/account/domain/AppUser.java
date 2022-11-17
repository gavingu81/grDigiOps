package com.grt.digiOps.account.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String username;
	private String password;
	@ManyToMany (fetch = FetchType.EAGER)
	private Collection<Role> roles= new ArrayList<>();

}
