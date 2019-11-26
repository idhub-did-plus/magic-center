<template>
    <div class="detail">
        <Nav></Nav>
        <div class="page-header">
            <h1>{{$t('m.userinfo.title')}}</h1>
        </div>
        <Left :id="id" :identity="identity" :claimType="claimType" :processingMsg="msg" ref="brother"></Left>
        <Right :orderId="orderId" :processingMsg="msg" :claimType="claimType" :identity="identity"></Right>
    </div>
</template>
<style lang="scss">
    
</style>
<script>
import Left from "../components/userDetailTop"
import Right from "../components/userDetailRight"
import url from "../modules/baseURL"
import Nav from "../components/navigator"

export default {
    components:{
        Nav
    },
    data(){
        return {
            id:"",
            identity:"",
            claimType:"",
            orderId:"",
            msg:[]
        }
    },
    components:{
        Left,Right
    },
    mounted(){
        this.id = this.$route.query.id;
        this.identity = this.$route.query.identity;
        this.claimType = this.$route.query.claimType;
        this.orderId = this.$route.query.orderId;
        //请求用户信息
        this.$http.get(url.baseURL+"/order/identity_archive",{
            params:{
                identity:this.identity
            }
        }).then(res=>{
            if(res.data.success == true){
                this.msg = res.data.data.data;
            }else{
                alert(this.$t('m.alert.userInfoFail'));
            }
        })
    }
}
</script>