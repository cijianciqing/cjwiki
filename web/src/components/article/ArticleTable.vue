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
<!--        :rowClassName="(record, index) => (index % 2 === 1 ? 'table-striped' : null)"-->
<!--        <template v-slot:cover1="{ text: cover }">-->
<!--            <img v-if="cover" :src="cover" alt="avatar"/>-->
<!--        </template>-->
        <template v-slot:stateRender ="{ text: cjState,record,index }">
            <a-tag v-if="cjState==='start'" color="green">开始</a-tag>
            <a-tag v-if="cjState==='improve'" color="orange">待补充</a-tag>
            <a-tag v-if="cjState==='verify'"  color="purple">待验证</a-tag>
            <a-tag v-if="cjState==='finish'" color="blue">完成</a-tag>
        </template>
        <template v-slot:action="{ text, record }">
            <a-space size="small">
                <a-button type="primary" shape="circle" @click="readArticle(record)">
                    <template #icon><ReadFilled /></template>
                </a-button>
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
            title="Article新增表单"
            v-model:visible="modalVisible"
            :confirm-loading="modalLoading"
            @ok="handleModalOk"
            @cancel="handleCancel"
            width="100%"
            wrapClassName="full-modal"
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
                </a-tree-select>
            </a-form-item>
            <a-form-item label="状态">
                <a-radio-group v-model:value="ebook.articleState" :options="articleStates" button-style="solid">

                </a-radio-group>
            </a-form-item>
            <a-form-item label="内容">
                <div id="wangEditor">
                    <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
                </div>
            </a-form-item>
        </a-form>

    </a-modal>

    <a-modal
            title="Article编辑表单"
            v-model:visible="modalVisible2"
            :confirm-loading="modalLoading2"
            @ok="handleModalOk2"
            @cancel="handleCancel2"
            width="100%"
            wrapClassName="full-modal"
    >
        <a-form :model="article" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="名称">
                <a-input v-model:value="article.name"/>
            </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="article.articleDesc" type="textarea"/>
            </a-form-item>
            <a-form-item label="分类">
                <a-tree-select
                        v-if="treeSelectData.length > 0"
                        v-model:value="article.categoryId"

                        :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                        style="width: 100%"
                        :tree-data="treeSelectData"
                        tree-default-expand-all
                        placeholder="Please select"
                        :show-line="true"
                >
                    <!--                        :default-value="article.categoryId"-->
                    <!--        v-model:selectedKeys="selectedKeys"
                                :default-value="article.categoryId"
                    -->
                    <!--    :show-icon="true"
                            :show-line="true"
                     -->
                </a-tree-select>
            </a-form-item>
            <a-form-item label="状态">
                <a-radio-group v-model:value="article.articleState" :options="articleStates" button-style="solid">

                </a-radio-group>
            </a-form-item>
            <a-form-item label="内容">
                <div id="wangEditor2">

                </div>
            </a-form-item>
        </a-form>
    </a-modal>

    <a-modal
            title="Article查看表单"
            v-model:visible="modalVisible3"
            width="100%"
            wrapClassName="full-modal"
            :footer="null"
    >
        <a-form :model="readonly_article" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="名称">
                <a-input v-model:value="readonly_article.name"/>
            </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="readonly_article.articleDesc" type="textarea"/>
            </a-form-item>
            <a-form-item label="分类">
                <a-input v-model:value="readonly_article.categoryId"/>
            </a-form-item>
        </a-form>
        <div class="11122233223"
             v-html="readonly_article.content">
        </div>
    </a-modal>

</template>

