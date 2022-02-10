<template>
    <a-list :data-source="listData.books" :grid="{ gutter: 16, xs: 1, sm: 2, md: 3, lg: 3, xl: 3, xxl: 3 }" item-layout="vertical"
            size="large">
        <!--
            用于a-list的 页脚
        -->
        <!--        <template #footer>-->
        <!--            <div>-->
        <!--                <b>ant design vue</b>-->
        <!--                footer part-->
        <!--            </div>-->
        <!--        </template>-->
        <template #renderItem="{ item }">
            <a-list-item key="item.title">
                <template #actions>
                  <span :key="type" v-for="{ type, text } in actions">
                    <component style="margin-right: 8px" v-bind:is="type"/>
                    {{ text }}
                  </span>
                </template>

                <a-list-item-meta :description="item.description">
                    <template #title>
                        <a :href="item.href">{{ item.title }}</a>
                    </template>
                    <template #avatar>
                        <a-avatar :src="item.avatar"/>
                    </template>
                </a-list-item-meta>

            </a-list-item>
        </template>
    </a-list>
</template>
<script lang="ts">
    import {StarOutlined, LikeOutlined, MessageOutlined} from '@ant-design/icons-vue';
    import {onMounted, reactive} from "vue";
    import axios from "axios";
    // import { defineComponent } from 'vue';

    var listData: any = reactive({books: []})
    // const ebooks1 = reactive({books: []});
    //
    // for (let i = 0; i < 6; i++) {
    //     listData.push({
    //         href: 'https://www.antdv.com/',
    //         title: `ant design vue part ${i}`,
    //         avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
    //         description:
    //             'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    //         content:
    //             'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
    //     });
    // }

    export default ({
        name: "EbookList",
        components: {
            StarOutlined,
            LikeOutlined,
            MessageOutlined,
        },
        setup() {
            /*
            * pagination 用于a-list的分页
            * */
            // const pagination = {
            //     onChange: (page: number) => {
            //         console.log(page);
            //     },
            //     pageSize: 3,
            // };
            onMounted(() => {
                axios({
                    method: 'get',
                    url: 'http://localhost:8083/wiki/ebook/list'
                }).then(function (response) {
                    listData.books = response.data.data;
                    console.log(listData);
                })

            })

            const actions: Record<string, string>[] = [
                {type: 'StarOutlined', text: '156'},
                {type: 'LikeOutlined', text: '156'},
                {type: 'MessageOutlined', text: '2'},
            ];
            return {
                listData,
                // pagination,
                actions,
            };
        },
    });
</script>

<style scoped>
    .ant-avatar {
        width: 50px;
        height: 50px;
        line-height: 50px;
        border-radius: 8%;
        margin: 5px 0;
    }
</style>


