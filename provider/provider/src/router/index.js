import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import processingDetail from "./processingDetail"
import unreceived from "./unreceived"
import history from "./history"
import management from "./management"
import processing from "./processing"
import newPage from "./newPage"
import Login from "./login"

export default new Router({
  routes: [
    {path:"/",redirect:"unReceivedList"},
    processingDetail,unreceived,management,history,processing,newPage
  ]
})
