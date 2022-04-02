<template>
    <a-layout>
        <a-layout-header style=" position: fixed; z-index: 1; width: 100%;">

            <GlobalHeader/>
            <!--            <div style=" padding: 10px; background-color: lightskyblue">-->

            <!--            </div>-->
        </a-layout-header>
        <a-row style="margin-top: 64px">
            <a-col :span="16" style=" padding: 10px;">
                <a-form layout="inline">
                    <a-form-item>
                        <a-input placeholder="名称">
                        </a-input>
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary">
                            查询
                        </a-button>
                    </a-form-item>
                    <a-form-item>
                        <a-button type="primary" @click="addMission">
                            新增
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-col>
            <a-col :span="6" style=" padding: 10px;">
                <a-tag color="#55acee">
                    <template #icon>
                        <twitter-outlined/>
                    </template>
                    Mission:
                </a-tag>
                <a-tag>目标进展</a-tag>

            </a-col>
            <a-col :span="2" style=" padding: 10px;">
                <a-button type="primary" shape="circle" @click="addStep">
                    <template #icon>
                        <PlusCircleOutlined/>
                    </template>
                </a-button>
            </a-col>
        </a-row>

        <!--        <a-layout-content :style="{ marginTop: '120px' }">
                    style="margin: 20px"
        -->

        <a-row :gutter="16">
            <a-col style="background-color: lightskyblue;" :span="16">
                <a-table
                        :columns="columns"
                        :row-key="record => record.id"
                        :data-source="missions"
                        :loading="loading"
                        :pagination="false"
                        :row-selection="tableRowSelection"
                        @change="handleChange"
                        :rowClassName="cjTableRowClass"
                        :customHeaderRow="cjTableHeaderRow"
                >

                    <!-- bordered 带边框-->
                    <template v-slot:importantRender="{ text: cjImportant,record,index }">
                        <a-tag v-if="parseInt(cjImportant) > 7" color="red">非常重要</a-tag>
                        <a-tag v-if=" parseInt(cjImportant)> 4  &&  parseInt(cjImportant)< 8 " color="orange">重要</a-tag>
                        <a-tag v-if=" parseInt(cjImportant)> 0  &&  parseInt(cjImportant)< 4  " color="blue">普通</a-tag>
                        <a-tag v-if="parseInt(cjImportant) === 0" color="cyan">无所谓</a-tag>
                    </template>

                    <template v-slot:finishRender="{ text: cjFinish,record,index }">
                        <a-tag v-if="cjFinish === '进行中' " color="green">进行中</a-tag>
                        <a-tag v-if="cjFinish === '已完成' " color="red">已完成</a-tag>
                    </template>

                    <template v-slot:action="{ text, record }">
                        <a-space size="small">
                            <a-tooltip>
                                <template #title>新增Mission</template>
                                <a-button type="primary" shape="circle" @click="addChildMission(record.id)">
                                    <template #icon>
                                        <PlusOutlined />
                                    </template>
                                </a-button>
                            </a-tooltip>
                            <a-tooltip>
                                <template #title>编辑Mission</template>
                                <a-button type="primary" shape="circle" @click="editMission(record)">
                                    <template #icon>
                                        <EditOutlined/>
                                    </template>
                                </a-button>
                            </a-tooltip>
                            <a-popconfirm
                                    title="子任务也会同步删除，确认删除?"
                                    ok-text="Confirm"
                                    cancel-text="Cancel"
                                    @confirm="delMission(record.id)"
                            >
                                <template #icon>
                                    <question-circle-outlined style="color: red"/>
                                </template>
                                <a-button type="primary" danger shape="circle">
                                    <template #icon>
                                        <CloseCircleOutlined/>
                                    </template>
                                </a-button>
                            </a-popconfirm>
                        </a-space>
                    </template>
                </a-table>
            </a-col>
            <a-col :span="8">
                <a-list item-layout="horizontal" :data-source="steps">
                    <template #renderItem="{ item }">
                        <a-list-item>

