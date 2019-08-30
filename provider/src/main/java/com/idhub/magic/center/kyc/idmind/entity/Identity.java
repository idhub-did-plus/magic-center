package com.idhub.magic.center.kyc.idmind.entity;

;

public class Identity {

	public String	id;
	//The id for the identity

	public Integer	entityCount;
	//The total number of entities within this Identity

	public Boolean	active;
	//Whether this identity has been active recently

	public Object	entityTags;
	//A break down of the entity tags for the Identity

	public Integer	deviceCount;
	//The total number of Devices within this Identity

	public Integer	paymentInstrumentCount;
	//The total number of Payment Instruments within this Identity

	public Number	score;
	//A risk score for the Identity, in the range 0..100 where a lower score represents higher risk

	public Boolean	tdi;
	//Whether this identity qualifies as a Trusted Digital Identity

	public Boolean	richIdentity;
	//Whether this identity qualifies as a Rich Identity

	public String	preferredAuthenticationMechanism;
	//The preferred authentication mechanism for this Identity

	public String	preferredAuthenticationDetails;
	//The details of the preferred authentication service to use, for example, the name of the identity protection service to use

	public Integer	addressCount;
	//The total number of Addresses within this Identity

	public Integer	userAccountCount;
	//The total number of User Accounts within this Identity

	public Integer	emailAddressCount;
	//The total number of email addresses within this Identity

	public String	scoreModel;
	//The name of the model that was used to calculate the risk score for the Identity

	public Integer	phoneCount;
	//The total number of phone numbers within this Identity

	public Integer	nationalIdCount;
	//The total number of national ids (e.g SSN, passport number) within this Identity

	public Boolean	externallyProofed;
	//Whether this identity has been proofed by a third party

	public Boolean	longlived;
	//Whether this identity has existed for a reasonable period of time

	public String 	ratings;
	//A break down of the number of reputations for the Identity

	public Integer	age;
	//The number of days this Identity has existed

	public Integer	frequency;
	//The number of transactions that have been seen from this Identity

	public Integer	recency;
	//The number of days since this Identity was last seen

	public Integer	clientCount;
	//The number of clients that have seen aspects of this Identity

}
