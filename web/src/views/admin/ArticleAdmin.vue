<template>
    <a-layout id="components-layout-demo-fixed-sider">
        <a-layout-sider :style="{background: '#fff', overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }">
            <div class="logo" >
<!--                <a-avatar shape="square" :size="64" style="backgroundColor:#87d068" icon="user" >  <h3>wqn</h3></a-avatar>-->

            </div>
            <ArticleCategoryTree/>
        </a-layout-sider>
        <a-layout :style="{ marginLeft: '200px' }">
            <a-layout-header :style="{ background: '#fff', padding: '0' }">
                <GlobalHeader/>
            </a-layout-header>
            <a-layout-content :style="{ margin: '24px 16px 0', overflow: 'initial' }">
                <div :style="{ padding: '24px', background: '#fff', textAlign: 'center' }">
                    <ArticleTable/>
                </div>
            </a-layout-content>

        </a-layout>
    </a-layout>
</template>

<script>
    import GlobalHeader from "@/components/GlobalHeader.vue";
    import GlobalFooter from "@/components/GlobalFooter.vue";

    import ArticleCategoryTree from "@/components/article/ArticleCategoryTree.vue";
    import ArticleTable from "@/components/article/ArticleTable.vue";


    export default {
        name: "ArticleAdmin",
        components: {
            GlobalHeader,
            GlobalFooter,
            ArticleCategoryTree,
            ArticleTable,

        }
    }
</script>

<style scoped>
    #components-layout-demo-fixed-sider .logo {
        height: 32px;
        background: dodgerblue;
        margin: 16px;
    }
</style>