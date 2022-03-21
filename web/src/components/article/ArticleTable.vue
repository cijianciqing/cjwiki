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
            <img v-if="cover" :src="cover" alt="avatar"/>
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
            title="Article表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
    >
        <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="名称">
                <a-input v-model:value="ebook.name"/>
            </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="ebook.articleDesc" type="textarea"/>
            </a-form-item>
            <a-form-item label="分类">
                <a-tree-select
                        v-if="treeSelectData.length > 0"
                        v-model:value="ebook.categoryId"
                        :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                        style="width: 100%"
                        :tree-data="treeSelectData"
                        tree-default-expand-all
                        placeholder="Please select"
                        :show-line="true"
                >
                    <!--        v-model:selectedKeys="selectedKeys"-->
                    <!--    :show-icon="true"
                            :show-line="true"
                     -->
                </a-tree-select>
            </a-form-item>
            <a-form-item label="状态">
                <a-radio-group v-model:value="ebook.articleState" :options="articleStates" button-style="solid">

                    <!--                     <a-radio-button v-for="(value, key) in articleStates.value" :key="key" :value="value">{{key}}</a-radio-button>-->
                    <!--                     <a-radio-button value="b">Shanghai</a-radio-button>-->
                    <!--                     <a-radio-button value="c">Beijing</a-radio-button>-->
                    <!--                     <a-radio-button value="d">Chengdu</a-radio-button>-->
                </a-radio-group>
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
    import {defineComponent, reactive, ref, onMounted, toRef, computed, watch} from 'vue';
    import axios from "axios";
    import {message} from 'ant-design-vue';
    import {Tool} from "@/util/tool";
    import {TreeDataItem} from "ant-design-vue/es/tree/Tree";
    import { useStore } from 'vuex'


    export default defineComponent({
        name: "ArticleTable",
        setup: function () {
            const store = useStore()

            const loading = ref(false);
            const columns = [
                // {
                //     title: '封面',
                //     dataIndex: 'cover',
                //     slots: { customRender: 'cover1' }
                // },
                {
                    title: '名称',
                    dataIndex: 'name',
                    width: '30%'
                },
                {
                    title: '状态',
                    key: 'articleState',
                    dataIndex: 'articleState',
                    width: '10%'
                },
                {
                    title: '文章描述',
                    key: 'articleDesc',
                    dataIndex: 'articleDesc',
                    width: '40%'
                },
                {
                    title: '操作',
                    key: 'action',
                    slots: {customRender: 'action'},
                    width: '20%'
                }
            ];



            //数据源需要array 而不是 object
            const ebooks = ref([]);
            // reactive({data: []});

            let pagination = reactive({
                current: 1,
                pageSize: 5,
                total: 0
            });
            //查询的参数
            const param = reactive({
                name: "",
                categoryId:  computed(() => {
                    return store.state.categoryId
                }),
                cjPageReq: {
                    page: pagination.current,
                    size: pagination.pageSize,
                }
            });

            watch(()=> param.categoryId,(newVal,oldVal)=>{
                console.log(newVal)
                handleQuery(param);
            })


            // param.categoryId = computed(() => {
            //     return store.state.categoryId
            // }).value


            // 直接显示当前user,登录后更新user
            // const ebooks = computed(() =>{
            //     // console.log("computed change",user)
            //     return store.state.articleTable
            // })



            // watch(() => store.state., (newValue, oldValue) => { //直接监听
            //     console.log("categoryId changed");
            //     handleQuery(param);
            // });

            onMounted(() => {
                // console.log("EbookTable mounted......")
                handleQuery(param);
                // getArticleStates();
            });


            /**
             * 查询数据
             **/
            const handleQuery = (params: any) => {
                loading.value = true;
                // console.log("handleQuery():",params)
                axios.post("/wiki/article/article/table", params
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        ebooks.value = data.data.list;
                        // store.state.articleTable= data.data.list;
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
                axios.delete("/wiki/article/article/delete/" + id).then((response) => {
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
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
                // param.cjPageReq.size = pagination.pageSize
                handleQuery(param);
            };


            // -------- 表单内容 ---------
            const ebook = reactive({data: {}});
            const modalVisible = ref(false);
            const modalLoading = ref(false);

            /**
             * 新增ebook
             */

            const add = () => {
                modalVisible.value = true;
                ebook.data = {};
                getArticleStates();
                getAllCategories();
            };

            const articleStates = reactive({data: []});
            const getArticleStates = () => {
                axios.get("/wiki/article/article/sate"
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        articleStates.data = data.data;
                        console.log("articleStates:", articleStates);
                    } else {
                        message.error(data.msg);
                    }
                });
            }

            const treeSelectData = ref<TreeDataItem[]>([]);
            const getAllCategories = () => {

                axios.post("/wiki/article/category/treeSelect"
                ).then((response) => {
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        treeSelectData.value.push(data.data);
                    } else {
                        message.error(data.msg);
                    }
                });
            };
            /**
             * 编辑ebook
             */
            const edit = (record: any) => {
                modalVisible.value = true;
                console.log("edit.....record............", record)
                ebook.data = Tool.copy(record);//复制数据，不影响源数据
            };
            const handleModalOk = () => {
                modalLoading.value = true;
                axios.post("/wiki/article/article/save", ebook.data).then((response) => {
                    modalLoading.value = false;
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
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
                // ebooks: toRef(ebooks, 'data'),
                ebooks,
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

                articleStates: toRef(articleStates, 'data'),
                treeSelectData,
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


