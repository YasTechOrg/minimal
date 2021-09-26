<!--suppress ALL -->
<template lang="pug">

form#login.d-flex.justify-content-center.align-items-center.flex-column

  img( src="../../assets/img/brand/logo.png" alt="Minimal" )

  p.mb-0.title Login

  .inputs

    input( type="text" dir="auto" placeholder="username" )

    input( type="password" dir="auto" placeholder="password" )

  button( type="button" ) Login

  p.mb-0.dn Don't have an account? #[a.cursor-pointer( @click="$router.push('/account/register')" ) Register]

  p.mb-0.fp.cursor-pointer Forgot your password?

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

})

export default class Login extends Vue {}
</script>