package com.idhub.magic.center.kyc.idmind.entity;

public class ExternalizedEvaluationResult {
	public ExternalizedRule[] firedRules;
	/*List[ExternalizedRule]	The complete set of rules that fired for this transaction*/
	public ExternalizedRule reportedRule;
	/*ExternalizedRule*/
	public String profile;
}