<!--                            :description="item.description"-->
                            <a-list-item-meta >
                                <template #title>
                                    <h4>{{ item.title }}</h4>
                                </template>
                                <template #avatar>
                                    <a-avatar style="background-color: #1890ff">
                                        <template #icon>
                                            <UserOutlined/>
                                        </template>
                                    </a-avatar>
                                    <!--                                        <a-avatar :src="item.avatar" />-->
                                </template>
                                <template #description>
                                    {{item.description}}
<!--                                    <div v-html="item.description"></div>-->
<!--                                    {{item.description}}-->
                                </template>
                            </a-list-item-meta>
                            <template #actions>
                                <a-tag color="cyan">{{item.updateTime}}</a-tag>

                                <a-button type="text" @click="editMissionStep(item)">edit</a-button>
                                <a-popconfirm placement="leftTop"
                                        title="确认删除?"
                                        ok-text="Confirm"
                                        cancel-text="Cancel"
                                        @confirm="delMissionStep(item.id)"
                                >
                                    <a-button type="text" danger>del</a-button>
                                </a-popconfirm>


                            </template>
                        </a-list-item>
                    </template>
                </a-list>
            </a-col>
        </a-row>


        <!--        </a-layout-content>-->

    </a-layout>



    <a-drawer
            title="Mission Form"
            width="90%"
            :visible="missionModalVisible"
            :body-style="{ paddingBottom: '80px' }"
            @close="missionModalCancel"
            :afterVisibleChange="afterMissionVisibleChange"
    >
        <a-form :model="step"  layout="vertical">

            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="创建时间">
                        <a-tag color="cyan">{{mission.createTime}}</a-tag>
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="最近更新时间">
                        <a-tag color="cyan">{{mission.updateTime}}</a-tag>
                    </a-form-item>
                </a-col>
            </a-row>

            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="标题">
                        <a-input id="inputNumber" v-model:value="mission.taskName" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="重要程度">
                        <a-input-number id="inputNumber" v-model:value="mission.taskImportant" :min="0" :max="10"/>
                    </a-form-item>
                </a-col>
            </a-row>


            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="进度">
                        <a-radio-group v-model:value="mission.finishStatus" button-style="solid">
                            <a-radio-button value="进行中">进行中</a-radio-button>
                            <a-radio-button value="已完成">已完成</a-radio-button>
                        </a-radio-group>
                    </a-form-item>
                </a-col>
            </a-row>

            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="描述" name="description">
                        <a-textarea
                                v-model:value="mission.taskDesc"
                                :rows="4"
                                placeholder="please enter mission description"
                        />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="内容">
<!--                        <a-textarea v-model:value="mission.taskContent" auto-size-->
<!--                                    placeholder="please enter mission content"-->
<!--                        />-->
                        <div id="wangEditor_mission">

                        </div>
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
            <a-button style="margin-right: 8px" @click="missionModalCancel">Cancel</a-button>
            <a-button type="primary" @click="missionModalOk">Submit</a-button>

        </div>
    </a-drawer>


    <a-drawer
            title="Step Form"
            width="90%"
            :visible="stepModalVisible"
            :body-style="{ paddingBottom: '80px' }"
            @close="stepModalCancel"
            :afterVisibleChange="afterStepVisibleChange"
    >
        <a-form :model="mission"  layout="vertical">

            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="创建时间">
                        <a-tag color="cyan">{{step.createTime}}</a-tag>
                    </a-form-item>
                </a-col>
                <a-col :span="12">
                    <a-form-item label="最近更新时间">
                        <a-tag color="cyan">{{step.updateTime}}</a-tag>
                    </a-form-item>
                </a-col>
            </a-row>

            <a-row :gutter="16">
                <a-col :span="12">
                    <a-form-item label="标题">
                        <a-input id="inputNumber" v-model:value="step.title" />
                    </a-form-item>
                </a-col>
                <a-col :span="12">

                </a-col>
            </a-row>


            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="描述" name="description">
                        <a-textarea
                                v-model:value="step.description"
                                :rows="4"
                                placeholder="please enter step description"
                        />
                    </a-form-item>
                </a-col>
            </a-row>
            <a-row :gutter="16">
                <a-col :span="24">
                    <a-form-item label="内容">
                        <!--                        <a-textarea v-model:value="mission.taskContent" auto-size-->
                        <!--                                    placeholder="please enter mission content"-->
                        <!--                        />-->
                        <div id="wangEditor_step">

                        </div>
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
            <a-button style="margin-right: 8px" @click="stepModalCancel">Cancel</a-button>
            <a-button type="primary" @click="stepModalOk">Submit</a-button>

        </div>
    </a-drawer>


