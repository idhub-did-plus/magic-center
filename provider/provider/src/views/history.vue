<template>
    <div class="history">
        <Nav></Nav>
        history
    </div>
</template>
<style lang="scss">
    
</style>
<script>
import Nav from "../components/navigator"
export default {
    components:{
        Nav
    },
    mounted(){
        //切换导航条活跃样式
        var arr = document.getElementsByClassName("nav")[0].children;
        for(var i=0;i<arr.length;i++){
                arr[i].setAttribute("class","")
            }
        document.getElementById("history").setAttribute("class","active");
    }
}
</script>