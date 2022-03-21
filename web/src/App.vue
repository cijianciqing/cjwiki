<template>
    <a-layout>
        <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
            <GlobalHeader/>
        </a-layout-header>
        <a-layout-content :style="{ position: 'fixed',padding: '0 50px',  marginTop: '64px', bottom:'100px'}">
            <router-view></router-view>
        </a-layout-content>
        <a-layout-footer style="text-align: center">
            <GlobalFooter/>
        </a-layout-footer>

    </a-layout>
</template>
<script>
    import GlobalHeader from "./components/GlobalHeader.vue";
    import GlobalFooter from "./components/GlobalFooter.vue";

    export default {
        components: {
            GlobalHeader,
            GlobalFooter,
        },
    };
</script>

<style>
    #components-layout-demo-top-side-2 .logo {
        float: left;
        width: 120px;
        height: 31px;
        margin: 16px 24px 16px 0;
        background: rgba(255, 255, 255, 0.3);
    }

    .ant-row-rtl #components-layout-demo-top-side-2 .logo {
        float: right;
        margin: 16px 0 16px 24px;
    }

    .site-layout-background {
        background: #fff;
    }
</style>
