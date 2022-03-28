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
                >
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

                            <a-list-item-meta :description="item.description">
                                <template #title>
                                    <a href="https://www.antdv.com/">{{ item.title }}</a>
                                </template>
                                <template #avatar>
                                    <a-avatar style="background-color: #1890ff">
                                        <template #icon>
                                            <UserOutlined/>
                                        </template>
                                    </a-avatar>
                                    <!--                                        <a-avatar :src="item.avatar" />-->
                                </template>
                            </a-list-item-meta>
                            <template #actions>
                                <a-tag color="cyan">{{item.updateTime}}</a-tag>

                                <a-popconfirm
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

    <a-modal
            title="Mission Form"
            v-model:visible="missionModalVisible"
            :confirm-loading="missionModalLoading"
            @ok="missionModalOk"
            @cancel="missionModalCancel"

    >
        <a-form :model="mission" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">

            <a-form-item label="名称">
                <a-input v-model:value="mission.taskName"/>
            </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="mission.taskDesc" type="textarea"/>
            </a-form-item>

            <a-form-item label="重要程度">

                <a-input-number id="inputNumber" v-model:value="mission.taskImportant" :min="0" :max="10"/>
                <!--                <a-radio-group v-model:value="mission.taskImportant" button-style="solid">-->
                <!--                    <a-radio-button value="veryImportant">非常重要</a-radio-button>-->
                <!--                    <a-radio-button value="important">重要</a-radio-button>-->
                <!--                    <a-radio-button value="normal">普通</a-radio-button>-->
                <!--                    <a-radio-button value="noMatter">随便</a-radio-button>-->
                <!--                </a-radio-group>-->
            </a-form-item>

            <a-form-item label="重要程度">
                <a-radio-group v-model:value="mission.finishStatus" button-style="solid">
                    <a-radio-button value="进行中">进行中</a-radio-button>
                    <a-radio-button value="已完成">已完成</a-radio-button>
                </a-radio-group>
            </a-form-item>

            <a-form-item label="内容">
                <a-textarea v-model:value="mission.taskContent" auto-size/>
            </a-form-item>
        </a-form>

    </a-modal>

    <a-modal
            title="MissionStep Form"
            v-model:visible="stepModalVisible"
            :confirm-loading="stepModalLoading"
            @ok="stepModalOk"
            @cancel="stepModalCancel"

    >
        <a-form :model="step" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">

            <a-form-item label="标题">
                <a-input v-model:value="step.title"/>
            </a-form-item>
            <a-form-item label="描述">
                <a-input v-model:value="step.description" type="textarea"/>
            </a-form-item>

        </a-form>

    </a-modal>

</template>
<script lang="ts">
    import {defineComponent, onMounted, reactive, ref, toRef, watch, computed} from 'vue';
    import GlobalHeader from "@/components/GlobalHeader.vue";
    // import CJMission from "@/components/mission/CJMissionInfo.vue";
    import axios from "axios";
    import {message} from "ant-design-vue";
    import {Tool} from "@/util/tool";


    export default defineComponent({
        components: {
            GlobalHeader,
            // CJMission,
        },
        setup() {


            type CJMissionTableDataType = {
                id: string,
                taskName: string;
                taskDesc: string;
                taskContent: string;
                taskImportant: number;
                parentId: string;
            };


            // const filteredInfo = ref();
            // const filtered = filteredInfo.value || {};
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
                    slots: {customRender: 'action'}
                }
            ];
            const selectedMissionId = ref<string>("0");
            const missions = reactive({data: []});
            const loading = ref(false);
            const tableRowSelection = {
                type: "radio",
                onSelect: function (record: any, selected: any, selectedRows: any, nativeEvent: any) {
                    console.log("record: ", record)
                    selectedMissionId.value = record.id//step-list同步变化
                    mission.value.parentId = record.id//用于模态框提交过程中parentId设置
                    step.value.taskId = record.id
                    // console.log("selectedRows:",selectedRows)
                }
            };
            const handleChange = (pagination:any, filters: any, sorter: any) => {
                console.log('Various parameters', pagination, filters, sorter);
                // filteredInfo.value = filters;
                // sortedInfo.value = sorter;
            };
            onMounted(() => {
                // console.log("CategoryTable mounted......")
                queryMission();
                // queryMissionSteps(selectedMissionId.value);
            });

            const mission = ref({
                parentId: "0"
            })
            const missionModalVisible = ref(false);
            const missionModalLoading = ref(false);
            /*
            * 新增
            * */
            const addMission = () => {
                missionModalVisible.value = true;
            }
            const clearMissionModal = () => {
                mission.value = {parentId: selectedMissionId.value}
            }

            const missionModalCancel = () => {
                clearMissionModal()
            }

            const missionModalOk = () => {
                missionModalLoading.value = true;

                axios.post("/wiki/mission/info/save", mission.value).then((response) => {
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
             * 查询数据
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
            * 编辑mission
            * */
            const editMission = (record: any) => {
                missionModalVisible.value = true;
                mission.value = Tool.copy(record);//复制数据，不影响源数据
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


            const step = ref({
                taskId: "0"
            })
            const stepModalVisible = ref(false);
            const stepModalLoading = ref(false);
            /*
           * 新增
           * */
            const addStep = () => {
                if (step.value.taskId != "0") {
                    stepModalVisible.value = true;
                }
            }
            const clearStepModal = () => {
                step.value = {taskId: selectedMissionId.value}
            }

            const stepModalCancel = () => {
                clearStepModal()
            }

            const stepModalOk = () => {
                stepModalLoading.value = true;

                axios.post("/wiki/mission/step/save", step.value).then((response) => {
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
            * step-list操作
            * */

            const steps = ref([]);

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
                        steps.value = data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };
            /*
              * 编辑step
              * */
            // const editStep = (record:any)=>{
            //     stepModalVisible.value=true;
            //     step.value = Tool.copy(record);//复制数据，不影响源数据
            // }
            /**
             * 删除mission
             **/

            const delMissionStep = (id: string) => {
                axios.delete("/wiki/mission/step/delete/" + id).then((response) => {
                    const data = response.data; // data = commonResp
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


                //mission的curd
                selectedMissionId,
                mission,
                addMission,
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
                steps,
                step,
                stepModalVisible,
                stepModalLoading,
                queryMissionSteps,
                addStep,
                stepModalOk,
                stepModalCancel,
                delMissionStep,


            };
        },
    });
</script>
<style>
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
