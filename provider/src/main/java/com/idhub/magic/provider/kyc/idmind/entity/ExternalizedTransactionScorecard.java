package com.idhub.magic.provider.kyc.idmind.entity;

public class ExternalizedTransactionScorecard {
	public AutomatedReviewEngineResult ar;
	/*AutomatedReviewEngineResult
	optional	The result of the automated review policy for this transaction*/
	public ConditionResult[] etr;
	/*array[ConditionResult]
	optional	The evaluated test results for this transaction*/
	public ConditionResult[] sc;
	/*array[ConditionResult]	The test results for this transaction*/
	public ExternalizedEvaluationResult er;
	/*ExternalizedEvaluationResult*/
}
