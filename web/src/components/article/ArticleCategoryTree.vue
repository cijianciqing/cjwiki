<template>
    <a-tree
            :tree-data="treeData"
            show-icon
            default-expand-all
            v-model:selectedKeys="selectedKeys"
            :show-line="true    "
    >

        <template #custom="{ selected }">
            <FolderOpenOutlined v-if="selected"/>
            <FolderOutlined v-else/>
        </template>
    </a-tree>
</template>

<script lang="ts">
    import {  ref } from 'vue';
    import { TreeDataItem } from 'ant-design-vue/es/tree/Tree';
    export default {
        name: "ArticleCategoryTree",
        setup(){
            const treeData: TreeDataItem[] = [
                {
                    title: 'parent 1',
                    key: '0-0',
                    slots: {
                        icon: 'custom',
                    },
                    children: [
                        { title: 'leaf', key: '0-0-0', slots: { icon: 'custom' } },
                        { title: 'leaf', key: '0-0-1', slots: { icon: 'custom' } },
                    ],
                },
            ];

            return {
                selectedKeys: ref(['0-0-0']),
                treeData,
            };
        }
    }
</script>

<style scoped>

</style>