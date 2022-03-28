<template>
    <a-layout id="components-layout-demo-fixed-sider">
        <a-layout-sider :style="{background: '#fff', overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }">
            <div class="logo" >
                <a-tag color="#55acee" >
                    <template #icon>
                        <twitter-outlined />
                    </template>
                    Twitter
                </a-tag>
              <!--  <a-dropdown>
                    <template #overlay>
                        <a-menu @click="handleMenuClick">
                            <a-menu-item key="1">新增</a-menu-item>
                            <a-menu-item key="2">修改</a-menu-item>
                            <a-menu-item key="3">删除</a-menu-item>
                        </a-menu>
                    </template>
                    <a-button>
                        操作
                        <DownOutlined />
                    </a-button>
                </a-dropdown>-->
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
        /*background: dodgerblue;*/
        margin: 16px;
    }
</style>