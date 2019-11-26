import Vue from 'vue'
import App from './App.vue'
import router from '../src/router'
import store from '../src/store'
import "./stylesheets/main.scss"

Vue.config.productionTip = false

import axios from "axios"
Vue.prototype.$http = axios

import VueI18n from 'vue-i18n'
Vue.use(VueI18n)

const i18n = new VueI18n({
  locale: 'en-CN',    // 语言标识
  // this.$i18n.locale // 通过切换locale的值来实现语言切换
  messages: {
    'zh-CN': require('./languages/lang/zh'),   // 中文语言包
    'en-US': require('./languages/lang/en')    // 英文语言包
  },
  silentTranslationWarn: true   //关闭双语包警告
})

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