<script lang="ts">
    import {defineComponent, reactive, ref, onMounted, toRef, computed, watch} from 'vue';
    import axios from "axios";
    import {message} from 'ant-design-vue';
    import {Tool} from "@/util/tool";
    import {TreeDataItem} from "ant-design-vue/es/tree/Tree";
    import {useStore} from 'vuex'
    import E from 'wangeditor'

    export default defineComponent({
        name: "ArticleTable",
        components: {},
        setup: function () {
            const store = useStore()


            //创建wangEditor
            let wangEditor: any;
            const createWangEditor = () => {
                wangEditor = new E("#wangEditor")
                wangEditor.create()
                //图片上传-配置服务端接口
                wangEditor.config.uploadImgServer = process.env.VUE_APP_BackEndServer+'/wiki/article/wangeditor/upload/file'
                //图片上传-自定义 timeout 时间
                wangEditor.config.uploadImgTimeout = 20 * 1000
                //关闭粘贴样式的过滤
                wangEditor.config.pasteFilterStyle = false
            }


            //创建wangEditor2
            let wangEditor2: any;
            const createWangEditor2 = (content: any) => {
                wangEditor2 = new E("#wangEditor2")
                wangEditor2.create()
                wangEditor2.txt.html(content);
                //图片上传-配置服务端接口
                wangEditor2.config.uploadImgServer = process.env.VUE_APP_BackEndServer+'/wiki/article/wangeditor/upload/file'
                //图片上传-自定义 timeout 时间
                wangEditor2.config.uploadImgTimeout = 20 * 1000
                //关闭粘贴样式的过滤
                wangEditor2.config.pasteFilterStyle = false

            }

            const loading = ref(false);
            const columns = [
                {
                    title: '名称',
                    dataIndex: 'name',
                    width: '30%'
                },
                {
                    title: '状态',
                    key: 'articleState',
                    dataIndex: 'articleState',
                    width: '10%',
                    slots: {customRender: 'stateRender'},
                    //格式应该不正确。。。
                    // customRender: function ({text, record, index}:any) {
                    //     if(text=="start"){
                    //         return '<a-tag color="green">开始</a-tag>'
                    //     }
                    //     if(text=="improve"){
                    //         return "<a-tag color=\"orange\">待补充</a-tag>"
                    //     }
                    //     if(text=="verify"){
                    //         return "<a-tag color=\"purple\">待验证</a-tag>"
                    //     }
                    //     if(text=="finish"){
                    //         return '<button>完成</button>'
                    //     }
                    // }

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

            const pagination = reactive({
                current: 1,
                pageSize: 5,
                total: 0
            });
            //查询的参数
            const param = reactive({
                name: "",
                categoryId: computed(() => {
                    return store.state.selectedNode.eventKey
                }),
                cjPageReq: {
                    page: pagination.current,
                    size: pagination.pageSize,
                }
            });

            watch(() => param.categoryId, (newVal, oldVal) => {
                console.log(newVal)
                handleQuery(param);
            })


            onMounted(() => {
                // console.log("EbookTable mounted......")
                handleQuery(param);


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


            /*
        * radio 组件
        * */
            const articleStates = ref([]);
            const getArticleStates = () => {
                axios.get("/wiki/article/article/sate"
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        articleStates.value = data.data;
                        console.log("articleStates:", articleStates);
                    } else {
                        message.error(data.msg);
                    }
                });
            }
            /*
           * tree-select 组件
           * */

            const treeSelectData = ref<TreeDataItem[]>([]);
            const getAllCategories = () => {
                axios.post("/wiki/article/category/treeSelect"
                ).then((response) => {
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        treeSelectData.value=data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };


            /**
             * 新增ebook
             */
                // -------- 表单内容 ---------
            const ebook = reactive({
                    data: {
                        categoryId: computed(() => {
                            return store.state.selectedNode.eventKey
                        }),
                        content: "",

                    }
                });
            const modalVisible = ref(false);
            const modalLoading = ref(false);


            const add = () => {
                modalVisible.value = true;
                getArticleStates();
                getAllCategories();
                setTimeout(function () {
                    if (wangEditor == null) {
                        createWangEditor();
                    }else {
                        wangEditor.destroy();//这里做了一次判断，判断编辑器是否被创建，如果创建了就先销毁。
                        createWangEditor();
                    }
                },200)

            };
            const handleCancel = () => {
                clearModalAdd()
            };

            function clearModalAdd() {
                modalVisible.value = false;
                ebook.data = {
                    content: "",
                    categoryId: computed(() => {
                        return store.state.selectedNode.eventKey
                    })
                };
                articleStates.value = []
                treeSelectData.value = []
            }

            const handleModalOk = () => {
                modalLoading.value = true;
                ebook.data.content = wangEditor.txt.html()
                console.log("ebook.data: ", ebook.data);
                axios.post("/wiki/article/article/save", ebook.data).then((response) => {

                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        modalVisible.value = false;
                        clearModalAdd()
                        // 重新加载列表，有可能是添加
                        handleQuery(param);
                    } else {
                        message.error(data.msg);
                    }
                }).finally(() => {

                    modalLoading.value = false;
                });
            };
            /**
             * 编辑ebook
             */
                // -------- 表单内容 ---------
            const article = reactive({data: {content: "",}});
            const modalVisible2 = ref(false);
            const modalLoading2 = ref(false);
            const edit = (record: any) => {
                modalVisible2.value = true;
                console.log("edit.....record............", record)
                article.data = Tool.copy(record);//复制数据，不影响源数据
                getArticleStates();
                getAllCategories();
                setTimeout(function () {
                    if (wangEditor2 == null) {
                        createWangEditor2(record.content);
                    }else {
                        wangEditor2.destroy();//这里做了一次判断，判断编辑器是否被创建，如果创建了就先销毁。
                        createWangEditor2(record.content);

                    }
                },200)


            };

            function clearModalEdit() {
                modalVisible2.value = false;
                article.data = {content: ""};
                articleStates.value = []
                treeSelectData.value = []
            }

            const handleCancel2 = () => {
                clearModalEdit()
            };


            const handleModalOk2 = () => {
                modalLoading2.value = true;
                article.data.content = wangEditor2.txt.html()
                axios.post("/wiki/article/article/save", article.data).then((response) => {

                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        modalVisible2.value = false;
                        clearModalEdit()
                        // 重新加载列表，有可能是添加
                        handleQuery(param);
                    } else {
                        message.error(data.msg);
                    }
                }).finally(() => {
                    modalLoading2.value = false;

                });
            };

            const modalVisible3 = ref(false);
            const readonly_article = reactive({data: {}});
            const readArticle = (record: any) => {
                modalVisible3.value = true;
                readonly_article.data = Tool.copy(record);//复制数据，不影响源数据
                console.log("read.....record............", record)
            }


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


                articleStates,
                treeSelectData,
                //新增表单
                add,
                ebook: toRef(ebook, 'data'),
                modalVisible,
                modalLoading,
                handleModalOk,
                handleCancel,


                //编辑表单
                edit,
                article: toRef(article, 'data'),
                modalVisible2,
                modalLoading2,
                handleModalOk2,
                handleCancel2,

                // read modal
                readArticle,
                readonly_article: toRef(readonly_article, 'data'),
                modalVisible3,

            };
        },

    });
</script>

<style lang="less" scoped>
    //表格斑马纹-->不起作用
    /*.ant-table-striped  td  {*/
    /*    background-color: #b30505;*/
    /*}*/

    img {
        width: 50px;
        height: 50px;
    }
    .full-modal {
        .ant-modal {
            max-width: 100%;
            top: 0;
            padding-bottom: 0;
            margin: 0;
        }
        .ant-modal-content {
            display: flex;
            flex-direction: column;
            height: calc(100vh);
        }
        .ant-modal-body {
            flex: 1;
        }
    }

</style>

