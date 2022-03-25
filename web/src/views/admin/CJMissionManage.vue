<template>
    <a-layout>
        <a-layout-header style=" position: fixed; z-index: 1; width: 100%;">

            <GlobalHeader/>
            <!--            <div style=" padding: 10px; background-color: lightskyblue">-->

            <!--            </div>-->
        </a-layout-header>
        <a-row style="margin-top: 64px">
            <a-col :span="14" style=" padding: 10px;">
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
            <a-col :span="8" style=" padding: 10px;">
            </a-col>
            <a-col :span="2" style=" padding: 10px;">
                <a-button type="primary" shape="circle">
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
            <a-col style="background-color: lightskyblue;" :span="14">
                <a-table
                        :columns="columns"
                        :row-key="record => record.id"
                        :data-source="missions"
                        :loading="loading"
                        :pagination="false"
                        :row-selection="tableRowSelection"
                >

                    <template v-slot:action="{ text, record }">
                        <a-space size="small">
                            <a-tooltip>
                                <template #title>显示Mission详细情况</template>
                                <a-button type="primary" shape="circle" @click="showMission">
                                    <template #icon>
                                        <ReadFilled/>
                                    </template>
                                </a-button>
                            </a-tooltip>

                            <a-tooltip>
                                <template #title>编辑Mission</template>
                                <a-button type="primary" shape="circle" @click="editMission(record)">
                                    <template #icon>
                                        <EditOutlined />
                                    </template>
                                </a-button>
                            </a-tooltip>
                            <a-popconfirm
                                    title="删除后不可恢复，确认删除?"
                                    ok-text="是"
                                    cancel-text="否"
                                    @confirm="delMission(record.id)"
                            >
                                <a-button type="primary" danger shape="circle">
                                    <template #icon>
                                        <CloseCircleOutlined />
                                    </template>
                                </a-button>
                            </a-popconfirm>
                        </a-space>
                    </template>
                </a-table>
            </a-col>
            <a-col :span="10">
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


                <a-radio-group v-model:value="mission.taskImportant" button-style="solid">
                    <a-radio-button value="veryImportant">非常重要</a-radio-button>
                    <a-radio-button value="important">重要</a-radio-button>
                    <a-radio-button value="normal">普通</a-radio-button>
                    <a-radio-button value="noMatter">随便</a-radio-button>
                </a-radio-group>
            </a-form-item>
            <a-form-item label="内容">
                <a-input v-model:value="mission.taskContent" type="textarea"/>
            </a-form-item>
        </a-form>

    </a-modal>
</template>
<script lang="ts">
    import {defineComponent, onMounted, reactive, ref, toRef, watch} from 'vue';
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
                    slots: {customRender: 'action'}
                }
            ];
            const selectedMissionId = ref<string>("0");
            const missions = reactive({data: []});
            const loading = ref(false);
            const tableRowSelection = {
                type: "radio",
                onSelect: function (record: any, selected: any, selectedRows: any, nativeEvent: any) {
                    selectedMissionId.value = record.id
                }
            };
            onMounted(() => {
                // console.log("CategoryTable mounted......")
                queryMission();
                // queryMissionSteps(selectedMissionId.value);
            });
            const mission = ref({parentId:selectedMissionId.value})
            const missionModalVisible = ref(false);
            const missionModalLoading = ref(false);
            /*
            * 新增
            * */
            const addMission = ()=>{
                missionModalVisible.value=true;
            }
            const clearMissionModal = ()=>{
                mission.value={
                    parentId: selectedMissionId.value
                }
                }
            const missionModalCancel = ()=>{
                clearMissionModal()
                }
            const missionModalOk= ()=>{
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
            const editMission = (record:any)=>{
                missionModalVisible.value=true;
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


            /*
            * step-list操作
            * */

            const steps = ref([]);
            // watch(() => param.categoryId, (newVal, oldVal) => {
            //     console.log(newVal)
            //     handleQuery(param);
            // })
            watch(selectedMissionId, (newVal, oldVal) => {
                queryMissionSteps(selectedMissionId.value);
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


            return {
                missions: toRef(missions, 'data'),
                columns,
                loading,
                tableRowSelection,
                queryMission,

                //mission的curd
                selectedMissionId,
                mission,
                addMission,
                missionModalVisible,
                missionModalLoading,
                missionModalOk,
                missionModalCancel,

                editMission,

                delMission,

                steps,

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
