package com.shintaro.cardinal.generic;

import java.time.LocalDateTime;

/*
 * By: Juliano Lira(ShintaroBRL) 
 * 
 */

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Entity {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Type(type="org.hibernate.type.UUIDCharType")
    public UUID id;
	
	@NotNull
	private boolean active;
	
	private LocalDateTime createdAt = LocalDateTime.now();

}
