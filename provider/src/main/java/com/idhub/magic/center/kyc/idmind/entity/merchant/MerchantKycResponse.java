package com.idhub.magic.center.kyc.idmind.entity.merchant;

import com.idhub.magic.center.kyc.idmind.entity.DocumentVerification;
import com.idhub.magic.center.kyc.idmind.entity.EDNAPolicyResult;
import com.idhub.magic.center.kyc.idmind.entity.ExternalizedTransactionScorecard;
import com.idhub.magic.center.kyc.idmind.entity.FraudPolicyResult;
import com.idhub.magic.center.kyc.idmind.entity.QuestionsWrapper;

public class MerchantKycResponse {
	public String mtid;
	/*string
	optional	The transaction ID for this KYC. This ID should be provided on subsequent updates to the KYC.*/
	public String acVerification;
	/*string
	optional	*/
	public DocumentVerification docVerification;
	/*DocumentVerification
	optional	*/
	public String[] ownerApplicationIds;
	/*array[string]
	optional	List of owner applications associated with the merchant*/
	public String parentMerchant;
/*	string
	optional	*/
	public String smsVerification;
	/*string
	optional	*/
	public String merchantAPIName;
	/*string
	optional	*/
	public String state;
	/*string
	optional	The current state of the KYC. Possible values are:
	A - Accepted
	R - Under Review
	D - Rejected*/
	public QuestionsWrapper oowQuestions;
	/*QuestionsWrapper
	optional	*/
	public EDNAPolicyResult user;
	/*EDNAPolicyResult
	optional	The current reputation of the user. Possible values are:
	TRUSTED
	UNKNOWN
	SUSPICIOUS
	BAD*/
	public String rcd;
	/*string	The set of result codes from the evaluation of the current transaction*/
	public String frn;
	//string	The name of the fraud rule that fired
	public String frd;
	//string	The description of the fraud rule that fired
	public ExternalizedTransactionScorecard ednaScoreCard;
	//ExternalizedTransactionScorecard	The score card for the current transaction
	public String arpd;
	/*string
	optional	The description, if any, of the automated review rule that fired*/
	public String arpid;
	/*string
	optional	The ID, if any, of the automated review rule that fired*/
	public String arpr;
	/*string
	optional	Result of the automated review evaluation. Possible values are:
	ERROR
	NO_POLICY - Error, no ARP
	DISABLED - ARP is disabled
	FILTERED - The transaction did not pass ARP filters
	PENDING - The ARP is waiting for a response from a third party
	FAIL - An ARP rule fired and returned a failure
	INDETERMINATE - No ARP rule fired
	SUCCESS - An ARP rule fired and the transaction was accepted*/
	public FraudPolicyResult frp;
	/*FraudPolicyResult
	optional	Result of fraud evaluation. Possible values are:
	ACCEPT
	MANUAL_REVIEW
	DENY*/
	public EDNAPolicyResult upr;
	/*EDNAPolicyResult
	optional	The previous reputation of the user when they were last evaluated. Possible values are:
	TRUSTED
	UNKNOWN
	SUSPICIOUS
	BAD*/
	public String erd;
	/*string
	optional	A description of the reason for the user’s reputation*/
	public FraudPolicyResult res;
	/*FraudPolicyResult
	optional	Result of policy evaluation. Combines the result of fraud and automated review evaluations. Possible values are:
	ACCEPT
	MANUAL_REVIEW
	DENY*/
	public String usc;
	/*string
	optional	User seen count*/
	public String tid;
	//string	The transaction ID.
}
