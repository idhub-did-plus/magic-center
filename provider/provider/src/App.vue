<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<style lang="scss">
  #app{
    width:1200px;
    margin:0 auto;
  }
</style>
<script>
import unReceivedList from "../src/views/unReceivedList"
export default {
  components:{
    unReceivedList
  }
}
</script>
