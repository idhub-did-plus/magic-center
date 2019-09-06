package com.idhub.magic.provider.kyc.idmind.entity;



public class Graph {

	public Integer	entities;
	//The total number of entities in the graph

	public Integer	blacklistedByClient;
	//The number of entities in the graph that are blacklisted by this client

	public Integer	depth;
	//The maximum depth of the graph; note that this may be limited by the depth parameter used while retrieving

	public Object	entitiesByType;
	//The number of entities broken down by Type

	public Object	entitiesByReputation;
	//The number of entities broken down by Reputation

	public Object	entitiesByReputationAndType;
	//The number of entities broken down by Type and Reputation

	public Object	entityTags;
	//The count of entities with specific tags in the graph

	public Integer	uniqueClientCount;
	//The number of unique clients that have seen entities in the graph

	public Integer	entitiesWithFraudEvents;
	//The number of entities in the graph that have at least one chargeback or fraudulent refund

	public Integer	overLinkedCount;
	//The number of entities in the graph that have an excessive number of linked entities

	public Integer	breadth;
	//The maximum breadth, that is, the maximum number of related entities, in the graph

	public Number	score;
	//The risk score of the graph, in the range 0..100 where a lower score represents higher risk

	public Integer	maxEntities;
	//The maximum number of entities retrieved to generate the metrics

	public Integer	chargeBackAndFraudRefundCount;
	//The total number of chargebacks and fraudulent refunds recorded within the entity graph

	public Integer	queryDepth;
	//The depth (degrees of seperation to which the graph was queried to generate the metrics

	public String	recentCutoffDate;
	//The java timestamp (milliseconds) after which entities are considered as being recent

	public Integer	recentEntities;
	//The total number of recent entities in the graph

	public Integer	recentEntitiesByType;
	//The number of recent entities broken down by Type

	public Integer	recentEntitiesByReputation;
	//The number of recent entities broken down by Reputation

	public Integer	recentEntitiesByReputationAndType;
	//The number of recent entities broken down by Type and Reputation

	public Integer	merchantEntities;
	//The total number of entities in the graph that have been seen by the current client

	public Integer	merchantEntitiesByType;
	//The number of entities in the graph that have been seen by the current client broken down by Type

	public Integer	merchantEntitiesByReputation;
	//The number of entities in the graph that have been seen by the current client broken down by Reputation

	public Integer	merchantEntitiesByReputationAndType;
	//The number of entities in the graph that have been seen by the current client broken down by Type and Reputation

	public Integer	merchantChargeBackAndFraudRefundCount;
	//The number of chargeback and fraudulent refund in the graph that are associated with entities seen by the current client

	public String	scoreModel;
}
