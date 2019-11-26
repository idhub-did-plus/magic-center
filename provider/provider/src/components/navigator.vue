<template>
    <div class="navigator">
        <ul class="nav nav-tabs">
            <li @click="toggle($event)" id="unReceivedList" role="presentation" class="active"><a href="#">{{$t('m.nav.unreceive')}}</a></li>
            <li @click="toggle($event)" id="processingList" role="presentation"><a href="#">{{$t('m.nav.received')}}</a></li>
            <li @click="toggle($event)" id="history" role="presentation"><a href="#">{{$t('m.nav.history')}}</a></li>
            <li @click="toggle($event)" id="management" role="presentation"><a href="#">{{$t('m.nav.manage')}}</a></li>
            <li @click="switchLang()" id="lang" role="presentation"><a href="#">{{$t('m.nav.lang')}}</a></li>
        </ul>
    </div>
</template>
<style lang="scss">
    .navigator{
        margin-top: 30px;
        #lang{
            float:right;
            margin-right: 100px;
        }
    }
</style>
<script>
export default {
    methods:{
        toggle($event){
            //路由跳转
            var path =  $event.currentTarget.getAttribute("id");
            this.$router.push({path:path});
        },
        switchLang(){
            var choose = window.confirm("是否确定切换语言？");
            if(choose){
                const locale = this.$i18n.locale;
                locale === 'zh-CN' ? this.$i18n.locale = 'en-US' : this.$i18n.locale = 'zh-CN';
            }
        }
    }
}
</script>