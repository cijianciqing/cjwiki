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
        <!--title设计-->
        <template #title="{ key: treeKey, title }">
            <a-dropdown :trigger="['contextmenu']">
                <span>{{ title }}</span>
                <template #overlay>
                    <a-menu @click="({ key: menuKey }) => onContextMenuClick(treeKey, menuKey)">
                        <a-menu-item key="1">新增</a-menu-item>
                        <a-menu-item key="2">修改</a-menu-item>

                        <a-menu-item key="4">查看</a-menu-item>
                        <a-popconfirm
                                title="删除后不可恢复，确认删除?"
                                ok-text="是"
                                cancel-text="否"
                                @confirm="delCategory(treeKey)"
                        >
                            <a-menu-item key="3">删除</a-menu-item>
                        </a-popconfirm>
                    </a-menu>
                </template>
            </a-dropdown>
        </template>
        <!--switcherIcon 修改默认图标-->
        <template #switcherIcon><down-outlined /></template>
        <!--        v-model:selectedKeys="selectedKeys"

        -->

        <!--根据数据-slots，修改 logo-->
        <template #cjCustom="{ selected }">
            <FolderOpenOutlined style="color: dodgerblue" v-if="selected"/>
            <FolderOutlined style="color: dodgerblue" v-else/>
        </template>
    </a-tree>
    <a-drawer
            title="Create a new account"
            :width="720"
            :visible="drawerVisible"
            :body-style="{ paddingBottom: '80px' }"
            @close="drawerOnCancel"
    >
        <a-form :model="cjDrawer"  layout="vertical">
            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="Name" name="name">
                        <a-input v-model:value="cjDrawer.name" placeholder="Please enter user name" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                 <!--   <a-form-item label="Url" name="url">
                        <a-input
                                v-model:value="form.url"
                                style="width: 100%"
                                addon-before="http://"
                                addon-after=".com"
                                placeholder="please enter url"
                        />
                    </a-form-item>-->
                </a-col>
            </a-row>

            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="Description" name="description">
                        <a-textarea
                                v-model:value="cjDrawer.categoryDesc"
                                :rows="4"
                                placeholder="please enter url description"
                        />
                    </a-form-item>
                </a-col>
            </a-row>
        </a-form>
        <div
                :style="{
        position: 'absolute',
        right: 0,
        bottom: 0,
        width: '100%',
        borderTop: '1px solid #e9e9e9',
        padding: '10px 16px',
        background: '#fff',
        textAlign: 'right',
        zIndex: 1,
      }"
        >
            <a-button style="margin-right: 8px" @click="drawerOnCancel">Cancel</a-button>
            <a-button type="primary" @click="drawerOnSubmit">Submit</a-button>

        </div>
    </a-drawer>
</template>

<script lang="ts">
    import {onMounted, ref} from 'vue';
    import {TreeDataItem} from 'ant-design-vue/es/tree/Tree';
    import axios from "axios";
    import {message} from "ant-design-vue";
    import { useStore } from 'vuex'
    import {Tool} from '@/util/tool'

    export default {
        name: "ArticleCategoryTree",
        setup: function () {
            const store = useStore()


            /*
            * ant design vue 设置
            * */
            const treeData = ref<TreeDataItem[]>([]);

            //初始化tree
            onMounted(() => {
                // console.log("CategoryTable mounted......")
                handleQuery();
            });


            /**
             * 查询数据
             **/
            const handleQuery = () => {

                axios.post("/wiki/article/category/list"
                ).then((response) => {
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        // treeData.value.push(data.data);
                        treeData.value=data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };

            //默认展开第一层
            const expandedKeys = ref<string[]>(['1']);

            const selectedKeys = ref<TreeDataItem[]>([]);

            const cjExpandEvent = function (expandedKeys: any, {expanded: bool, node}: any) {
                // console.log("cjExpand: ")
                console.log(expandedKeys)
            }

            const cjSelectEvent = function (selectedKeys: string[], e: { selected: boolean, selectedNodes: any, node: any, event: any }) {
                // console.log("selectedKeys: ", selectedKeys)
                // console.log("selectednode: ", e.node)
                // console.log("selectednode: ", e.node.title)
                // cjDrawer.value={categoryDesc: "", name: "", parentId: ""}
                store.commit("setSelectNode", e.node);
            }

            const onContextMenuClick = (treeKey: string, menuKey: string) => {
                // console.log(`treeKey: ${treeKey}, menuKey: ${menuKey}`);
                // expandedKeys.value=[treeKey]

                if (menuKey == "2" || menuKey == "4") {//修改、查看
                    /*
                    * 获取选中treeNode的数据
                    * 赋值给cjDrawer
                    * */
                    axios.get("/wiki/article/category/" + treeKey
                    ).then((response) => {
                        const data = response.data;
                        if (data.code == process.env.VUE_APP_ResponseSuccess) {
                            cjDrawer.value = data.data;
                        } else {
                            message.error(data.msg);
                        }
                    });

                    drawerVisible.value = true
                } else if (menuKey == "1") {//修改
                    drawerVisible.value = true
                    cjDrawer.value.parentId = treeKey
                }else if (menuKey == "3") {//删除
                    delCategory(treeKey)
                }
            };
            /*
            * Drawer操作
            *
            * */
            const cjDrawer = ref({
                // name:"",
                // categoryDesc:"",
                parentId:"1"//默认：设置父节点为CJUPUP
            });

            const drawerVisible = ref<boolean>(false);

            const showDrawer = () => {
                drawerVisible.value = true;
            };

            const drawerOnSubmit = () => {
                axios.post("/wiki/article/category/save", cjDrawer.value
                ).then((response) => {
                    const data = response.data
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {

                        drawerVisible.value = false
                        handleQuery()
                    } else {
                        message.error(data.msg);
                    }
                });
            }

            const drawerOnCancel = () => {
                drawerVisible.value = false;
                cjDrawer.value = ({
                    parentId:"1"//默认：设置父节点为CJUPUP
                });
            };

            /**
             * 删除数据
             **/
            const delCategory = (id: String) => {
                axios.delete("/wiki/article/category/delete/" + id).then((response) => {
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        // 重新加载category
                        handleQuery();
                    }
                });
            };


            return {
                // selectedKeys: ref(['0-0-0']),
                treeData,
                expandedKeys,
                selectedKeys,
                cjExpandEvent,
                cjSelectEvent,

                onContextMenuClick,//右击操作

                //drawer
                cjDrawer,
                drawerVisible,
                showDrawer,
                drawerOnCancel,
                drawerOnSubmit,
                delCategory,

            };
        }
    }
</script>

<style scoped>

</style>