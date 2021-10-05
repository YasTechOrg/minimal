<!--suppress ALL -->
<template lang="pug">

#dashboard.d-flex.justify-content-start.align-items-start

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

      .c.d-flex.flex-column( v-if="products.length !== 0" )

        ProductCartCard1(
          v-for="product in products"
          :key="product"
          :product="product"
        )

      .nf( v-else )

        p.mb-0 You cart is empty!


</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import MinimalProfile from "@/components/elements/MinimalProfile.vue"
import ProductCartCard1 from "@/components/ProductCartCard1.vue"
import axios from "axios";
import {getToken} from "@/csrfManager";
import {mapGetters} from "vuex";

@Options({

  // Page Components
  components: {
    MinimalProfile,
    ProductCartCard1
  },

  // On Page Load
  async mounted()
  {
    // Get Token
    const token: string = this.$route.query.at

    // Check If Url Have Token
    if (token != null && token !== "")
    {
      this.$store.commit("setAuth", token)
      location.href = "/dashboard"
    }
  },

  // Page Computed Variables
  computed: {

    // Return User Data
    userInfo()
    {
      return this.$store.state.userData
    },

    // Products
    products(): string[]
    {
      return this.$store.state.cart
    }
  }
})

export default class Dashboard extends Vue {}
</script>

<style src="../../assets/sass/page/dashboard.sass" lang="sass"/>