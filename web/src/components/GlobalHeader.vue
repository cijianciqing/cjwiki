<template>
    <a-layout-header class="header">
        <div class="logo"/>
        <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' ,display: 'block'}">
<!--            <a-menu-item key="1">nav1 11</a-menu-item>-->
<!--            <a-menu-item key="2">nav 2</a-menu-item>-->
<!--            <a-menu-item key="3">nav 3</a-menu-item>-->
            <a-menu-item key="/">
                <router-link to="/">首页</router-link>
            </a-menu-item>
            <a-menu-item key="/admin/ebook">
                <router-link to="/admin/ebook">电子书管理</router-link>
            </a-menu-item>
            <a-menu-item key="/admin/category">
                <router-link to="/admin/category">分类管理</router-link>
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
            <a class="login-menu" v-show="user.id">
                <span>您好：{{user.username}}</span>
            </a>
            <a class="login-menu" v-show="!user.id" @click="showLoginModal">
                <span>登录</span>
            </a>

        </a-menu>

    </a-layout-header>

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
    import { defineComponent, ref, computed, reactive } from 'vue';
    import axios from 'axios';
    import { message } from 'ant-design-vue';
    import { useStore } from 'vuex'
    import qs from 'qs'

    export default {
        name: "GlobalHeader",
        setup() {

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
            const user = computed(() =>{
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
                    if (data.code == process.env.VUE_APP_ResponseSuccess){
                        loginModalVisible.value = false;
                        message.success("登录成功！");

                        store.commit("setUser", data.data);
                    } else {

                        message.error(data.msg);
                    }
                }).finally(()=>{
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
                    if (data.code == process.env.VUE_APP_ResponseSuccess){
                        loginModalVisible.value = false;
                        message.success("CJ退出成功！");

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
            return {
                loginModalVisible,
                loginModalLoading,
                showLoginModal,
                loginUser,
                login,
                logout,
                user,

            }

        }
    };
</script>

<style>
    .login-menu {
        float: right;
        color: white;
        padding-left: 10px;
    }
</style>