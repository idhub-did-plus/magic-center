package com.idhub.magic.infra.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class IssueAgent {
	@Id
	String id;
	String name;
	String symbol;
	
	String identity;
}
