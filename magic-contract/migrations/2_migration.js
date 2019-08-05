const ecr = artifacts.require("EthereumClaimsRegistry");
const r1484 = artifacts.require("IdentityRegistry");
const resolver = artifacts.require("ERC1056");
const r1056 = artifacts.require("EthereumDIDRegistry");

module.exports = function(deployer) {
 // deployer.deploy(ecr);
  deployer.deploy(r1484);
};