</template>
<script lang="ts">
    import {defineComponent, onMounted, reactive, ref, toRef, watch, computed} from 'vue';
    import GlobalHeader from "@/components/GlobalHeader.vue";
    // import CJMission from "@/components/mission/CJMissionInfo.vue";
    import axios from "axios";
    import {message} from "ant-design-vue";
    import {Tool} from "@/util/tool";
    import E from 'wangeditor'

/*
* 对于有id-pid的数据
*
* 选中的节点与新增操作有关，与select\update\delete操作无关
* */
    export default defineComponent({
        components: {
            GlobalHeader,
            // CJMission,
        },
        setup() {

            /*
            * 当页面加载时，自动获取所有的mission
            * */
            onMounted(() => {
                // console.log("CategoryTable mounted......")
                queryMission();
                // queryMissionSteps(selectedMissionId.value);
            });

            let wangEditor_mission: any;
            const createWangEditor = () => {
                wangEditor_mission = new E("#wangEditor_mission")
                wangEditor_mission.create()
                //图片上传-配置服务端接口
                wangEditor_mission.config.uploadImgServer = process.env.VUE_APP_BackEndServer+'/wiki/article/wangeditor/upload/file'
                //图片上传-自定义 timeout 时间
                wangEditor_mission.config.uploadImgTimeout = 20 * 1000
                //关闭粘贴样式的过滤
                wangEditor_mission.config.pasteFilterStyle = false
            }

            let wangEditor_step: any;
            const createWangEditor_step = () => {
                wangEditor_step = new E("#wangEditor_step")
                wangEditor_step.create()
                //图片上传-配置服务端接口
                wangEditor_step.config.uploadImgServer = process.env.VUE_APP_BackEndServer+'/wiki/article/wangeditor/upload/file'
                //图片上传-自定义 timeout 时间
                wangEditor_step.config.uploadImgTimeout = 20 * 1000
                //关闭粘贴样式的过滤
                wangEditor_step.config.pasteFilterStyle = false
            }

            type CJMissionTableDataType = {
                id: string,
                taskName: string;
                taskDesc: string;
                taskContent: string;
                taskImportant: number;
                parentId: string;
            };
            /*
            * ant-design-vue table组件基本配置
            * */
            const columns = [
                {
                    title: '标题',
                    dataIndex: 'taskName',
                    width:"20%",
                    // slots: { title: 'customColumnTitle' }
                    // ellipsis: true,
                },
                {
                    title: '描述',
                    dataIndex: 'taskDesc',
                    width:"30%",
                    ellipsis: true,//超过长度后自动变为省略号。。。
                },
                {
                    title: '重要程度',
                    dataIndex: 'taskImportant',
                    width:"15%",
                    // ellipsis: true,
                    slots: {customRender: 'importantRender'},
                    sortDirections: ['descend', 'ascend'],
                    defaultSortOrder: 'descend',
                    sorter: (a: CJMissionTableDataType, b: CJMissionTableDataType) => {

                        return a.taskImportant - b.taskImportant
                    }
                },
                {
                    title: '完成',
                    key: 'finishStatus',
                    dataIndex: 'finishStatus',
                    width:"15%",
                    // ellipsis: true,
                    slots: {customRender: 'finishRender'},
                    filters: [
                        { text: '进行中', value: '进行中' },
                        { text: '已完成', value: '已完成' },
                    ],
                    // filteredValue: filtered.name || null,
                    // filteredValue: ["进行中","已完成"],
                    onFilter: (value: string, record: any) => record.finishStatus.includes(value),
                    defaultFilteredValue:["进行中"]
                },
                {
                    title: '操作',
                    key: 'action',
                    width:"20%",
                    slots: {customRender: 'action'}
                }
            ];

            const tableRowSelection = {
                type: "radio",//单选
                onSelect: function (record: any, selected: any, selectedRows: any, nativeEvent: any) {
                    console.log("record: ", record)
                    selectedMissionId.value = record.id//step-list同步变化
                    // mission.data.parentId = record.id//用于新增子任务过程中，模态框提交时parentId设置
                    step.data.taskId = record.id
                    // console.log("selectedRows:",selectedRows)
                }
            }

            const cjTableHeaderRow = (column:any , index:any)=>{
                return {
                    class: "cjTableHeaderClass",//此css属性大多被覆盖。。。。
                    on: {//不好用
                        click: () => {
                            console.log("click the table header")
                        },        // 点击表头行
                    }
                }
            }

            const cjTableRowClass = (record:any, index:number)=>{
                if(index%2 == 1){
                    return "cjTableRowClass"
                }
                return null
            }


            //ant-design-vue table组件发生变化时
            const handleChange = (pagination:any, filters: any, sorter: any) => {
                console.log('Various parameters', pagination, filters, sorter);
                // filteredInfo.value = filters;
                // sortedInfo.value = sorter;
            };


            /*
            * mission操作
            * */
            const selectedMissionId = ref<string>("0");
            const missions = reactive({data: []});
            const loading = ref(false);

            /*
            * mission modal基本属性
            * */
            const mission = reactive({
                data:{
                    parentId: "0",//用于添加子任务
                    taskContent:"",
                }
            })

            const missionModalVisible = ref(false);
            const missionModalLoading = ref(false);

            const afterMissionVisibleChange = (visible:boolean)=>{
                // console.log("afterMissionVisibleChange:" ,visible)
                if(visible){
                    createWangEditor()
                    if(mission.data.taskContent !=""){
                        wangEditor_mission.txt.html(mission.data.taskContent);
                    }
                }
            }
            /**
             * 查询mission--查找所有的mission
             **/
            const queryMission = () => {
                loading.value = true;
                axios.post("/wiki/mission/info/table"
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        missions.data = data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };
            /*
            * 新增
            * */
            const addMission = () => {//添加第一级mission
                missionModalVisible.value = true;
                mission.data.parentId="0"
            }
            const addChildMission = (missionId:string) => {//添加 child mission
                missionModalVisible.value = true;
                mission.data.parentId=missionId
            }
            /*
              * 编辑mission
              * */
            const editMission = (record: any) => {
                console.log("editMission-record:", record)
                missionModalVisible.value = true;
                mission.data = Tool.copy(record);//复制数据，不影响源数据
            }
            /*
            * 清空mission modal
            * */
            const clearMissionModal = () => {
                // 销毁wangEditor_mission
                if(wangEditor_mission){
                    wangEditor_mission.destroy()
                }
                //清空mission内容
                mission.data = {
                    taskContent: "",
                    parentId: selectedMissionId.value
                }
            }

            const missionModalCancel = () => {
                missionModalVisible.value = false;
                clearMissionModal()
            }

            const missionModalOk = () => {
                missionModalLoading.value = true;
                mission.data.taskContent = wangEditor_mission.txt.html()
                axios.post("/wiki/mission/info/save", mission.data).then((response) => {
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        missionModalVisible.value = false;
                        clearMissionModal()
                        // 重新加载列表，有可能是添加
                        queryMission();
                    } else {
                        message.error(data.msg);
                    }
                }).finally(() => {
                    missionModalLoading.value = false;
                });
            }

            /**
             * 删除mission
             **/

            const delMission = (id: number) => {
                axios.delete("/wiki/mission/info/delete/" + id).then((response) => {
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        // 重新加载列表
                        queryMission();
                    }
                });
            };




            const step = reactive({
                data:{
                    taskId: "0",//代表:初始情况下未选中任何mission
                    stepContent:""
                }
            })
            const stepModalVisible = ref(false);
            const stepModalLoading = ref(false);
            const afterStepVisibleChange = (visible:boolean)=>{
                // console.log("afterMissionVisibleChange:" ,visible)
                if(visible){
                    createWangEditor_step()
                    if(step.data.stepContent !=""){
                        wangEditor_step.txt.html(step.data.stepContent);
                    }
                }
            }
            /*
           * 新增
           * */
            const addStep = () => {
                if (selectedMissionId.value != "0") {
                    stepModalVisible.value = true;
                }else{
                    message.info("请选择一个目标")
                }
            }
            const editMissionStep = (item:any)=>{
                stepModalVisible.value = true;
                step.data = Tool.copy(item);//复制数据，不影响源数据
                }

            const clearStepModal = () => {
                // 销毁wangEditor_step
                if(wangEditor_step){
                    wangEditor_step.destroy()
                }
                //清空mission内容
                step.data = {stepContent: "", taskId: selectedMissionId.value}
            }

            const stepModalCancel = () => {
                stepModalVisible.value = false;
                clearStepModal()
            }

            const stepModalOk = () => {
                stepModalLoading.value = true;
                step.data.stepContent = wangEditor_step.txt.html()
                axios.post("/wiki/mission/step/save", step.data).then((response) => {
                    const data = response.data; // data = commonResp
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        stepModalVisible.value = false;
                        clearStepModal()
                        // 重新加载列表，有可能是添加
                        queryMissionSteps(selectedMissionId.value);
                    } else {
                        message.error(data.msg);
                    }
                }).finally(() => {
                    stepModalLoading.value = false;
                });
            }


            /*
            * step-list操作,mission与missionStep的关联
            * */

            const steps = reactive({data:[]});

            watch(selectedMissionId, (newVal, oldVal) => {
                queryMissionSteps(newVal);
            })
            /**
             * 查询steps
             **/
            const queryMissionSteps = (missionId: string) => {
                loading.value = true;
                axios.get("/wiki/mission/step/" + missionId
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        steps.data = data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };

            /**
             * 删除mission
             **/

            const delMissionStep = (id: string) => {
                axios.delete("/wiki/mission/step/delete/" + id).then((response) => {
                    const data = response.data
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        // 重新加载列表
                        queryMissionSteps(selectedMissionId.value);
                    }
                });
            };

            return {
                missions: toRef(missions, 'data'),
                columns,
                loading,
                tableRowSelection,
                handleChange,
                cjTableRowClass,
                cjTableHeaderRow,

                //mission的curd
                selectedMissionId,
                afterMissionVisibleChange,
                mission: toRef(mission, 'data'),
                addMission,
                addChildMission,
                queryMission,
                missionModalVisible,
                missionModalLoading,
                missionModalOk,
                missionModalCancel,

                editMission,
                delMission,

                /*
                * mission step操作
                * */
                steps : toRef(steps, 'data'),
                step  : toRef(step, 'data'),
                afterStepVisibleChange,
                stepModalVisible,
                stepModalLoading,
                queryMissionSteps,
                addStep,
                editMissionStep,
                stepModalOk,
                stepModalCancel,
                delMissionStep,


            };
        },
    });
</script>
<style>
    .cjTableHeaderClass{
        /*font-size: large;*/
        font-family: "Times New Roman",Georgia,Serif,serif;
        text-align:center;

    }
    .cjTableRowClass {
        background-color: #F1F3F4;
    }
    #components-layout-demo-fixed .logo {
        width: 120px;
        height: 31px;
        background: rgba(255, 255, 255, 0.2);
        margin: 16px 24px 16px 0;
        float: left;
    }

    .site-layout .site-layout-background {
        background: #fff;
    }

    [data-theme='dark'] .site-layout .site-layout-background {
        background: #141414;
    }
</style>
