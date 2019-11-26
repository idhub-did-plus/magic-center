<template>
    <div class="login">
        <div class="div"> 
            <input type="button" @click="login" class="button" value="log in by your wallet">
        </div>
    </div>
</template>
<style lang="scss" scoped>
    body{ 
        .login{
            height:1000px;
            background-color:#5544aa;
            background-image:url('../assets/b9.jpg');
            text-align:center;
            .div{ 
                margin:0 auto; 
                border:1px solid #F00;
                position: absolute;   
                top: 50%;   
                left: 40%;   
                .button {
                    background-color: #4CAF50;
                    border: 3;
                    color: white;
                    padding: 15px 32px;
                    text-align: center;
                    text-decoration: none;
                    display: inline-block;
                    font-size: 24px;
                    margin: 4px 2px;
                    cursor: pointer;
                }
            }
        }
    } 
</style>
<script>
import url from "../modules/baseURL"
export default {
    data(){
        return {
            timestamp:0
        }
    },
    mounted(){
        var myDate = new Date();
        this.timestamp = myDate.getTime();
        window.addEventListener('load', function() {
	        if (!window.web3) {//用来判断你是否安装了metamask
	            window.alert('Please install MetaMask first.');
	            return;
	        }
	      	if (window.ethereum) {
		        window.web3 = new Web3(ethereum);
		        try{
                    ethereum.enable();
		        }catch(error) {
		           window.alert('something is wrong.');
		        }
	        }
        });
    },
    methods:{
        onsign(error, signature){
            var ajax = new XMLHttpRequest();
	        ajax.onreadystatechange = ()=>{
                if(ajax.readyState == 4 && ajax.status == 200){
                    var msg = ajax.responseText;
                    alert(msg);
                    var json = JSON.parse(msg)
                    if(json.success){
                        this.$router.push({path:"/unReceivedList"})
                    }else{
                        alert(json.message)
                    }
                }
            }
            var identity = web3.eth.coinbase;
            //跨域请求是否提供凭据信息(cookie、HTTP认证及客户端SSL证明等)
            ajax.withCredentials = true;
            ajax.open('get',url.baseURL+'/auth/login?identity='+identity+'&timestamp='+this.timestamp + "&signature=" + signature);
            ajax.send(null);
        },
        login(){
            var data = web3.fromUtf8(this.timestamp + web3.eth.coinbase)
	        web3.personal.sign(data, web3.eth.coinbase,this.onsign);
        }
    }
}
</script>