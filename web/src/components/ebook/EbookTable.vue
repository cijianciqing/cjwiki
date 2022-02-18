<template>
    <p>
        <a-form layout="inline" :model="param">
            <a-form-item>
                <a-input v-model:value="param.name" placeholder="名称">
                </a-input>
            </a-form-item>
            <a-form-item>
                <a-button type="primary" @click="handleQuery(param)">
                    查询
                </a-button>
            </a-form-item>
            <a-form-item>
                <a-button type="primary" @click="add()">
                    新增
                </a-button>
            </a-form-item>
        </a-form>
    </p>
    <a-table
            :columns="columns"
            :row-key="record => record.id"
            :data-source="ebooks"

            :loading="loading"
            :pagination="pagination"
            @change="handleTableChange"
    >

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
                    <a-button type="primary" danger>
                        删除
                    </a-button>
                </a-popconfirm>
            </a-space>
        </template>
    </a-table>
    <a-modal
            title="电子书表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
    >
        <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="封面">
                <a-input v-model:value="ebook.cover" />
            </a-form-item>
            <a-form-item label="名称">
                <a-input v-model:value="ebook.name" />
            </a-form-item>
             <a-form-item label="分类一">
                 <a-input v-model:value="ebook.category1Id" />
             </a-form-item>
             <a-form-item label="分类二">
                 <a-input v-model:value="ebook.category2Id" />
             </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="ebook.description" type="textarea" />
            </a-form-item>
        </a-form>
    </a-modal>

  <!--  <a-button type="primary" @click="showModal">Open Modal</a-button>
    <a-modal v-model:visible="visible1" title="Basic Modal" @ok="handleOk1">
        <p>Some contents...</p>
        <p>Some contents...</p>
        <p>Some contents...</p>
    </a-modal>-->
</template>

<script lang="ts">
    // import {SmileOutlined, DownOutlined} from '@ant-design/icons-vue';
    import {defineComponent, reactive, ref, onMounted, toRef} from 'vue';
    import axios from "axios";
    import { message } from 'ant-design-vue';
    import {Tool} from "@/util/tool";



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
            //查询的参数
            const param = reactive({
                name:"",
                cjPageReq: {
                    page: 1,
                    size: 3,
                }});
            //数据源需要array 而不是 object

            const ebooks = reactive({data:[]});

            let pagination = reactive({
                current: 1,
                pageSize: 3,
                total: 0
            });
            const loading = ref(false);


            onMounted(() => {
                // console.log("EbookTable mounted......")
                handleQuery(param);
            });
            /**
             * 查询数据
             **/
            const handleQuery = (params :any) => {
                loading.value = true;
                console.log("handleQuery():",params)
                axios.post("/wiki/ebook/table", params
                ).then((response) => {
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
                        handleQuery(param);
                    }
                });
            };


            /**
             * pagination表格点击页码时触发
             */
            const handleTableChange = (pagination: any) => {
                console.log("看看自带的分页参数都有啥：" + pagination);
                param.cjPageReq.page = pagination.current
                param.cjPageReq.size = pagination.pageSize
                handleQuery(param);
            };


            // -------- 表单内容 ---------
            const ebook = reactive({data:{}});
            const modalVisible = ref(false);
            const modalLoading = ref(false);

            /**
             * 新增ebook
             */

            const add = () => {
                modalVisible.value = true;
                ebook.data = {};
            };
            /**
             * 编辑ebook
             */
            const edit = (record: any) => {
                modalVisible.value = true;
                console.log("edit.....record............",record)
                ebook.data = Tool.copy(record);
            };
            const handleModalOk = () => {
                modalLoading.value = true;
                axios.post("/wiki/ebook/save", ebook.data).then((response) => {
                    modalLoading.value = false;
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess){
                        modalVisible.value = false;

                        // 重新加载列表，有可能是添加
                        handleQuery(param);
                    } else {
                        message.error(data.msg);
                    }
                });
            };


            /*
            * 测试
            *
            * */

            //modal测试
            // const visible1 = ref(false);
            //
            // const showModal = () => {
            //     visible1.value = true;
            // };
            //
            // const handleOk = (e: MouseEvent) => {
            //     console.log(e);
            //     visible1.value = false;
            // };

            return {
                param,
                ebooks: toRef(ebooks, 'data'),
                pagination,
                columns,
                loading,
                handleQuery,
                handleDelete,

                handleTableChange,

                ebook: toRef(ebook, 'data'),
                modalVisible,
                modalLoading,
                handleModalOk,
                add,
                edit,

                //测试数据
                // visible1,
                // showModal,
                // handleOk,

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


