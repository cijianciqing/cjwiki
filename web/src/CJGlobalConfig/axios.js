
//导入axios 并进行配置
import axios from 'axios';

export default {
    init() {
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

    }

}