<template>
    <a-layout id="components-layout-demo-top-side">
        <a-layout-header class="header">
            <GlobalHeader/>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px">

            <a-layout style="padding: 24px 0; background: #fff">
                <a-layout-sider width="200" style="background: #fff">

                    <a-menu
                            id="dddddd"
                            style="height: 100%"
                            v-model:openKeys="openKeys"
                            v-model:selectedKeys="selectedKeys"
                            mode="inline"
                            @click="handleClick"

                    >
                        <!--
                       第一层：
                        遍历categorys，如果item包含children,如果不包含children
                        -->
                        <template v-for="item in categorys">
                            <template v-if="item.children">
                                <a-sub-menu :key="item.id">
                                    <template v-slot:title>
                                        <span><user-outlined/>{{item.name}}</span>
                                    </template>
                                    <!--
                                     第二层：
                                      遍历categorys，如果item包含children,如果不包含children
                                      -->
                                    <template v-for="item2 in item.children">
                                        <template v-if="item2.children">
                                            <a-sub-menu :key="item2.id">
                                                <template v-slot:title>
                                                    <span><user-outlined/>{{item2.name}}</span>
                                                </template>
                                                <!--
                                                 第三层--start:
                                                  遍历categorys，如果item包含children,如果不包含children
                                                  -->
                                                <!--这种方法报错：
                                                VueCompilerError: <template v-for> key should be placed on the <template> tag.
                                                -->
                                                <!--<template v-for="item3 in item2.children" >
                                                    <a-menu-item  :key="item3.id">
                                                        <MailOutlined /><span>{{item3.name}}</span>
                                                    </a-menu-item>
                                                </template>-->
                                                <a-menu-item v-for="item3 in item2.children" :key="item3.id">
                                                    <MailOutlined/>
                                                    <span>{{item3.name}}</span>
                                                </a-menu-item>
                                                <!--第三层--end-->
                                            </a-sub-menu>

                                        </template>
                                        <template v-else>
                                            <a-menu-item :key="item2.id">
                                                <MailOutlined/>
                                                <span>{{item2.name}}</span>
                                            </a-menu-item>
                                        </template>
                                    </template>
                                    <!--第二层--end-->
                                </a-sub-menu>
                            </template>
                            <template v-else>
                                <a-menu-item :key="item.id">
                                    <MailOutlined/>
                                    <span>{{item.name}}</span>
                                </a-menu-item>
                            </template>

                        </template>
                        <!--第一层--end-->

                    </a-menu>
                </a-layout-sider>
                <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
                    Content
                </a-layout-content>
            </a-layout>
        </a-layout-content>
    </a-layout>
</template>
<script lang="ts">
    import GlobalHeader from "@/components/GlobalHeader.vue";
    import {defineComponent, onMounted, reactive, ref, toRef, watch} from 'vue';
    import axios from "axios";
    import {message} from "ant-design-vue";

    export default defineComponent({
        components: {
            GlobalHeader,
        },
        setup() {

            const selectedKeys = ref<string[]>([]);
            const openKeys = ref<string[]>([]);


            onMounted(() => {
                // console.log("CategoryTable mounted......")
                handleQuery();
            });

            const categorys = reactive({data: []});
            const loading = ref(false);
            /**
             * 查询数据
             **/
            const handleQuery = () => {
                loading.value = true;
                axios.post("/wiki/category/table"
                ).then((response) => {
                    loading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        categorys.data = data.data;
                    } else {
                        message.error(data.msg);
                    }
                });
            };


            const handleClick = (e: Event) => {
                console.log('click', e);
                console.log("selectedKeys:", selectedKeys)
                console.log("openKeys:", openKeys)
            };
            const titleClick = (e: Event) => {
                console.log('titleClick', e);
            };
            watch(
                () => openKeys,
                val => {
                    console.log('openKeys', val);
                },
            );
            return {
                categorys: toRef(categorys, 'data'),
                selectedKeys,
                openKeys,

                handleClick,
                titleClick,
            };
        },

    });
</script>

