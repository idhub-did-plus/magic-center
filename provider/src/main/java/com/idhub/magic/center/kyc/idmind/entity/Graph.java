package com.idhub.magic.center.kyc.idmind.entity;



public class Graph {

	Integer	entities;
	//The total number of entities in the graph

	Integer	blacklistedByClient;
	//The number of entities in the graph that are blacklisted by this client

	Integer	depth;
	//The maximum depth of the graph; note that this may be limited by the depth parameter used while retrieving

	Object	entitiesByType;
	//The number of entities broken down by Type

	Object	entitiesByReputation;
	//The number of entities broken down by Reputation

	Object	entitiesByReputationAndType;
	//The number of entities broken down by Type and Reputation

	Object	entityTags;
	//The count of entities with specific tags in the graph

	Integer	uniqueClientCount;
	//The number of unique clients that have seen entities in the graph

	Integer	entitiesWithFraudEvents;
	//The number of entities in the graph that have at least one chargeback or fraudulent refund

	Integer	overLinkedCount;
	//The number of entities in the graph that have an excessive number of linked entities

	Integer	breadth;
	//The maximum breadth, that is, the maximum number of related entities, in the graph

	Number	score;
	//The risk score of the graph, in the range 0..100 where a lower score represents higher risk

	Integer	maxEntities;
	//The maximum number of entities retrieved to generate the metrics

	Integer	chargeBackAndFraudRefundCount;
	//The total number of chargebacks and fraudulent refunds recorded within the entity graph

	Integer	queryDepth;
	//The depth (degrees of seperation to which the graph was queried to generate the metrics

	String	recentCutoffDate;
	//The java timestamp (milliseconds) after which entities are considered as being recent

	Integer	recentEntities;
	//The total number of recent entities in the graph

	Integer	recentEntitiesByType;
	//The number of recent entities broken down by Type

	Integer	recentEntitiesByReputation;
	//The number of recent entities broken down by Reputation

	Integer	recentEntitiesByReputationAndType;
	//The number of recent entities broken down by Type and Reputation

	Integer	merchantEntities;
	//The total number of entities in the graph that have been seen by the current client

	Integer	merchantEntitiesByType;
	//The number of entities in the graph that have been seen by the current client broken down by Type

	Integer	merchantEntitiesByReputation;
	//The number of entities in the graph that have been seen by the current client broken down by Reputation

	Integer	merchantEntitiesByReputationAndType;
	//The number of entities in the graph that have been seen by the current client broken down by Type and Reputation

	Integer	merchantChargeBackAndFraudRefundCount;
	//The number of chargeback and fraudulent refund in the graph that are associated with entities seen by the current client

	String	scoreModel;
}
