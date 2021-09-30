<template lang="pug">

#dashboard.d-flex.justify-content-start.align-items-start.flex-wrap

  .uif_card

    .header.desk.d-flex.align-items-center.justify-content-start
      MinimalProfile.size2( :email="userInfo['email']" :name="userInfo['firstname']" )

      .tp
        p {{ userInfo["firstname"] }} {{ userInfo["lastname"] }}
        p.mb-0 {{ userInfo["email"] }}

    .header.mobile.d-none.flex-column.align-items-center.justify-content-start
      MinimalProfile.size2( :email="userInfo['email']" :name="userInfo['firstname']" )

      .tp
        p {{ userInfo["firstname"] }} {{ userInfo["lastname"] }}
        p.mb-0 {{ userInfo["email"] }}

    .content.d-flex.flex-column.ms-auto.me-auto.mb-0

      button( @click="$router.push('/dashboard/profile')" ) Profile
      button( @click="$router.push('/dashboard/payment')" ) Payment Information
      button( @click="$router.push('/dashboard/purchase')" ) Purchase History


  .cart_card

    p.mb-0 Cart

    .content


</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { setAuth } from "@/authManager"
import MinimalProfile from "@/components/elements/MinimalProfile.vue"

@Options({

  // Page Components
  components: {
    MinimalProfile
  },

  // Page Variables
  data()
  {
    return {
      userInfo: {}
    }
  },

  // On Page Load
  async mounted()
  {
    // Get Token
    const token: string = this.$route.query.at

    // Check If Url Have Token
    if (token != null && token !== "")
    {
      setAuth(token)
      location.href = "/dashboard"
    }

    // Get User Info
    this.userInfo = JSON.parse(String(localStorage.getItem("userData")))
  }
})

export default class Dashboard extends Vue {}
</script>

<style src="../../assets/sass/page/dashboard.sass" lang="sass"/>