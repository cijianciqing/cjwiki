<template>
    <a-layout>

        <a-layout-content
                :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
            <CategoryTable />
        </a-layout-content>
    </a-layout>
</template>

<script>

    import CategoryTable from '../../components/category/CategoryTable.vue'
    export default {
        name: "CategoryAdmin",

        components:{
            CategoryTable
        }
    }
</script>

<style scoped>

</style>