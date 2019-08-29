package com.idhub.magic.center.kyc.idmind.entity;

;

public class Identity {

	String	id;
	//The id for the identity

	Integer	entityCount;
	//The total number of entities within this Identity

	Boolean	active;
	//Whether this identity has been active recently

	Object	entityTags;
	//A break down of the entity tags for the Identity

	Integer	deviceCount;
	//The total number of Devices within this Identity

	Integer	paymentInstrumentCount;
	//The total number of Payment Instruments within this Identity

	Number	score;
	//A risk score for the Identity, in the range 0..100 where a lower score represents higher risk

	Boolean	tdi;
	//Whether this identity qualifies as a Trusted Digital Identity

	Boolean	richIdentity;
	//Whether this identity qualifies as a Rich Identity

	String	preferredAuthenticationMechanism;
	//The preferred authentication mechanism for this Identity

	String	preferredAuthenticationDetails;
	//The details of the preferred authentication service to use, for example, the name of the identity protection service to use

	Integer	addressCount;
	//The total number of Addresses within this Identity

	Integer	userAccountCount;
	//The total number of User Accounts within this Identity

	Integer	emailAddressCount;
	//The total number of email addresses within this Identity

	String	scoreModel;
	//The name of the model that was used to calculate the risk score for the Identity

	Integer	phoneCount;
	//The total number of phone numbers within this Identity

	Integer	nationalIdCount;
	//The total number of national ids (e.g SSN, passport number) within this Identity

	Boolean	externallyProofed;
	//Whether this identity has been proofed by a third party

	Boolean	longlived;
	//Whether this identity has existed for a reasonable period of time

	Object	ratings;
	//A break down of the number of reputations for the Identity

	Integer	age;
	//The number of days this Identity has existed

	Integer	frequency;
	//The number of transactions that have been seen from this Identity

	Integer	recency;
	//The number of days since this Identity was last seen

	Integer	clientCount;
	//The number of clients that have seen aspects of this Identity

}
