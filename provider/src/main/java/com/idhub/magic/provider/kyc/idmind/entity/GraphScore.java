package com.idhub.magic.provider.kyc.idmind.entity;


public class GraphScore {
	public Identity identity;
	public String	tid;
	public Graph graph;
	
	

	public String	paymentReputation;
	//The reputation of the payment, if any, in the transaction

	public String	simpleIdReputation;
	//The simple identity involved in this transaction

	public String	deviceReputation;
	//The reputation of the device, if any, in the transaction

	public String	accountReputation;
	//The reputation of the user account, if any, in the transaction

	public String	billingReputation;
	//The reputation of the billing address, if any, in the transaction

	public String	shippingReputation;
	//The reputation of the shipping address, if any, in the transaction
}
