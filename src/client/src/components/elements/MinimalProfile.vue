<template lang="pug">

.minimalProfile.cursor-pointer.position-relative

  p.mb-0( v-if="name" ) {{ name[0] }}

  .dd.d-none( v-if="menu" )

    .d-flex( @click="$router.push('/dashboard')" ).align-items-center.justify-content-start
      span.material-icons-outlined.md-24 dashboard
      p.mb-0 Dashboard

    .d-flex( @click="$router.push('/dashboard/profile')" ).align-items-center.justify-content-start
      span.material-icons-outlined.md-24 account_circle
      p.mb-0 Profile

    .d-flex( @click="$router.push('/dashboard/cart')" ).align-items-center.justify-content-start
      span.material-icons-outlined.md-24 shopping_cart
      p.mb-0 Cart

    .d-flex( @click="$emit('logout')" ).align-items-center.justify-content-start
      span.material-icons-outlined.md-24 logout
      p.mb-0 Logout

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // Element Props
  props: ["email", "name", "menu"],

  // Element Variables
  data()
  {
    return {
      image: false,
    }
  },
})

export default class MinimalProfile extends Vue {}
</script>

<style scoped src="../../assets/sass/elements/minimalProfile.sass" lang="sass"></style>