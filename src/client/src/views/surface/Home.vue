<!--suppress ALL -->
<template lang="pug">

#home
  .inSalesProducts
    .input-group.w-50.me-auto.ms-auto.d-flex.justify-content-between.position-relative
      input.form-control( placeholder="Search everything you want....")
      img.input-group-text( src="../../assets/img/images/search.png" alt="Search Icon" )
  p IN SALES PRODUCTS


</template>

<script lang="ts">
import {Options, Vue} from 'vue-class-component'

@Options({})

export default class Home extends Vue {
}
</script>

<style src="../../assets/sass/layout/home.sass" lang="sass"/>
