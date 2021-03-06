import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '@/views/Home.vue'
import ArticleAdmin from '@/views/admin/ArticleAdmin.vue'

import BackStageManage from '@/views/BackStageManage.vue'
import AuthorityManage from '@/views/backStage/AuthorityManage.vue'
import CJUserManage from '@/views/backStage/AuthorityManage/CJUserManage.vue'
import CJRoleManage from '@/views/backStage/AuthorityManage/CJRoleManage.vue'
import CJResourceManage from '@/views/backStage/AuthorityManage/CJResourceManage.vue'

import CJMissionManage from '@/views/admin/CJMissionManage.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/admin/article',
    name: 'ArticleAdmin',
    component: ArticleAdmin
  },
  {
    path: '/admin/mission',
    name: 'CJMissionManage',
    component: CJMissionManage
  },
  {
    path: '/back',
    name: 'BackStageManage',
    component: BackStageManage,
    children: [
      {
        path: 'user',
        name: 'CJUserManage',
        component: CJUserManage
      },
      {
        path: 'role',
        name: 'CJRoleManage',
        component: CJRoleManage
      },
      {
        path: 'resource',
        name: 'CJResourceManage',
        component: CJResourceManage
      },


      // ...其他子路由
    ],
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
