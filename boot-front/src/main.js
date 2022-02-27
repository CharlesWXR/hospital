import { createApp } from 'vue'

import ElementPlus, { ElLoading } from 'element-plus'
import 'element-plus/dist/index.css'

import AntDesign, { message } from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'

import App from './App.vue'
import router from './router'
import store from './store'

import axios from 'axios'

import VueQrcode from '@chenfengyuan/vue-qrcode';

const app = createApp(App);

app.use(ElementPlus);
app.use(AntDesign);

app.component(VueQrcode.name, VueQrcode);

axios.defaults.baseURL = 'http://localhost:8088/api';

let loadingInstance = null;
axios.interceptors.request.use(config => {
    // show loading in full screen mode
    if (!loadingInstance) {
        loadingInstance = ElLoading.service({
            lock: true,
            text: '加载中',
            background: 'rgba(0, 0, 0, 0.7)'
        });
    }

    setTimeout(() => {
        if (loadingInstance) {
            loadingInstance.close();
            message.error("Link Timeout!");
        }
    }, 5000);

    try {
        if (localStorage.Token) {
            config.headers.Token = localStorage.Token;
        }
        return config;
    } catch (e) {
        console.error(e);
        return config;
    }
})

axios.interceptors.response.use(response => {
    // hide loading
    if (loadingInstance) {
        loadingInstance.close();
        loadingInstance = null;
    }
    return response;
});

app.config.globalProperties.$http = axios;

app.use(store).use(router).mount('#app');
