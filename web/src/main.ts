import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引用ant design vue
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
//ant design vue的图标组件
import * as Icons from '@ant-design/icons-vue';

//interface App<HostElement = any>
const app = createApp(App)
app.use(store).use(router).use(Antd).mount('#app')

// 全局使用图标
const icons: any = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}
