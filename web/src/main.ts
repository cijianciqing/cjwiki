import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引用ant design vue
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
//ant design vue的图标组件
import * as Icons from '@ant-design/icons-vue'

import axios from 'axios';
//axios配置baseURL
axios.defaults.baseURL = process.env.VUE_APP_BackEndServer;

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
    return Promise.reject(error);
});

// import {CJAxiosConfig } from "./CJGlobalConfig/axios.js"

const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app')

// 全局使用图标
const icons: any = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}

//显示当前的vue cli 配置环境
console.log('环境：', process.env.NODE_ENV);
console.log('后端服务端：', process.env.VUE_APP_BackEndServer);