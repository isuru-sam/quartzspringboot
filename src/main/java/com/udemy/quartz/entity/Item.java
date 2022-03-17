package com.udemy.quartz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
	
	@Column(name="item_code")
    private String item_code;
	
	/*@Column(name="person_id")
    private String person_id;*/
	
	
	@ManyToOne
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

	
}
