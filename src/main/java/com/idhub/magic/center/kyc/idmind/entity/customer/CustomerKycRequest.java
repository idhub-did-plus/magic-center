package com.idhub.magic.center.kyc.idmind.entity.customer;

import java.util.Date;

public class CustomerKycRequest {
	public  String dob;
	/*string
	optional	Applicant’s date of birth encoded as an ISO 8601 string (YYYY-MM-DD)*/
	public String assn;
	/*string
	optional	Applicant’s social security number or national identification number

	Should follow the format <ISO-3166-1 alpha-2 country code>:<national id>
	For example, \"US:123456789\" represents a United States Social Security number.
	If no country code is provided, it is assumed to be a United States Social Security number.*/
	public String assnl4;
	/*string
	optional	Last 4 digits of the applicant’s social security number or national identification number*/
	public String scanData;
	/*string
	optional	Document front-side image data, Base64 encoded. 400KB minimum size, 4MB maximum size, color only.*/
	public String faceImageData;
	/*string
	optional	Document face image data, Base64 encoded. 400KB minimum size, 4MB maximum size, color only.*/
	public String backsideImageData;
	/*string
	optional	Document back-side image data, Base64 encoded. 400KB minimum size, 4MB maximum size, color only.*/
	int stage;
	/*integer
	optional	Stage of application being processed. An integer between 1 and 5. If not provided, defaults to 1.*/
	public String merchantAid;
	/*string
	optional	If this individual is linked to a merchant (business) as one of the owners of the business, this parameter should match the exact application ID of the merchant.

	This field can also be used to link a corporate owner to a merchant.*/
	boolean personalguarantee;
	/*boolean
	optional	If this individual is linked to a merchant (business) as one of the owners of the business, whether the individual provides a personal guarantee of debt*/
	int ownership;
	/*number
	optional	If this individual is linked to a merchant (business) as one of the owners of the business, the percentage of ownership*/
	public String title;
	/*string
	optional	Title of the applicant*/
	public String m;
	/*string
	optional	Reflects the merchant’s merchantAPIname that was assigned on account creation*/
	public String tid;
	/*string
	optional	Transaction identifier. If not provided, an ID will be allocated

	Maximum length is 32 characters*/
	public String man;
	/*string
	required	Account name for the user

	Maximum length is 60 characters*/
	public String tea;
	/*string
	optional	Email address for the user

	Maximum length is 60 characters*/
	public String soc;
	/*string
	optional	OAuth service that authenticated the user. For example, google or facebook*/
	public String ip;
	/*string
	optional	Customer’s IP address

	Maximum length is 40 characters*/
	public String dfp;
	/*string
	optional	Device fingerprint blob*/
	public String dft;
	/*string
	optional	Device fingerprint type:*/
	public String AU;// - Augur
	public String IO;// - Iovation
	public String CB;// - ThreatMetrix
	public String IA;// - InAuth
	/*Augur has full Security Test support, the other types are for device matching purposes only.*/
	public Date tti;
	/*Object
	optional	Transaction time in UTC. Encoded as a Unix time stamp or ISO 8601 string. Data containing milliseconds will not be accepted.*/
	public String bfn;
	/*string
	optional	Billing (source) first name

	Maximum length is 30 characters*/
	public String bln;
	/*string
	optional	Billing (source) last name

	Maximum length is 50 characters*/
	public String pccn;
	/*string
	optional	Credit card unique identifier (hash). IdentityMind will supply procedure to generate hash.

	Note: The hash must be of the full card number, not a masked or tokenized representation.

	Maximum length is 128 characters*/
	public String pcct;
	/*string
	optional	A masked or tokenized version of the credit card number. IdentityMind will supply procedure to generate token.

	Maximum length is 64 characters*/
	public String pcty;
	/*string
	optional	The card type. Possible values are:*/
	public String CREDIT;
	public String DEBIT;
	public String PREPAID;
	//UNKNOWN
	public String phash;
	/*string
	optional	Generic payment account unique identifier (hash). This is used when IdentityMind does not natively support the payment type.

	If the payment instrument is Skrill, this will be a hash of the Skrill account email address. 

	Note: The hash must be of the full account number, not a masked or tokenized representation.

	Maximum length is 128 characters*/
	public String ptoken;
	/*string
	optional	A masked or tokenized version of the account token. 

	If the payment instrument is Skrill, the ptoken should follow the format skrill:emailaddress. For example, skrill:johndoe@gmail.com. 

	Maximum length is 64 characters*/
	public String profile;
	/*string
	optional	The policy profile to be used to evaluate this transaction. If no profile is specified, the DEFAULT profile is used. Prior to IDMRisk 1.19, this was encoded in the smid field.*/
	public String smna;
	/*string
	optional	Deprecated

	The user-friendly name for the merchant for whom this transaction is being processed. Used for display purposes in the UI.*/
	public String smid;
	/*string
	optional	Deprecated

	A unique identifier for the merchant for whom this transaction is being processed. Succeeded by the profile field in IDMRisk 1.19.*/
	public String bsn;
	/*string
	optional	Billing (source) street. Includes house number, street name, and apartment number.

	Maximum length is 100 characters*/
	public String bco;
	/*string
	optional	Billing (source) country. ISO 3166-1 alpha-2 country code of the billing address of the transaction. Default is US.

	Maximum length is 2 characters*/
	public String bz;
	/*string
	optional	Billing (source) zip / postal code

	Maximum length is 20 characters*/
	public String bc;
	/*string
	optional	Billing (source) city

	Data truncates to 30 characters*/
	public String bs;
	/*string
	optional	Billing (source) state. Use official postal state/region abbreviations whenever possible (e.g. CA for California). 

	Maximum length is 30 characters*/
	public String sfn;
	/*string
	optional	Shipping (destination) first name

	Maximum length is 30 characters*/
	public String sln;
	/*string
	optional	Shipping (destination) last name

	Maximum length is 50 characters*/
	public String ssn;
	/*string
	optional	Shipping (destination) street. Includes house number, street name, and apartment number.

	Maximum length is 100 characters*/
	public String sco;
	/*string
	optional	Shipping (destination) country. ISO 3166-1 alpha-2 country code of the billing address of the transaction. Default is US.

	Maximum length is 2 characters*/
	public String sz;
	/*string
	optional	Shipping (destination) zip / postal code

	Maximum length is 20 characters*/
	public String sc;
	/*string
	optional	Shipping (destination) city

	Data truncates to 30 characters*/
	public String ss;
	/*string
	optional	Shipping (destination) state. Use official postal state/region abbreviations whenever possible (e.g. CA for California). 

	Maximum length is 30 characters*/
	public String aflid;
	/*string
	optional	Affiliate ID. The client specific identifier for the affiliate that generated this transaction.

	Maximum length is 100 characters*/
	public String aflsd;
	/*string
	optional	The signup/affiliate creation date of the affiliate associated with this transaction. Either an ISO 8601 encoded string or a Unix timestamp.*/
	long accountCreationTime;
	/*long
	optional	User account creation time with the merchant in UTC. Encoded as a Unix timestamp.*/
	public String blg;
	/*string
	optional	Customer browser language. Values are defined in ISO 639-1.*/
	public String clat;
	/*string
	optional	Customer latitude*/
	public String clong;
	/*string
	optional	Customer longitude*/
	public String phn;
	/*string
	optional	Customer primary phone number

	public String Maximum length is 60 characters*/
	public String pm;;
	/*string
	optional	Customer mobile phone number

	Maximum length is 60 characters*/
	public String pw;
/*	string
	optional	Customer work phone number

	Maximum length is 60 characters*/
	public String pach;
	/*string
	optional	ACH account unique identifier (hash)

	Note: The hash must be of the full account number, not a masked or tokenized representation*/
	public String pbc;
	/*string
	optional	Hash of the unique identifier for a Bitcoin wallet*/
	public String memo;
/*	string
	optional	Free-form memo field for client use.

	memo and memo1-memo40 are available for use as custom fields. These custom fields are viewable, searchable, sortable, and can be labelled using the IdentityMind UI.

	For example, you can send SKU through memo1, Quantity through memo2, and Region through memo3.*/
}
