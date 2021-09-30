<!--suppress ALL -->
<template lang="pug">

#loader-wrapper.d-flex.justify-content-center.align-items-center.d-none

  img( src="../assets/img/animations/loader.gif" alt="loader" )

#mobile_menu.d-none

  .w-100.d-flex.flex-column

    router-link( active-class="ac" to="/" ) Home
    router-link( active-class="ac" to="/categories" ) Categories
    router-link( active-class="ac" to="/shops" ) Shops
    router-link( active-class="ac" to="/about" ) About Us
    router-link( active-class="ac" to="/contact" ) Contact Us

    .d-flex.flex-column.p-0( v-if="cAtuh" )

      .divir

      router-link( active-class="ac" to="/dashboard" ) Dashboard
      router-link( active-class="ac" to="/dashboard/cart" ) Cart
      router-link( active-class="ac" to="/dashboard/payment" ) Payment
      router-link( active-class="ac" to="/dashboard/purchase" ) Purchase
      router-link( active-class="ac" to="/dashboard/ticket" ) Ticket


.layoutManager

  header.d-flex.align-items-center.justify-content-center( v-if="pageLayout === 'surface' || pageLayout === 'dashboard'" data-main )

    .inner.d-flex.align-items-center.justify-content-between.desktop

      .left.d-flex.align-items-center.justify-content-start

        img( src="../assets/img/brand/logo.png" alt="Minimal" class="cursor-pointer" @click="$router.push('/')" )

        .navigation.d-flex.align-items-center.justify-content-start

          router-link( to="/" active-class="selected" ) Home

          router-link( to="/categories" active-class="selected" ) Categories

          router-link( to="/shops" active-class="selected" ) Shops

          router-link( to="/about" active-class="selected" ) About Us

          router-link( to="/contact" active-class="selected" ) Contact Us


      .right

        button( @click="$router.push('/account/login')" v-if="!cAtuh" ) Login

        .user.cursor-pointer.position-relative( v-else )
          p.mb-0( v-if="Object.keys(userInfo).length !== 0" ) {{ userInfo["firstname"][0] }}
          .dd.d-none

            .d-flex( @click="$router.push('/dashboard')" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 dashboard
              p.mb-0 Dashboard

            .d-flex( @click="$router.push('/dashboard/profile')" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 account_circle
              p.mb-0 Profile

            .d-flex( @click="$router.push('/dashboard/cart')" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 shopping_cart
              p.mb-0 Cart

            .d-flex( @click="logoutUser" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 logout
              p.mb-0 Logout

    .inner.d-flex.justify-content-between.w-100.align-items-center.mobile.d-none

      .left.d-flex.justify-content-start.align-items-center

        img( src="../assets/img/images/menu.png" alt="menu" @click="toggleMobileMenu" )

      .logo.d-flex.justify-content-center.align-items-center

        img( src="../assets/img/brand/logo.png" alt="Minimal" class="cursor-pointer" @click="$router.push('/')" )

      .right.d-flex.justify-content-end.align-items-center

        button.d-flex.justify-content-center.align-items-center( @click="$router.push('/account/login')" v-if="!cAtuh" )
          img( src="../assets/img/images/login_white.png" alt="login" )

        .user.position-relative( v-else )

          p.mb-0.unselectable( v-if="Object.keys(userInfo).length !== 0" ) {{ userInfo["firstname"][0] }}

          .dd.d-none

            .d-flex( @click="$router.push('/dashboard')" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 dashboard
              p.mb-0.unselectable Dashboard

            .d-flex( @click="$router.push('/dashboard/profile')" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 account_circle
              p.mb-0.unselectable Profile

            .d-flex( @click="$router.push('/dashboard/cart')" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 shopping_cart
              p.mb-0.unselectable Cart

            .d-flex( @click="logoutUser" ).align-items-center.justify-content-start
              span.material-icons-outlined.md-24 logout
              p.mb-0.unselectable Logout


  main( v-if="pageLayout === 'surface'" data-surface )
    slot

  main( v-if="pageLayout === 'account'" data-account )
    slot

  main( v-if="pageLayout === 'dashboard'" data-dashboard ).d-flex.justify-content-center

    .inner.d-flex.align-items-start.justify-content-start

      .menu.flex-grow-0

        .inner

          p.mb-0 Menu

          router-link( active-class="ac" to="/dashboard" ).c.d-flex.justify-content-center.align-items-center
            | Dashboard

          router-link( active-class="ac" to="/dashboard/cart" ).c.d-flex.justify-content-center.align-items-center
            | Cart

          router-link( active-class="ac" to="/dashboard/payment" ).c.d-flex.justify-content-center.align-items-center
            | Payment

          router-link( active-class="ac" to="/dashboard/purchase" ).c.d-flex.justify-content-center.align-items-center
            | Purchase

          router-link( active-class="ac" to="/dashboard/profile" ).c.d-flex.justify-content-center.align-items-center
            | Profile

          router-link( active-class="ac" to="/dashboard/ticket" ).c.d-flex.justify-content-center.align-items-center
            | Ticket

          a.c.d.d-flex.justify-content-center.align-items-center.cursor-pointer( @click="logoutUser" )
            | Logout


      .content.flex-grow-1

        .navigation.d-flex.align-items-center.justify-content-start.w-100

          span.material-icons-outlined.md-24 dashboard

          p.mb-0 {{ $route.meta["nav"] }}

        slot

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { checkAuth, removeAuth, getAuth } from "@/authManager"
import axios from "axios"
import { getToken } from "@/csrfManager"

@Options({

  // App Varibales
  data()
  {
    return {
      userInfo: {}
    }
  },

  // App Watchers
  watch: {

    // Set Layout Title
    $route: {
      immediate: true,
      handler(to)
      {
        // Set Title
        document.title = to.meta.title || `Minimal`

        // Is Router Loaded
        if (typeof this.$route.name !== 'undefined')
        {

          // Set Title
          document.title = to.meta.title || `${ this.$route.name } | Minimal`

          if (!document.getElementById("mobile_menu")!.classList.contains("d-none"))
          {
            document.getElementById("mobile_menu")!.classList.add("d-none")
          }

          // Load Page
          this.load()
        }
      }
    },
  },

  // App Methods
  methods: {

    // On App Load
    async load()
    {
      // Show Loading
      document.getElementById("loader-wrapper")!.classList.remove("d-none")

      // Check Login
      if (checkAuth())
      {
        const d: any = localStorage.getItem("at_time")
        const date1: any = new Date(d)
        const date2: any = new Date()
        const diffTime = Math.abs(date2 - date1)
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))

        // Check if expired
        if (diffDays >= 5)
        {
          removeAuth()
          if (this.pageLayout === 'dashboard')
          {
            this.$router.push("/account/login?res=expi")
          }
        }

        // Double Login Check
        if (this.pageLayout === "account")
        {
          this.$router.push("/dashboard")
        }

        // Get User Info
        this.userInfo = await new Promise(resolve =>
        {
          axios
            .get("/api/rest/account/user/get", {
              headers: {
                "_csrf" : getToken(),
                "Authorization": getAuth()
              }
            })
            .then(value =>
            {
              resolve(value.data)

              localStorage.setItem("userData", JSON.stringify(value.data))
            })
            .catch(() =>
            {
              removeAuth()

              if (this.pageLayout === 'dashboard')
              {
                this.$router.push("/account/login?res=expi")
              }
            })
        })
      }
      else
      {
        if (this.pageLayout === "dashboard")
        {
          this.$router.push("/account/login")
        }
      }


      document.getElementById("loader-wrapper")!.classList.add("d-none")
    },

    // Logout
    logoutUser()
    {
      localStorage.removeItem("at")
      localStorage.removeItem("userData")
      location.reload()
    },

    // Toggle Mobile Menu
    toggleMobileMenu()
    {
      document.getElementById("mobile_menu")!.classList.toggle("d-none")
    }
  },

  // App Computed Variables
  computed: {

    // Get Page Layout
    pageLayout(): string
    {
      return this.$route.meta["layout"]
    },

    // Check Auth
    cAtuh(): boolean
    {
      return checkAuth()
    }
  }
})

export default class LayoutManager extends Vue {}
</script>

<style src="../assets/sass/layout/header.sass" lang="sass"/>
<style src="../assets/sass/layout/account.sass" lang="sass"/>
<style src="../assets/sass/layout/surface.sass" lang="sass"/>
<style src="../assets/sass/layout/dashboard.sass" lang="sass"/>