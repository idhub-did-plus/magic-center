<template>
    <div class="list">
        <Nav></Nav>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>{{$t('m.list.time')}}</th>
                    <th>{{$t('m.list.identity')}}</th>
                    <th>{{$t('m.list.country')}}</th>
                    <th>{{$t('m.list.district')}}</th>
                    <th>{{$t('m.list.claim')}}</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item,index) in unReceive" :key="item.id">
                    <td>{{item.createTime}}</td>
                    <td>{{item.order.identity}}</td>
                    <td>{{item.order.country}}</td>
                    <td>{{item.order.jurisdiction}}</td>
                    <td>{{item.order.claimType}}</td>
                    <td><button type="button" class="btn btn-success float" @click="receive($event)" :id="index" :orderId="item.order.id">{{$t('m.list.order')}}</button></td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="...">
            <ul class="pager">
                <li id="prev"><a @click="prev">{{$t('m.list.prev')}}</a></li>
                <li id="next"><a @click="next">{{$t('m.list.next')}}</a></li>
            </ul>
        </nav>
        <div id="page">{{$t('m.list.num')}}<span>{{nowPage}}</span>/<span>{{Math.ceil(this.totle/pageSize)?Math.ceil(this.totle/pageSize):1}}</span>{{$t('m.list.page')}}</div>
    </div>
</template>
<style lang="scss">
    .list{
        nav{
            margin-top: 100px;
        }
        .table{
            margin-top: 60px;
        }
        .list-group-item{
            position: relative;
            .name{
                padding:25px;
                color:#000;
                font-weight: 600;
            }
            .float{
                float:right;
            }
            .badge{
                position:absolute;
                top:10px;
                right:100px;
            }
            .btn{
                position:absolute;
                top:6px;
                right:20px;
                padding:3px 12px;
                margin-left: 20px;
                font-size: 12px;
            }
        }
        #page{
            float:right;
            margin-top: 30px;
            margin-right: 60px;
            span{
                padding:10px;
            }
        }
    }
</style>
<script>
import Nav from "../components/navigator"
import url from "../modules//baseURL"

export default {
    data(){
        return {
            unReceive:"",
            orderId:"",
            totle:0,
            nowPage:1,
            maxPage:1,
            pageSize:6
        }
    },
    components:{
        Nav
    },
    methods:{
        prev(){
            if(this.nowPage > 1){
                this.nowPage = this.nowPage-1
            }else{
                this.nowPage = 1;
                document.getElementById("prev").setAttribute("class","disabled");
            }
        },
        next(){
            if(this.nowPage < this.maxPage){
                this.nowPage = this.nowPage+1
            }else{
                this.nowPage = this.maxPage;
                document.getElementById("next").setAttribute("class","disabled");
            }
        },
        receive($event){
            //点击接单按钮
            this.orderId = $event.currentTarget.getAttribute("orderId");
            this.$http.get(url.baseURL+"/order/receive",{
                params:{
                    orderId:this.orderId
                }
            }).then(res=>{
                this.unReceive = res.data.data;
                if(res.data.success == true){
                    alert(this.$t('m.alert.success'));
                    //0.5s后刷新页面
                    setTimeout(function(){
                        window.location.reload();
                    },500); 
                }
                if(res.data.success == false){
                    alert(this.$t('m.alert.orderFail'))
                }
            })
        }
    },
    created(){
        // 实现分页 获取列表总数
        this.$http.get(url.baseURL+"/order/size",{
            params:{
                state:"unreceived"
            }
        }).then(res=>{
            if(res.data.success == true){
                this.totle = res.data.data;
                this.maxPage = Math.ceil(this.totle/this.pageSize)?Math.ceil(this.totle/this.pageSize):1;
            }else{
                alert(this.$t('m.alert.pageFail'));
            }
        })
        //请求状态列表
        this.$http.get(url.baseURL+"/order/list",{
            params:{
                state:"unreceived",
                startPage:this.nowPage-1,
                pageSize:this.pageSize
            }
        }).then(res=>{
            if(res.data.success == true){
                this.unReceive = res.data.data;
            }else{
                alert(this.$t('m.alert.listFail'));
            }
        })
        
    },
    mounted(){
        //切换导航条活跃样式
        var arr = document.getElementsByClassName("nav")[0].children;
        for(var i=0;i<arr.length;i++){
            arr[i].setAttribute("class","")
        }
        document.getElementById("unReceivedList").setAttribute("class","active");
    },
    watch:{
        nowPage:function(){
            //请求列表信息
            this.$http.get(url.baseURL+"/order/list",{
                params:{
                    state:"unreceived",
                    startPage:this.nowPage-1,
                    pageSize:this.pageSize
                }
            }).then(res=>{
                if(res.data.success == true){
                    this.unReceive = res.data.data;
                }else{
                    alert(this.$t('m.alert.listFail'));
                }
                
            })
        }
    }
}
</script>