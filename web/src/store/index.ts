import {createStore} from 'vuex'

declare let CJSessionStorage: any;
const CJUSER = "CJUSER";

export const cjStore = createStore({
    state: {
        user: CJSessionStorage.get(CJUSER) || {}
    },
    mutations: {
        setUser(state, user) {
            console.log("store set user：", user);
            state.user = user;
            CJSessionStorage.set(CJUSER, user);
        },
        delUser(state) {
            console.log("store del user：");
            state.user = {};
            CJSessionStorage.remove(CJUSER);
        }
    },
    actions: {},
    modules: {}
})

export default cjStore;

