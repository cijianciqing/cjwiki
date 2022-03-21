import {createStore} from 'vuex'

declare let CJSessionStorage: any;
const CJUSER = "CJUSER";

export const cjStore = createStore({
    state: {
        user: CJSessionStorage.get(CJUSER) || {},
        // articleTable:[],
        categoryId:"1"
    },
    mutations: {
        /*
        * 登录、登出设置
        * */
        setUser(state, user) {
            console.log("store set user：", user);
            state.user = user;
            CJSessionStorage.set(CJUSER, user);
        },
        delUser(state) {
            console.log("store del user：");
            state.user = {};
            CJSessionStorage.remove(CJUSER);
        },

        /*
       * article与category之间的交互
       * */
        setCategoryId(state, CategoryId) {
            console.log("store  setCategoryId：", CategoryId);
            state.categoryId = CategoryId;
        },

    },
    actions: {},
    modules: {}
})

export default cjStore;

