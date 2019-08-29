package com.idhub.magic.center.kyc.idmind.entity;


public class GraphScore {
	Identity identity;
	String	tid;
	Graph graph;
	
	

	String	paymentReputation;
	//The reputation of the payment, if any, in the transaction

	String	simpleIdReputation;
	//The simple identity involved in this transaction

	String	deviceReputation;
	//The reputation of the device, if any, in the transaction

	String	accountReputation;
	//The reputation of the user account, if any, in the transaction

	String	billingReputation;
	//The reputation of the billing address, if any, in the transaction

	String	shippingReputation;
	//The reputation of the shipping address, if any, in the transaction
}
