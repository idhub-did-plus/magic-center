<template>
    <div class="newPage">
        <object data="" width="100%" height="100%">
            <iframe src="" width="100%" height="100%">
                This file does not support scan.
            </iframe>
        </object>
    </div>
</template>
<style lang="scss">
    .newPage{
        height:600px;
    }
</style>
<script>
import url from "../modules/baseURL"

export default {
    data(){
        return {
            newPage:"",
            materialId:""
        }
    },
    mounted(){
        this.materialId = this.$route.query.materialId;
        document.getElementsByTagName("object")[0].setAttribute("data",url.baseURL+"/order/material_stream?id="+this.materialId);
        document.getElementsByTagName("iframe")[0].setAttribute("src",url.baseURL+"/order/material_stream?id="+this.materialId);
    }
}
</script>