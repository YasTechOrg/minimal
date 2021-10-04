<template lang="pug">

#about

  .pageContainer

    p.mb-5.pageTitle ABOUT US

    p.w-75 MINIMAL is an online store that focuses on selling clothes and was developed by Hossein Araghi and Fatemeh Hallaji. The main reason for this project is the final project of the Harvard Computer Science course.

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import ProductsCart from "@/components/ProductsCart.vue";

@Options({
  components: {ProductsCart}
})

export default class About extends Vue {}
</script>

<style src="../../assets/sass/page/about.sass" lang="sass"/>