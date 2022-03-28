<template>

    <div class="logo"/>
    <a-menu v-model:selectedKeys="currentRoute" theme="dark" mode="horizontal" :style="{ lineHeight: '64px',display: 'block' }"><!---->

        <a-menu-item key="/">
            <router-link to="/">首页</router-link>
        </a-menu-item>
        <a-menu-item key="/admin/mission" >
            <router-link to="/admin/mission">目标管理</router-link>
        </a-menu-item>
        <a-menu-item key="/admin/article">
            <router-link to="/admin/article">知识管理</router-link>
        </a-menu-item>
        <a-menu-item>
            <a-dropdown>
                <a class="ant-dropdown-link" @click.prevent>
                    Music
                    <DownOutlined/>
                </a>
                <template #overlay>
                    <a-menu>
                        <a-menu-item>
                            <a-button type="primary" @click="play">
                                <template #icon><SearchOutlined /></template>
                                播放
                            </a-button>
                        </a-menu-item>
                        <a-menu-item>
                            <a-button  type="primary" danger @click="stop">
                                <template #icon><SearchOutlined /></template>
                                停止
                            </a-button>
                        </a-menu-item>

                    </a-menu>
                </template>
            </a-dropdown>
        </a-menu-item>
        <!-- <a-sub-menu>
             <template #icon>
                 <setting-outlined/>
             </template>
             <template #title>Navigation Three - Submenu</template>
             <a-menu-item key="setting:1">Option 1</a-menu-item>
             <a-menu-item key="setting:2">Option 2</a-menu-item>
         </a-sub-menu>-->
        <a-menu-item key="/back">
            <router-link to="/back">后台管理</router-link>
        </a-menu-item>
        <a-menu-item key="/about">
            <router-link to="/about">关于我们</router-link>
        </a-menu-item>

        <a-popconfirm
                title="确认退出登录?"
                ok-text="是"
                cancel-text="否"
                @confirm="logout()"
        >
            <a class="login-menu" v-show="user.id">
                <span>退出登录</span>
            </a>
        </a-popconfirm>
        <a class="login-menu" v-show="user.id" v-text="'您好：'+user.username">
        </a>
        <!--    如果使用如下格式，登录后不自动显示用户昵称            <span>您好：{{user.username}}</span>-->
        <a class="login-menu" v-show="!user.id" @click="showLoginModal">
            <span>登录</span>
        </a>

    </a-menu>


    <a-modal
            title="登录"
            v-model:visible="loginModalVisible"
            :confirm-loading="loginModalLoading"
            @ok="login"
    >
        <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
            <a-form-item label="登录名">
                <a-input v-model:value="loginUser.user"/>
            </a-form-item>
            <a-form-item label="密码">
                <a-input v-model:value="loginUser.pwd" type="password"/>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import {defineComponent, ref, computed, reactive, onMounted} from 'vue';
    import axios from 'axios';
    import {message} from 'ant-design-vue';
    import {useStore} from 'vuex'
    import qs from 'qs'
    import cjStore from "@/store";
    import {useSound} from '@vueuse/sound'
    import buttonSfx from '@/assets/excuse.mp3'
    import {useRouter} from 'vue-router'

    export default {
        name: "GlobalHeader",
        setup() {

            const router = useRouter();

            //选中的菜单
            const currentRoute = ref([]);
            onMounted(()=>{
                console.log("当前route:",router.currentRoute.value)
                currentRoute.value = [router.currentRoute.value.path]
            })

            // onMounted(() => {
            //     // console.log("CategoryTable mounted......")
            //     handleQuery();
            // });


            const store = useStore()

            // 用来登录
            const loginUser = reactive({
                user: "",
                pwd: ""
            });

            const loginModalVisible = ref(false);
            const loginModalLoading = ref(false);
            const showLoginModal = () => {
                loginModalVisible.value = true;
            };

            // 直接显示当前user,登录后更新user
            const user = computed(() => {
                // console.log("computed change",user)
                return store.state.user
            })
            // const user = watchEffect(() => {
            //     store.state.user
            //
            // });

            // 登录
            const login = () => {
                console.log("开始登录");
                loginModalLoading.value = true;
                // loginUser.value.password = hexMd5(loginUser.value.password + KEY);
                //测试
                // store.commit("setUser",
                //     {
                //         "id": 111,
                //         "name":"u11"
                //     });
                axios.post('/cjauth', qs.stringify(loginUser)).then((response) => {
                    loginModalLoading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        loginModalVisible.value = false;
                        message.success("登录成功！");
                        axios.defaults.headers.common['token'] = data.data.token
                        store.commit("setUser", data.data);
                    } else {

                        message.error(data.msg);
                    }
                }).finally(() => {
                    loginModalLoading.value = false;
                })
                //当返回的Http状态为500时执行以下命令
                //     .catch(function (error) {
                //     console.log("return error000");
                //     console.log(error);
                // })
                ;
            };

            // 登录
            const logout = () => {
                console.log("CJ开始退出登录");
                loginModalLoading.value = true;
                axios.post('/logout').then((response) => {
                    loginModalLoading.value = false;
                    const data = response.data;
                    if (data.code == process.env.VUE_APP_ResponseSuccess) {
                        loginModalVisible.value = false;
                        message.success("CJ退出成功！");
                        axios.defaults.headers.common['token'] = ""
                        store.commit("delUser", data.data);
                    } else {
                        message.error(data.msg);
                    }
                })
                //当返回的Http状态为500时执行以下命令
                //     .catch(function (error) {
                //     console.log("return error000");
                //     console.log(error);
                // })
                ;
            };

            const {play, isPlaying, stop, pause, sound} = useSound(buttonSfx, {
                interrupt: true,//不重复播放
                soundEnabled: true,//允许有声音？？？？
            })


            return {
                currentRoute,

                loginModalVisible,
                loginModalLoading,
                showLoginModal,
                loginUser,
                login,
                logout,
                user,

                play,
                stop,
            }

        }
    };
</script>

<style>
    .login-menu {
        float: right;
        color: white;
        padding-left: 10px;
        margin-right: 10px;
    }
</style>