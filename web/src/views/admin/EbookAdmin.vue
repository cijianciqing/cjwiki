<template>
    <a-layout>

        <a-layout-content
                :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
            <EbookTable />
        </a-layout-content>
    </a-layout>
</template>

<script>

    import EbookTable from '../../components/ebook/EbookTable.vue'
    export default {
        name: "EbookAdmin",

        components:{
            EbookTable
        }
    }
</script>

<style scoped>

</style>