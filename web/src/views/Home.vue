<template>
    <a-layout id="components-layout-demo-top-side">
        <a-layout-header class="header">
            <GlobalHeader/>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px; margin: 50px">

            <a-carousel autoplay>
                <div><h1>No Execuse!!</h1></div>
                <div><h2>Execuse sounds best to the person who's making them up.</h2></div>
                <div><h2>Stop feeling sorry for youself.</h2></div>
                <div><h2>Wake your ass up. It's game on.</h2></div>
            </a-carousel>

            <div style="background: #ececec; padding-top: 30px; width: 100%">
                <a-card title="Card title" :bordered="false" >
                    <a-list item-layout="horizontal" :data-source="missions">
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
                </a-card>
            </div>
        </a-layout-content>
    </a-layout>


</template>
<script>
    import {useSound} from '@vueuse/sound'
    import buttonSfx from '@/assets/excuse.mp3'
    import GlobalHeader from "@/components/GlobalHeader.vue";
    import {defineComponent, onMounted, reactive, ref, toRef, watch} from 'vue';
    import {Tool} from "@/util/tool";
    import axios from "axios";
    import {message} from "ant-design-vue";


    export default defineComponent({
        components: {
            GlobalHeader,
        },



        setup() {

            // interface CJMissionTableDataType = {
            //     id: string,
            //     taskName: string;
            //     taskDesc: string;
            //     taskContent: string;
            //     taskImportant: number;
            //     parentId: string;
            // };

            const missions = reactive({data: []});
            /**
             * 查询mission--查找今天的mission
             **/
            const queryMission = () => {
                axios.post("/wiki/mission/info/needToResolve"
                ).then((response) => {
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        missions.data = data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };

            return {
                missions: toRef(missions, 'data'),

            }
        },
    });
</script>

<style scoped>
    /* For demo */
    .ant-carousel >>> .slick-slide {
        text-align: center;
        height: 200px;
        line-height: 200px;
        background: #364d79;
        overflow: hidden;
    }

    .ant-carousel >>> .slick-slide h2 {
        color: #fff;
    }

    .ant-carousel >>> .slick-slide h1 {
        color: #fff;
    }
</style>