import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from "@/views/surface/Home.vue"
import Categories from "@/views/surface/Categories.vue"
import Shops from "@/views/surface/Shops.vue"
import About from "@/views/surface/About.vue"
import Contact from "@/views/surface/Contact.vue"
import Login from "@/views/account/Login.vue"

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home Page',
    component: Home,
    meta: { layout: "surface" },
  },
  {
    path: '/categories',
    name: 'Product Categories',
    component: Categories,
    meta: { layout: "surface" },
  },
  {
    path: '/shops',
    name: 'Shops',
    component: Shops,
    meta: { layout: "surface" },
  },
  {
    path: '/about',
    name: 'About Us',
    component: About,
    meta: { layout: "surface" },
  },
  {
    path: '/contact',
    name: 'Contact Us',
    component: Contact,
    meta: { layout: "surface" },
  },
  {
    path: '/account/login',
    name: 'Login',
    component: Login,
    meta: { layout: "account" },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
