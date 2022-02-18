<template>
    <a-table
            :columns="columns"
            :row-key="record => record.id"
            :data-source="ebooks"

            :loading="loading"
            :pagination="pagination"
    >
<!--

     @change="handleTableChange"-->
        <template v-slot:cover1="{ text: cover }">
            <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
            <a-space size="small">
                <a-button type="primary" @click="edit(record)">
                    编辑
                </a-button>
                <a-popconfirm
                        title="删除后不可恢复，确认删除?"
                        ok-text="是"
                        cancel-text="否"
                        @confirm="handleDelete(record.id)"
                >
                    <a-button type="danger">
                        删除
                    </a-button>
                </a-popconfirm>
            </a-space>
        </template>
    </a-table>
</template>
<script lang="ts">
    // import {SmileOutlined, DownOutlined} from '@ant-design/icons-vue';
    import {defineComponent, reactive, ref, onMounted, toRef} from 'vue';
    import axios from "axios";
    import { message } from 'ant-design-vue';



    export default defineComponent({
        name: "EbookTable",
        setup() {
            const columns = [
                {
                    title: '封面',
                    dataIndex: 'cover',
                    slots: { customRender: 'cover1' }
                },
                {
                    title: '名称',
                    dataIndex: 'name'
                },
                {
                    title: '分类一',
                    key: 'category1Id',
                    dataIndex: 'category1Id'
                },
                {
                    title: '分类二',
                    dataIndex: 'category2Id'
                },
                {
                    title: '文档数',
                    dataIndex: 'docCount'
                },
                {
                    title: '阅读数',
                    dataIndex: 'viewCount'
                },
                {
                    title: '点赞数',
                    dataIndex: 'voteCount'
                },
                {
                    title: 'Action',
                    key: 'action',
                    slots: { customRender: 'action' }
                }
            ];
            let param = reactive({});
            //数据源需要array 而不是 object

            let ebooks = reactive({data:[]});
            let pagination = reactive({
                current: 1,
                pageSize: 2,
                total: 0
            });
            let loading = ref(false);


            onMounted(() => {
                // console.log("EbookTable mounted......")
                handleQuery({
                    cjPageReq: {
                        page: 1,
                        size: 3
                    },
                });
            });
            /**
             * 查询数据
             **/
            const handleQuery = (params :any) => {
                loading.value = true;
                axios.post("/wiki/ebook/table", {
                        name: params.name,
                        cjPageReq: {
                            page: params.cjPageReq.page,
                            size: params.cjPageReq.size,
                        },
                }).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess){
                        ebooks.data = data.data.list;
                        // 更新分页按钮
                        pagination.current = params.cjPageReq.page;
                        pagination.total = data.data.total;
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
                        handleQuery({
                            cjPageReq: {
                                page: pagination.current,
                                size: pagination.pageSize,
                            }
                        });
                    }
                });
            };

            return {
                param,
                ebooks: toRef(ebooks, 'data'),
                pagination,
                columns,
                loading,
                handleQuery,
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


