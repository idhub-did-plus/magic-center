<template>
    <div class="right">
        <div id="claimBox">
            <div id="header"></div>
            <div id="content">
                <button @click="kyc" type="button" class="btn btn-primary">{{$t('m.right.state')}}</button>
                <button @click="kycState" type="button" class="btn btn-primary">{{$t('m.right.history')}}</button>
                <button @click="agree" type="button" class="btn btn-primary">{{$t('m.right.issue')}}</button>
                <button @click="refuse" type="button" class="btn btn-primary">{{$t('m.right.refuse')}}</button>
            </div>
        </div>
        <popup :state="state" id="son"></popup>
        <kycHistory :kycHistory="kycHistory" id="history"></kycHistory>
    </div>
</template>
<style lang="scss">
    .right{
        margin-top: 40px;
        position:fixed;
        top:70px;
        #claimBox{
            width:300px;
            height:350px;
            background:#fff;
            margin-left: 890px;
            box-shadow: 2px 3px 4px #ccc;
            #header{
                width:300px;
                height:50px;
                background:url("../assets/bg.jpg");
                background-size: 100%;
            }
            #content{
                width:300px;
                height:300px;
                background: #fff;
                text-align: center;
                padding:30px 20px 15px 20px;
                border:1px solid #ccc;
                button{
                    display:inline-block;
                    width:220px;
                    height:40px;
                    margin-top: 15px;
                    background:#4682B4;
                    box-shadow: 3px 4px 5px LightSlateGray;
                }
            }
        }
    }
</style>
<script>
import url from "../modules/baseURL"
import popup from "../components/popUp"
import kycHistory from "../components/kycHistory"

