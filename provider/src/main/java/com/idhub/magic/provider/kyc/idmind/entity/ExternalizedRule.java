package com.idhub.magic.provider.kyc.idmind.entity;

public class ExternalizedRule {
	public String name;
	/*string	The rule name*/
	public String description;
	/*string	Details of the evaluation of this rule for the current transaction*/
	public String details;
	/*string
	optional	The rule description*/
	public FraudPolicyResult resultCode;
	/*FraudPolicyResult	Result of rule. Possible values are:
	ACCEPT
	MANUAL_REVIEW
	DENY*/
	public ConditionResult[] testResults;
	/*array[ConditionResult]
	optional	The results of the individual assertions of the rule*/
	public int ruleId;
	/*integer	The unique rule identifier*/
}
