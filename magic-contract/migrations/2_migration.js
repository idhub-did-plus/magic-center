const ecr = artifacts.require("EthereumClaimsRegistry");
const r1484 = artifacts.require("IdentityRegistry");
const resolver = artifacts.require("ERC1056");
const r1056 = artifacts.require("EthereumDIDRegistry");
let addr1484;
let addr1056;
let addr1056r;
module.exports = function(deployer) {
 // deployer.deploy(ecr);
  deployer.deploy(r1484).then(() => {
    return r1484.deployed();
}).then(inst1484 => {
  addr1484 = inst1484.address;
  return deployer
        .deploy(
            r1056)

}).then(inst1056 => {
  addr1056 = inst1056.address;

    return deployer
        .deploy(
            resolver, addr1484,addr1056);
})
};
