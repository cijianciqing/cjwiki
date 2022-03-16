<template>
    <a-layout-content style="padding: 0 50px">
        <a-layout style="padding: 24px 0; background: #fff">
            <a-layout-sider width="200" style="background: #fff">
                <ArticleCategoryTree />
            </a-layout-sider>
            <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
                <ArticleTable />
            </a-layout-content>
        </a-layout>
    </a-layout-content>
</template>

<script>
    import ArticleCategoryTree  from "@/components/article/ArticleCategoryTree.vue";
    import ArticleTable from "@/components/category/ArticleTable.vue";
    export default {
        name: "ArticleAdmin",
        components:{
            ArticleCategoryTree,
            ArticleTable
        }
    }
</script>

<style scoped>

</style>