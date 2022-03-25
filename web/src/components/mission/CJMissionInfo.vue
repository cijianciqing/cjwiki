<template>

    <a-table
            :columns="columns"
            :row-key="record => record.id"
            :data-source="missions"
            :loading="loading"
            :pagination="false"
    >

        <template v-slot:action="{ text, record }">
            <a-space size="small">
                <a-button type="primary" >
                    编辑
                </a-button>
                <a-popconfirm
                        title="删除后不可恢复，确认删除?"
                        ok-text="是"
                        cancel-text="否"
                        @confirm="handleDelete(record.id)"
                >
                    <a-button type="primary" danger>
                        删除
                    </a-button>
                </a-popconfirm>
            </a-space>
        </template>
    </a-table>

</template>

<script lang="ts">
    import {defineComponent, reactive, ref, onMounted, toRef} from 'vue';
    import axios from "axios";
    import { message } from 'ant-design-vue';



    export default defineComponent({
        setup() {
            const columns = [
                {
                    title: '标题',
                    dataIndex: 'taskName',
                },
                {
                    title: '描述',
                    dataIndex: 'taskDesc',
                },
                {
                    title: '重要程度',
                    dataIndex: 'taskImportant',
                },
                {
                    title: 'Action',
                    key: 'action',
                    slots: { customRender: 'action' }
                }
            ];
            const missions = reactive({data:[]});
            const loading = ref(false);
            onMounted(() => {
                // console.log("CategoryTable mounted......")
                handleQuery();
            });
            /**
             * 查询数据
             **/
            const handleQuery = () => {
                loading.value = true;
                axios.post("/wiki/mission/table"
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess){
                        missions.data = data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };
            /**
             * 删除数据
             **/
            const handleDelete = (id: number) => {
                axios.delete("/wiki/ebook/delete/" + id).then((response) => {
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess){
                        // 重新加载列表
                        handleQuery();
                    }
                });
            };

            return {
                missions: toRef(missions, 'data'),
                columns,
                loading,
                handleQuery,
                handleDelete,

            };
        },

    });
</script>
<style scoped>
    img {
        width: 50px;
        height: 50px;
    }
</style>


