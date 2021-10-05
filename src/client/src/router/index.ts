import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from "@/views/surface/Home.vue"
import Categories from "@/views/surface/Categories.vue"
import Shops from "@/views/surface/Shops.vue"
import About from "@/views/surface/About.vue"
import Contact from "@/views/surface/Contact.vue"
import Login from "@/views/account/Login.vue"
import Register from "@/views/account/Register.vue"
import Dashboard from "@/views/dashboard/Dashboard.vue"
import Cart from "@/views/dashboard/Cart.vue"
import Payment from "@/views/dashboard/Payment.vue"
import Purchase from "@/views/dashboard/Purchase.vue"
import Profile from "@/views/dashboard/Profile.vue"
import Ticket from "@/views/dashboard/Ticket.vue"
import CS50 from "@/views/surface/CS50.vue"
import Search from "@/views/surface/Search.vue"
import Single from "@/views/surface/Single.vue"

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
    path: '/search',
    name: 'Search',
    component: Search,
    meta: { layout: "surface" },
  },
  {
    path: '/single',
    name: 'Single',
    component: Single,
    meta: { layout: "surface" },
  },
  {
    path: '/about',
    name: 'About Us',
    component: About,
    meta: { layout: "surface" },
  },
  {
    path: '/cs50x',
    name: 'CS50x Iran',
    component: CS50,
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
  },
  {
    path: '/account/register',
    name: 'Register',
    component: Register,
    meta: { layout: "account" },
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
    meta: { layout: "dashboard", nav: "Dashboard" },
  },
  {
    path: '/dashboard/cart',
    name: 'Cart',
    component: Cart,
    meta: { layout: "dashboard", nav: "Dashboard / Cart" },
  },
  {
    path: '/dashboard/payment',
    name: 'Payment',
    component: Payment,
    meta: { layout: "dashboard", nav: "Dashboard / Payment" },
  },
  {
    path: '/dashboard/purchase',
    name: 'Purchase',
    component: Purchase,
    meta: { layout: "dashboard", nav: "Dashboard / Purchase" },
  },
  {
    path: '/dashboard/profile',
    name: 'Profile',
    component: Profile,
    meta: { layout: "dashboard", nav: "Dashboard / Profile" },
  },
  {
    path: '/dashboard/ticket',
    name: 'Ticket',
    component: Ticket,
    meta: { layout: "dashboard", nav: "Dashboard / Ticket" },
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
