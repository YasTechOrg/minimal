<template lang="pug">

#contact
  .pageContainer

    p.mb-5.pageTitle CONTACT US

    .d-flex
      p.me-3 email :
      p info@minimal.yastech.org

    .d-flex
      p.me-3 phone number :
      p 09928197949 ,
      p 09138272072

    .d-flex
      p.me-3 ticket :
      router-link( active-class="ac" to="/dashboard" ) go to dashboard


</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

})

export default class Contact extends Vue {}
</script>

<style src="../../assets/sass/page/contact.sass" lang="sass"/>