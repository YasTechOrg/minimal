<!--suppress ALL -->
<template lang="pug">

form#register.d-flex.justify-content-center.align-items-center.flex-column

  img( src="../../assets/img/brand/logo.png" alt="Minimal" )

  p.mb-0.title Register

  .inputs

    input( type="text" dir="auto" placeholder="firstname" name="firstname" )

    input( type="text" dir="auto" placeholder="lastname" name="lastname" )

    input( type="text" dir="auto" placeholder="email" name="email" )

    input( type="password" dir="auto" placeholder="password" name="password" )

    input( type="password" dir="auto" placeholder="repet password" )

  button( type="button" ) Register

  p.mb-0.dn Have an account? #[a.cursor-pointer( @click="$router.push('/account/login')" ) Login]

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

})

export default class Register extends Vue {}
</script>