package com.idhub.magic.provider.kyc.idmind.entity;

public class AutomatedReviewEngineResult {
	public ReviewResult result;
	/*ReviewResult	Result of rule. Possible values are:
	ERROR
	NO_POLICY
	DISABLED
	FILTERED
	PENDING
	FAIL
	INDETERMINATE
	SUCCESS*/
	public String ruleId;
	/*string	The unique rule identifier*/
	public String ruleName;
	/*string	The rule name*/
	public String ruleDescription;
/*	string	The rule description*/
}
