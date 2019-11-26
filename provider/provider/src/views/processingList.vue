<template>
    <div class="received">
        <Nav></Nav>
        <nav aria-label="...">
            <ul class="pager">
                <li id="prev"><a @click="prev">{{$t('m.list.prev')}}</a></li>
                <li id="next"><a @click="next">{{$t('m.list.next')}}</a></li>
            </ul>
        </nav>
        <ul class="list-group">
            <li v-for="(item,index) in processing" :key="item.id" class="list-group-item">
                <span class="badge">{{item.order.claimType}}</span>
                <span>{{item.createTime}}</span>
                <span class="name">{{item.order.identity}}</span>
                <button 
                    type="button" 
                    class="btn btn-success" 
                    @click="toggle($event)" 
                    :id="index"
                    :identity="item.order.identity"
                    :claimType="item.order.claimType"
                    :orderId="item.order.id"
                >{{$t('m.list.detail')}}</button>
            </li>
        </ul>
        <div id="page">{{$t('m.list.num')}}<span>{{nowPage}}</span>/<span>{{Math.ceil(this.totle/pageSize)?Math.ceil(this.totle/pageSize):1}}</span>{{$t('m.list.page')}}</div>
    </div>
</template>
<style lang="scss">
    .received{
        nav{
            margin-top: 100px;
        }
        .name{
            padding:25px;
            color:#000;
            font-weight: 600;
        }
        .btn{
            padding:3px 12px;
            margin-left: 10px;
            font-size: 12px;
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
import url from "../modules/baseURL"

export default {
    data(){
        return {
            processing:"",
            identity:"",
            claimType:"",
            totle:0,
            pageSize:6,
            nowPage:1,
            maxPage:1
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
        toggle($event){
            var identity = $event.currentTarget.getAttribute("identity");
            var claimType = $event.currentTarget.getAttribute("claimType");
            var orderId = $event.currentTarget.getAttribute("orderId");
            var path =  $event.currentTarget.getAttribute("id");
            this.$router.push({
                path:"processingDetail/"+path,
                query:{
                    id:path,
                    identity:identity,
                    claimType:claimType,
                    orderId:orderId
                }
            });
        }
    },
    mounted(){
        //切换导航条活跃样式
        var arr = document.getElementsByClassName("nav")[0].children;
        for(var i=0;i<arr.length;i++){
            arr[i].setAttribute("class","")
        }
        document.getElementById("processingList").setAttribute("class","active");
    },
    created(){
        //实现分页 获取processing状态下的订单总数
        this.$http.get(url.baseURL+"/order/size",{
            params:{
                state:"processing"
            }
        }).then(res=>{
            if(res.data.success == true){
                this.totle = res.data.data;
                this.maxPage = Math.ceil(this.totle/this.pageSize)?Math.ceil(this.totle/this.pageSize):1;
            }else{
                alert(this.$t('m.alert.pageFail'));
            }
            
        })
        //请求第一页的列表信息
        this.$http.get(url.baseURL+"/order/list",{
            params:{
                state:"processing",
                startPage:this.nowPage-1,
                pageSize:this.pageSize
            }
        }).then(res=>{
            if(res.data.success == true){
                this.processing = res.data.data;
            }
            if(res.data.success == false){
                alert(this.$t('m.alert.listFail'));
            }
        })
    },
    watch:{
        nowPage:function(){
            //请求列表信息
            this.$http.get(url.baseURL+"/order/list",{
                params:{
                    state:"processing",
                    startPage:this.nowPage-1,
                    pageSize:this.pageSize
                }
            }).then(res=>{
                if(res.data.success == true){
                    this.processing = res.data.data;
                }
                if(res.data.success == false){
                    alert(this.$t('m.alert.listFail'));
                }
            })
        }
    }
}
</script>