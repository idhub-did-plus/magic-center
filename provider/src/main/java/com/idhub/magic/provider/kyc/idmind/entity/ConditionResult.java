package com.idhub.magic.provider.kyc.idmind.entity;

public class ConditionResult {
	public boolean fired;
/*	boolean
	optional	Whether the condition fired or not*/
	public String test;
	/*string
	optional	The ID of the security test or the key of the transaction data to which the condition applied*/
	public boolean waitingForData;
	/*boolean
	optional	Indicates that the result is waiting for an asynchronous response from the customer and/or third party service.*/
	public String details;
	/*string
	optional	Te*/
	public Number ts;
	public String stage;
	public Object additional;
	public Condition condition;
}
class Condition{
	public String left;
	public Boolean right;
	public String operator;
}
