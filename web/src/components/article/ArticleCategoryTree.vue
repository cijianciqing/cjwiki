<template>
    <a-tree
            v-if="treeData.length > 0"
            :tree-data="treeData"
            :show-icon="true"
            v-model:expandedKeys="expandedKeys"
            v-model:selectedKeys="selectedKeys"
            :show-line="true"
            @expand="cjExpandEvent"
            @select="cjSelectEvent"
    >
        <template #switcherIcon><down-outlined /></template>
        <!--        v-model:selectedKeys="selectedKeys"

        -->

        <template #cjCustom="{ selected }">
            <FolderOpenOutlined style="color: dodgerblue" v-if="selected"/>
            <FolderOutlined style="color: dodgerblue" v-else/>
        </template>
    </a-tree>
</template>

<script lang="ts">
    import {onMounted, ref} from 'vue';
    import {TreeDataItem} from 'ant-design-vue/es/tree/Tree';
    import axios from "axios";
    import {message} from "ant-design-vue";
    import { useStore } from 'vuex'

    export default {
        name: "ArticleCategoryTree",
        setup() {
            const store = useStore()
            //初始化tree
            onMounted(() => {
                // console.log("CategoryTable mounted......")
                handleQuery();
            });


            /*
            * ant design vue 设置
            * */
            const treeData = ref<TreeDataItem[]>([]);

            /**
             * 查询数据
             **/
            const handleQuery = () => {

                axios.post("/wiki/article/category/list"
                ).then((response) => {
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        treeData.value.push(data.data);
                    } else {
                        message.error(data.msg);
                    }
                });
            };

            //默认展开第一层
            const expandedKeys = ref<string[]>(['1']);

            const selectedKeys = ref<TreeDataItem[]>([]);

            const cjExpandEvent = function (expandedKeys: any, {expanded: bool, node}: any) {
                console.log("cjExpand")
                console.log(expandedKeys)
            }

            const cjSelectEvent = function(selectedKeys : string[], e:{selected: boolean, selectedNodes: any, node: any, event: any}){
                console.log("selectedKeys: ",selectedKeys)
                console.log("selectedKeys[0]: ",selectedKeys[0])
                store.commit("setCategoryId", selectedKeys[0]);

            }

            return {
                // selectedKeys: ref(['0-0-0']),
                treeData,
                expandedKeys,
                selectedKeys,
                cjExpandEvent,
                cjSelectEvent,


            };
        }
    }
</script>

<style scoped>

</style>