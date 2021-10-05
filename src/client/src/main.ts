import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import "@/assets/lib/w3.css"
import "@/assets/lib/google_icons.css"
import "vue3-carousel/dist/carousel.css"
import "@vueform/slider/themes/default.css"
import "@vueform/multiselect/themes/default.css"

const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')
