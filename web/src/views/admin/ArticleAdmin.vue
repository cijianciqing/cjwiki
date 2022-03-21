<template>
    <a-layout style="padding: 20px">
        <a-layout-sider :style="{width:'20%', overflow: 'auto',  background: '#c61818', height: 'calc(100vh - 100px)', left: 0 }">
            <ArticleCategoryTree/>
        </a-layout-sider>
        <a-layout>
            <a-layout-content :style="{ width:'80%',marginLeft: '200px' }">
                <ArticleTable/>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>

<script>
    import ArticleCategoryTree from "@/components/article/ArticleCategoryTree.vue";
    import ArticleTable from "@/components/article/ArticleTable.vue";

    export default {
        name: "ArticleAdmin",
        components: {
            ArticleCategoryTree,
            ArticleTable
        }
    }
</script>

<style scoped>

</style>