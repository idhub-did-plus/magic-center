<template>
    <div class="top">
        <div id="box">
            <div id="top"></div>
            <div id="bottom">
                <div id="msg">
                    <div id="username" v-if="processingMsg">
                        {{processingMsg.archive.identityInfo.name.firstName}}
                        {{processingMsg.archive.identityInfo.name.middleName}}
                        {{processingMsg.archive.identityInfo.name.lastName}}
                    </div>
                    <div id="username" v-else>
                    </div>
                    <span>{{claimType}}</span>
                    <br>
                    <span>{{identity}}</span>
                </div>
            </div>
            <div id="img"><img src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3987282417,3624059467&fm=26&gp=0.jpg" alt=""></div>
        </div>
        <div id="textInfo" class="info">
            <ul v-if="processingMsg">
                <li style="margin-top:20px;"><span>{{$t('m.userinfo.birthday')}}</span>{{processingMsg.archive.identityInfo.birthday}}</li>
                <li><span>{{$t('m.userinfo.country')}}</span>{{processingMsg.archive.identityInfo.country}}</li>
                <li><span>{{$t('m.userinfo.residenceCountry')}}</span>{{processingMsg.archive.identityInfo.residenceCountry}}</li>
                <li><span>{{$t('m.userinfo.idcardNumber')}}</span>{{processingMsg.archive.identityInfo.idcardNumber}}</li>
                <li><span>{{$t('m.userinfo.passportNumber')}}</span>{{processingMsg.archive.identityInfo.passportNumber}}</li>
                <li><span>{{$t('m.userinfo.phoneNumber')}}</span>{{processingMsg.archive.identityInfo.phoneNumber}}</li>
                <li><span>{{$t('m.userinfo.gender')}}</span>{{processingMsg.archive.identityInfo.gender}}</li>
                <li><span>{{$t('m.userinfo.email')}}</span>{{processingMsg.archive.basicInfo.email}}</li>
                <li><span>{{$t('m.userinfo.taxId')}}</span>{{processingMsg.archive.basicInfo.taxId}}</li>
                <li><span>{{$t('m.userinfo.ssn')}}</span>{{processingMsg.archive.basicInfo.ssn}}</li>
                <li>
                    <span>{{$t('m.userinfo.address')}}</span>
                    <p v-for="(i,index) in processingMsg.archive.identityInfo.address.addressSequence" :key="index">
                        <span class="address">{{i.name}}:</span>{{i.value}}
                    </p>
                    <p><span class="address">{{$t('m.userinfo.postalCode')}}</span>{{processingMsg.archive.identityInfo.address.postalCode}}</p>
                </li>
                <li><span>{{$t('m.userinfo.financialProfile')}}</span>
                    <p><span class="address">{{$t('m.userinfo.buyer')}}</span>{{buyerType}}</p>
                    <p><span class="address">{{$t('m.userinfo.investor')}}</span>{{investorType}}</p>
                </li>
            </ul>
            <ul v-else>
                <li style="margin-top:20px;"><span>{{$t('m.userinfo.birthday')}}</span></li>
                <li><span>{{$t('m.userinfo.country')}}</span></li>
                <li><span>{{$t('m.userinfo.residenceCountry')}}</span></li>
                <li><span>{{$t('m.userinfo.idcardNumber')}}</span></li>
                <li><span>{{$t('m.userinfo.passportNumber')}}</span></li>
                <li><span>{{$t('m.userinfo.phoneNumber')}}</span></li>
                <li><span>{{$t('m.userinfo.gender')}}</span></li>
                <li><span>{{$t('m.userinfo.email')}}</span></li>
                <li><span>{{$t('m.userinfo.taxId')}}</span></li>
                <li><span>{{$t('m.userinfo.ssn')}}</span></li>
                <li>
                    <span>{{$t('m.userinfo.address')}}</span>
                    <p>
                        <span class="address"></span>
                    </p>
                    <p><span class="address">{{$t('m.userinfo.postalCode')}}</span></p>
                </li>
                <li><span>{{$t('m.userinfo.financialProfile')}}</span>
                    <p><span class="address">{{$t('m.userinfo.buyer')}}:</span></p>
                    <p><span class="address">{{$t('m.userinfo.investor')}}:</span></p>
                </li>
            </ul>
        </div>
        <div id="fileInfo" class="info">
            <ul ref="ul1">
                <li v-for="(item,index) in processingMsg.materials" :key="index" @click="showFile(index)">
                    <div>
                        <p><img src="../assets/page.png" alt=""></p>
                        <span>{{item.type}}</span>
                    </div>
                </li>
            </ul>
        </div>
        <file id="file" :message="processingMsg" :userID="identity" :item="fileIndex"></file>
    </div>
