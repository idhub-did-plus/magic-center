package com.idhub.magic.infra.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
@Entity
public class IssuranceRecord {
	@Id
	String id;
	Date time;
	String projectId;
	String tokenAddress;
	
	

}
