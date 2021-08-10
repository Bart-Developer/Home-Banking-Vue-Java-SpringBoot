import { createRouter, createWebHistory } from 'vue-router'
import Menu from '../views/Menu.vue'
import Login from '../views/Login.vue'
import App from '../App.vue'

const routes = [
  
  {
    path:'/',
    name: app,
    component: App
  },
  {
    path: '/menu',
    name: 'menu',
    component: Menu,

  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
