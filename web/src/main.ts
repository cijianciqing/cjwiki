import { createApp } from 'vue'
import App from './App.vue'
//引入Vue Router
import router from './router'
//引入Vuex
import cjStore from './store'
//引用ant design vue
import Antd, {message} from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
//ant design vue的图标组件
import * as Icons from '@ant-design/icons-vue'

import axios from 'axios';
//axios配置baseURL
axios.defaults.baseURL = process.env.VUE_APP_BackEndServer;
//表示跨域请求时是否需要使用凭证
axios.defaults.withCredentials = true;
//axios的全局设置不会随着store中数据的变化而变化，需要在后续登录和退出是进行设置
axios.defaults.headers.common['token'] = cjStore.state.user.token == undefined ? "" : cjStore.state.user.token

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {

    console.log(config.url, '请求参数：', config);
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log(response.config.url, '返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    console.log(error.response.data);
    console.log(error.response.status);
    console.log(error.response.headers);
    if(error.response.status==403){
        message.error("CJ权限不足: "+error.response.data);
    }

    return Promise.reject(error);
});

// import {CJAxiosConfig } from "./CJGlobalConfig/axios.js"

const app = createApp(App);
app.use(cjStore)
    .use(router)
    .use(Antd)
    .mount('#app')

// 全局使用图标
const icons: any = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}

//显示当前的vue cli 配置环境
console.log('环境：', process.env.NODE_ENV);
console.log('后端服务端：', process.env.VUE_APP_BackEndServer);
console.log(app);