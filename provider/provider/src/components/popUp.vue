<template>
    <div class="popup">
        <div class="panel panel-default">
            <div class="panel-heading">
                <span>Kyc State</span>
                <span id="close" @click="close">X</span>
            </div>
            <div class="panel-body">kyc</div>
        </div>
    </div>
</template>
<style lang="scss">
    .popup{
        display: none;
        .panel{
            position: absolute;
            left:0;
            right:0;
            margin:auto;
            top:-40px;
            width:600px;
            height:400px;
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
                height:340px;
                overflow-y:auto;
            }
        }
    }
</style>
<script>
export default {
    data(){
        return {
            kState:""
        }
    },
    props:["state"],
    methods:{
        close(){
            document.getElementsByClassName("popup")[0].style.display = "none"
        }
    },
    watch:{
        state(newVal){
            this.kState = newVal;
            document.getElementsByClassName("panel-body")[0].innerHTML = this.kState;
        }
    }
}
</script>