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

        MinimalProfile(
          v-else-if="Object.keys(userInfo).length !== 0"
          :email="userInfo['email']"
          :name="userInfo['firstname']"
          @logout="logoutUser"
          menu="true"
        )

    .inner.d-flex.justify-content-between.w-100.align-items-center.mobile.d-none

      .left.d-flex.justify-content-start.align-items-center

        img( src="../assets/img/images/menu.png" alt="menu" @click="toggleMobileMenu" )

      .logo.d-flex.justify-content-center.align-items-center

        img( src="../assets/img/brand/logo.png" alt="Minimal" class="cursor-pointer" @click="$router.push('/')" )

      .right.d-flex.justify-content-end.align-items-center

        button.d-flex.justify-content-center.align-items-center( @click="$router.push('/account/login')" v-if="!cAtuh" )
          img( src="../assets/img/images/login_white.png" alt="login" )

        MinimalProfile(
          v-else-if="Object.keys(userInfo).length !== 0"
          :email="userInfo['email']"
          :name="userInfo['firstname']"
          @logout="logoutUser"
          menu="true"
        )


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
  footer.w-100( v-if="pageLayout === 'surface' || pageLayout === 'dashboard'" data-main )

    .logo_part.d-flex.justify-content-center.align-items-center

      img( src="../assets/img/brand/logo.png" alt="Logo" )

    .nav_part.row.m-auto

      .col-4.p-0.d-flex.justify-content-center.align-items-center

        router-link( to="/about" ) About Us

      .col-4.p-0.d-flex.justify-content-center.align-items-center

        router-link( to="/contact" ) Contact Us

      .col-4.p-0.d-flex.justify-content-center.align-items-center

        router-link( to="/cs50x" ) CS50XIRAN

    .copy_part.d-flex.justify-content-center.align-items-center

      p.mb-0 @ {{ new Date().getFullYear() }} All Rights Reserved | Hossein Araghi & Fatemeh Hallaji

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import axios from "axios"
import { getToken } from "@/csrfManager"
import MinimalProfile from "@/components/elements/MinimalProfile.vue"
import { mapGetters } from "vuex"

@Options({

  // App Components
  components: {
    MinimalProfile
  },

  // App Varibales
  data()
  {
    return {
      userInfo: {
        email: ""
      }
    }
  },

  // App Watchers
  watch: {

    // Set Layout Title
    $route: {
      immediate: true,
      async handler(to)
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

          // Show Loading
          document.getElementById("loader-wrapper")!.classList.remove("d-none")

          // Load Page
          await this.load()

          // Hide Loading
          document.getElementById("loader-wrapper")!.classList.add("d-none")
        }
      }
    },
  },

  // App Methods
  methods: {

    // On App Load
    async load()
    {
      // Check Login
      if (this.$store.state.at !== "")
      {
        const d: any = this.$store.state.at_time
        const date1: any = new Date(d)
        const date2: any = new Date()
        const diffTime = Math.abs(date2 - date1)
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24))

        // Check if expired
        if (diffDays >= 5)
        {
          this.$store.commit("removeAuth")

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
                "_csrf" : getToken() as any,
                "Authorization": this.getAuth
              }
            })
            .then(value =>
            {
              resolve(value.data)

              this.$store.commit("setUserData", value.data)
            })
            .catch(() =>
            {
              this.$store.commit("removeAuth")

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
    },

    // Logout
    logoutUser()
    {
      this.$router.push("/account/login?res=logout")
    },

    // Toggle Mobile Menu
    toggleMobileMenu()
    {
      document.getElementById("mobile_menu")!.classList.toggle("d-none")
    },

    // Get Browser Version
    getBrowserVersion()
    {
      var ua = navigator.userAgent
      var tem
      var M = ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || []

      if(/trident/i.test(M[1]))
      {
        tem =  /\brv[ :]+(\d+)/g.exec(ua) || []

        return 'IE ' + (tem[1] || '')
      }

      if(M[1] === 'Chrome')
      {
        tem = ua.match(/\b(OPR|Edge)\/(\d+)/)

        if(tem != null) return tem.slice(1).join(' ').replace('OPR', 'Opera')
      }

      M = M[2]? [M[1], M[2]] : [navigator.appName, navigator.appVersion, '-?']

      if((tem= ua.match(/version\/(\d+)/i)) != null) M.splice(1, 1, tem[1])

      return M.join(' ')
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
      return this.$store.state.at !== ""
    },

    ...mapGetters([
        "getAuth"
    ])
  }
})

export default class LayoutManager extends Vue {}
</script>

<style src="../assets/sass/layout/header.sass" lang="sass"/>
<style src="../assets/sass/layout/footer.sass" lang="sass"/>
<style src="../assets/sass/layout/account.sass" lang="sass"/>
<style src="../assets/sass/layout/surface.sass" lang="sass"/>
<style src="../assets/sass/layout/dashboard.sass" lang="sass"/>