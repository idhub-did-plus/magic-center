package com.idhub.magic.common.service;

public class DeployedContractAddress {

	static DeployedContractAddress instance = new DeployedContractAddress();

	static public DeployedContractAddress getInstance() {
		return instance;
	}

	Addresses main = new Addresses(
			new String[] { "0x90e1B1C7B8C829b3d0b1C09eD961e46f5AeeD184", "0x6d0f04B6Ca0217323af7fB7147a63C97Ef910617",
					"0x42197FC11ad0765567e1b552d4063464DE938923", "0x8940D955F437d592c764cA243aeB7742BC156F9C" });
	Addresses rospen = new Addresses(
			new String[] { "0x90e1B1C7B8C829b3d0b1C09eD961e46f5AeeD184", "0x6d0f04B6Ca0217323af7fB7147a63C97Ef910617",
					"0x42197FC11ad0765567e1b552d4063464DE938923", "0x8940D955F437d592c764cA243aeB7742BC156F9C" });
	Addresses current = main;

	public String addr1484() {
		return current.addr1484();
	}

	public String addr1056() {
		return current.addr1056();
	}

	public String addr1056Resolver() {
		return current.addr1056Resolver();
	}

	public String addr780() {
		return current.addr780();
	}

	public void setMain() {
		current = main;
	}

	public void setRospen() {
		current = rospen;
	}

}