</template>
<style lang="scss">
    .top{
        float: left;
        #box{
            width:800px;
            height:280px;
            background:	#fff;
            box-shadow: 2px 3px 4px #ccc;
            position:relative;
            padding-bottom: 20px;
            #top{
                width:800px;
                height:130px;
                background: url("../assets/bg.jpg") no-repeat;
                background-size: 100%;
            }
            #img{
                width:120px;
                height:120px;
                border:1px solid #ccc;
                border-radius: 50%;
                overflow: hidden;
                position: absolute;
                top:50px;
                left:30px;
                img{
                    width:100%;
                    height:100%;
                }
            }
            #bottom{
                color:#000;
                #msg{
                    margin-top: 10px;
                    margin-left: 170px;
                    font-size: 16px;
                    div:nth-child(1){
                        margin-top: 25px;
                        margin-left: 10px;
                        font-weight: 700;
                        margin-bottom: 10px;
                    }
                    span:nth-child(2){
                        margin-left: 10px;
                    }
                    span:nth-child(4){
                        margin-left: 10px;
                    }
                }
            }
        }
        .info{
            margin-top: 15px;
            padding: 35px 50px;
            width:800px;
            background:	#fff;
            box-shadow: 2px 3px 4px #ccc;
        }
        #textInfo{
            ul{
                li{
                    border:1px solid #eee;
                    padding:6px;
                    color:#000;
                    span{
                        padding-left:20px;
                        padding-right: 20px;
                        font-weight: 600;
                        display:inline-block;
                        width:240px;
                    }
                    .address{
                        font-weight: 500;
                        width: 180px;
                        padding-right: 30px;
                    }
                    p{
                        padding:6px;
                        padding-left: 220px;
                    }
                }
            }
        }
        #fileInfo{
            padding:30px 20px;
            margin-bottom: 50px;
            overflow: hidden;
            ul{
                width:100%;
                height:100%;
                position: relative;
                z-index:10;
                li{
                    float:left;
                    margin-top: 35px;
                    margin-left: 20px;
                    width:170px;
                    height:180px;
                    overflow: hidden;
                    box-shadow: 1px 1px 1px #ccc;
                    text-align: center;
                    p{
                        display:inline-block;
                        width:70px;
                        img{
                            margin-top: 30px;
                            width:100%;
                            height:100%;
                        }
                    }
                    span{
                        margin-top: 10px;
                        margin-bottom: 20px;
                        display:block;
                        font-size: 12px;
                        color:#000;
                        cursor: pointer;
                        padding:3px;
                        word-wrap: break-word;
                    }
                }
            }
        }
    }
</style>
<script>
import file from "../components/file"

export default {
    data(){
        return {
            buyerType:"",
            investorType:"",
            fileIndex:0,
            financialProfile:"",
            buyerType:"",
            investorType:""
        }
    },
    components:{
        file
    },
    props:["identity","claimType","processingMsg"],
    methods:{
        showFile(index){
            this.fileIndex = index;
            document.getElementById("file").style.display = "block";
        }
    },
    watch:{
        processingMsg(newVal){
            this.financialProfile = newVal;
            if(this.financialProfile.archive.financialProfile == null){
                this.buyerType = null;
                this.investorType = null;
            }else{
                this.buyerType = this.financialProfile.archive.financialProfile.buyerType;
                this.investorType = this.financialProfile.archive.financialProfile.investorType;
            }
        }
    }
}
</script>