<template>
    <div class="historyBox">
        <div class="panel panel-default">
            <div class="panel-heading">
                <span>Kyc History</span>
                <span id="closeHistory" @click="close">X</span>
            </div>
            <div class="panel-body">
                <ul>
                    <li v-for="(item,index) in kycHistory" :key="index">
                        {{item}}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>
<style lang="scss">
    .historyBox{
        display: none;
        .panel{
            position: absolute;
            left:0;
            right:0;
            margin:auto;
            top:-40px;
            width:600px;
            height:420px;
            .panel-heading{
                overflow: hidden;
                span:nth-child(1){
                    float:left;
                }
                span:nth-child(2){
                    float: right;
                    cursor: pointer;
                }
            }
            .panel-body{
                width:600px;
                height:360px;
                overflow-y:auto;
                li{
                    padding:10px;
                    margin-top: 10px;
                    border:1px solid #ccc;
                    p{
                        padding:8px;
                        span{
                            padding:10px;
                            color:#000;
                        }
                    }
                    p:nth-child(2){
                        margin-top: 5px;
                    }
                }
            }
        }
    }
    
</style>
<script>
export default {
    props:["kycHistory"],
    methods:{
        close(){
            document.getElementsByClassName("historyBox")[0].style.display = "none";
        }
    }
}
</script>