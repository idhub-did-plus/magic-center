package com.idhub.magic.infra.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class IssueProject{
	@Id
	String id;
	String name   ;
	String agentIdentity ;
	String status;
	Date createTime;
	IssuerInformation issuerInformation;
	TokenConfig tokenConfig;
	DeployedToken deployedToken;
}