export default {
    components:{
        popup,
        kycHistory
    },
    data(){
        return {
            kycHistory:[],
            state:"",
            setClaimValue:[],
            nationality:"",
            residence:"",
            jurisdictions:"",
            account:"",
            cType:"",
            subAddr:""
        }
    },
    props:["orderId","processingMsg","claimType","identity"],
    mounted(){
        this.account = web3.eth.accounts[0];
    },
    watch:{
        processingMsg(newVal){
            this.setClaimValue = newVal;
            this.nationality = this.setClaimValue.archive.identityInfo.country;
            this.residence = this.setClaimValue.archive.identityInfo.residenceCountry;
            this.jurisdictions = null;
        },
        claimType(newVal){
            this.cType = newVal;
        },
        identity(newVal){
            this.subAddr = newVal;
        }
    },
    methods:{
        kyc(){
            //kyc状态接口idm evalute
            this.$http.get(url.baseURL+"/idm/evaluate",{
                params:{
                    orderId:this.orderId
                }
            }).then(res=>{
                if(res.data.success == true && res.data.data == null){
                   alert(this.$t('m.right.check'));
                }
                if(res.data.success == true && res.data.data != null){
                    this.state = res.data.data;
                    //加一个弹窗展示
                    document.getElementById("son").style.display = "block";
                }
            })
        },
        kycState(){
            //kyc调用记录接口 order interactions
            this.$http.get(url.baseURL+"/order/interactions",{
                params:{
                    orderId:this.orderId
                }
            }).then(res=>{
                if(res.data.success == true){
                   this.kycHistory = res.data.data;
                   document.getElementById("history").style.display = "block";
                }else{
                    console.log(this.$t('m.alert.historyFail'));
                }
            })
        },
        agree(){
            //链上claim颁发
            //先判断是否是合规用户（ST合格投资者）
            if(this.cType == "ST_Compliant_Investor"){
                //给合规用户颁发三个链上claim
                //与REC780合约交互
                var abiArray = [
                    {
                    "constant": false,
                    "inputs": [
                        {
                        "name": "key",
                        "type": "bytes32"
                        },
                        {
                        "name": "value",
                        "type": "bytes32"
                        }
                    ],
                    "name": "setSelfClaim",
                    "outputs": [],
                    "payable": false,
                    "stateMutability": "nonpayable",
                    "type": "function"
                    },
                    {
                    "constant": false,
                    "inputs": [
                        {
                        "name": "subject",
                        "type": "address"
                        },
                        {
                        "name": "key",
                        "type": "bytes32"
                        },
                        {
                        "name": "value",
                        "type": "bytes32"
                        }
                    ],
                    "name": "setClaim",
                    "outputs": [],
                    "payable": false,
                    "stateMutability": "nonpayable",
                    "type": "function"
                    },
                    {
                    "constant": false,
                    "inputs": [
                        {
                        "name": "issuer",
                        "type": "address"
                        },
                        {
                        "name": "subject",
                        "type": "address"
                        },
                        {
                        "name": "key",
                        "type": "bytes32"
                        }
                    ],
                    "name": "removeClaim",
                    "outputs": [],
                    "payable": false,
                    "stateMutability": "nonpayable",
                    "type": "function"
                    },
                    {
                    "constant": true,
                    "inputs": [
                        {
                        "name": "issuer",
                        "type": "address"
                        },
                        {
                        "name": "subject",
                        "type": "address"
                        },
                        {
                        "name": "key",
                        "type": "bytes32"
                        }
                    ],
                    "name": "getClaim",
                    "outputs": [
                        {
                        "name": "",
                        "type": "bytes32"
                        }
                    ],
                    "payable": false,
                    "stateMutability": "view",
                    "type": "function"
                    },
                    {
                    "constant": true,
                    "inputs": [
                        {
                        "name": "",
                        "type": "address"
                        },
                        {
                        "name": "",
                        "type": "address"
                        },
                        {
                        "name": "",
                        "type": "bytes32"
                        }
                    ],
                    "name": "registry",
                    "outputs": [
                        {
                        "name": "",
                        "type": "bytes32"
                        }
                    ],
                    "payable": false,
                    "stateMutability": "view",
                    "type": "function"
                    },
                    {
                    "anonymous": false,
                    "inputs": [
                        {
                        "indexed": true,
                        "name": "issuer",
                        "type": "address"
                        },
                        {
                        "indexed": true,
                        "name": "subject",
                        "type": "address"
                        },
                        {
                        "indexed": true,
                        "name": "key",
                        "type": "bytes32"
                        },
                        {
                        "indexed": false,
                        "name": "value",
                        "type": "bytes32"
                        },
                        {
                        "indexed": false,
                        "name": "updatedAt",
                        "type": "uint256"
                        }
                    ],
                    "name": "ClaimSet",
                    "type": "event"
                    },
                    {
                    "anonymous": false,
                    "inputs": [
                        {
                        "indexed": true,
                        "name": "issuer",
                        "type": "address"
                        },
                        {
                        "indexed": true,
                        "name": "subject",
                        "type": "address"
                        },
                        {
                        "indexed": true,
                        "name": "key",
                        "type": "bytes32"
                        },
                        {
                        "indexed": false,
                        "name": "removedAt",
                        "type": "uint256"
                        }
                    ],
                    "name": "ClaimRemoved",
                    "type": "event"
                    }
                ];
                //合约地址
                var address = "0x3c5a44d263ABed6795D0Cb5ed5f7BC2E4337eeF6";
                var MyContract = web3.eth.contract(abiArray);
                // 使用合约地址实例化合约
                var ERC780Contract = MyContract.at(address);
                
                //首先分别判断三个claim的value值是否存在，不存在则不颁发
                //第一个claim
                if(this.nationality){
                    //合约setClaim方法的三个参数
                    var identity = this.subAddr;
                    var key = web3.sha3('nationality');
                    var val = 'stCompliance-0.0.1-' + this.nationality;
                    var value = web3.sha3(val);
                    //调智能合约
                    ERC780Contract.setClaim.sendTransaction(identity,key,value,{from:this.account},function(err, result){
                        if(!err){
                            console.log("nationality "+result)
                        }else{
                            console.log("nationality "+err)
                        }
                    })
                }

                //第二个claim
                if(this.residence){
                    //合约setClaim方法的三个参数
                    var identity = this.subAddr;
                    var key = web3.sha3('residence');
                    var val = 'stCompliance-0.0.1-' + this.residence;
                    var value = web3.sha3(val);
                    //调智能合约
                    ERC780Contract.setClaim.sendTransaction(identity,key,value,{from:this.account},function(err, result){
                        if(!err){
                            console.log("residence "+result)
                        }else{
                            console.log("residence "+err)
                        }
                    })
                }

                //第三个claim
                if(this.jurisdictions){
                    //合约setClaim方法的三个参数
                    var identity = this.subAddr;
                    var key = web3.sha3('jurisdictions');
                    var val = 'stCompliance-0.0.1-' + this.jurisdictions;
                    var value = web3.sha3(val);
                    //调智能合约
                    ERC780Contract.setClaim.sendTransaction(identity,key,value,{from:this.account},function(err, result){
                        if(!err){
                            console.log("jurisdictions "+result)
                        }else{
                            console.log("jurisdictions "+err)
                        }
                    })
                }
            }
            
            //链下claim颁发
            this.$http.get(url.baseURL+"/order/issue_claim",{
                params:{
                    orderId:this.orderId
                }
            }).then(res=>{
                if(res.data.success == true){
                    alert(this.$t('m.alert.issueSuccess'))
                }else{
                    alert(this.$t('m.alert.issueFail'))
                }
            })
        },
        refuse(){
            this.$http.get(url.baseURL+"/order/refuse_claim",{
                params:{
                    orderId:this.orderId
                }
            }).then(res=>{
                if(res.data.success == true){
                    alert(this.$t('m.alert.refuseSuccess'))
                }else{
                    alert(this.$t('m.alert.refuseFail'))
                }
            })
        }
    }
}
</script>