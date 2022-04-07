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
                        <a-menu-item>
                            <a-button  type="primary"  @click="showLyrics">
                                <template #icon><SearchOutlined /></template>
                                显示歌词
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

    <a-modal
            title="The Lyrics"
            v-model:visible="modalVisible3"
    >

        <div class="11122233223">
            <p>what's stopping you?</p>
            <p></p>
            <p>Are you too tired?</p>
            <p></p>
            <p>Didn't get enough sleep?</p>
            <p></p>
            <p>Don't have enough energy?</p>
            <p></p>
            <p>Don't have enough time?</p>
            <p></p>
            <p>Is that what's stopping you right now?</p>
            <p></p>
            <p>Don't have enough money?</p>
            <p></p>
            <p>Is that the thing?</p>
            <p></p>
            <p>Or is the thing that's stopping you</p>
            <p></p>
            <p>you?</p>
            <p></p>
            <p>Excuses sound best</p>
            <p></p>
            <p>to the person who's making them up.</p>
            <p></p>
            <p>Stop feeling sorry for youself.</p>
            <p></p>
            <p>Get off the pity potty</p>
            <p></p>
            <p>telling everybody you're sad</p>
            <p></p>
            <p>and sob stories,trying to get people</p>
            <p></p>
            <p>to show up to your pity parties</p>
            <p></p>
            <p>and your pity parades.</p>
            <p></p>
            <p>If you ever see me in a rolls royce</p>
            <p></p>
            <p>a 6 or 7 star hotel</p>
            <p></p>
            <p>living my life to the fullest</p>
            <p></p>
            <p>don't get jealous of me.</p>
            <p></p>
            <p>because i worked my ass off to get it.</p>
            <p></p>
            <p>Nobody handed me nothing.</p>
            <p></p>
            <p>Wake your ass up.</p>
            <p></p>
            <p>Awaken the beast inside.</p>
            <p></p>
            <p>It's game on.</p>
            <p></p>
            <p>It's go season.</p>
            <p></p>
            <p>It's time for you to take advantage of the access</p>
            <p></p>
            <p>and the resources that you have</p>
            <p></p>
            <p>in your country and your community.</p>
            <p></p>
            <p>You got a problem with your life?</p>
            <p></p>
            <p>You got a problem with your enviroment?</p>
            <p></p>
            <p>Do something about it.</p>
            <p></p>
            <p>If you want it</p>
            <p></p>
            <p>go get it.</p>
            <p></p>
            <p>Recognise.</p>
            <p></p>
            <p>the excuses are not valid.</p>
            <p></p>
            <p>They're conjured up</p>
            <p></p>
            <p>they're fabricated</p>
            <p></p>
            <p>they're lies.</p>
            <p></p>
            <p>And how do you stop the lies?</p>
            <p></p>
            <p>You stop the lies with the truth.</p>
            <p></p>
            <p>And the truth is</p>
            <p></p>
            <p>you have time</p>
            <p></p>
            <p>you hace the skill</p>
            <p></p>
            <p>you have the knowledge</p>
            <p></p>
            <p>and the support</p>
            <p></p>
            <p>and the willpower</p>
            <p></p>
            <p>and the discipline</p>
            <p></p>
            <p>to get it done.</p>
            <p></p>
            <p>The fruit of everthing good in life</p>
            <p></p>
            <p>begins with a challenge.</p>
            <p></p>
            <p>Everything is uphill that's worthwhile.</p>
            <p></p>
            <p>And it's not going to come to you</p>
            <p></p>
            <p>and it's not going to fall in your lap</p>
            <p></p>
            <p>and it's not going to be something that,oh my gosh</p>
            <p></p>
            <p>it just was so simple</p>
            <p></p>
            <p>it's always going to be difficult.</p>
            <p></p>
            <p>If you want it, you gatta go get it.</p>
            <p></p>
            <p>This is your chance.</p>
            <p></p>
            <p>This is your shot.</p>
            <p></p>
            <p>This is your moment.</p>
            <p></p>
            <p>This is your time.</p>
            <p></p>
            <p>This is your place.</p>
            <p></p>
            <p>This is your opportunity.</p>
            <p></p>
            <p>This is my time</p>
            <p></p>
            <p>This is my moment</p>
            <p></p>
            <p>tomorrow,tomorrow,tomorrow</p>
            <p></p>
            <p>ain't no such thing as tomorrow</p>
            <p></p>
            <p>we only got today.</p>
            <p></p>
            <p>It's your dream.</p>
            <p></p>
            <p>if you're going to have it</p>
            <p></p>
            <p>get your butt up and make it happen</p>
            <p></p>
            <p>if you're going to have it</p>
            <p></p>
            <p>rise and grind.</p>
            <p></p>
            <p>You still got work to do</p>
            <p></p>
            <p>stay on that baskerball court</p>
            <p></p>
            <p>stay on that football field</p>
            <p></p>
            <p>it's grind season homie.</p>

        </div>
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

            // 登录
            const login = () => {
                console.log("开始登录");
                loginModalLoading.value = true;
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
            }

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
            }

            //音频播放
            const {play, isPlaying, stop, pause, sound} = useSound(buttonSfx, {
                interrupt: true,//不重复播放
                soundEnabled: true,//允许有声音？？？？
            })

            const modalVisible3 = ref(false);
            const showLyrics = () => {
                modalVisible3.value = true;

            }

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
                modalVisible3,
                showLyrics,
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