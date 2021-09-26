<!--suppress ALL -->
<template lang="pug">

form#register.d-flex.justify-content-center.align-items-center.flex-column(
    method="POST"
    action="/api/controller/account/register"
  )

  img( src="../../assets/img/brand/logo.png" alt="Minimal" )

  input( :value="require('@/csrfManager').getToken()" name="_csrf" hidden )

  p.mb-0.title Register

  .inputs

    input( type="text" dir="auto" placeholder="firstname" name="firstname" )

    input( type="text" dir="auto" placeholder="lastname" name="lastname" )

    input( type="text" dir="auto" placeholder="email" name="email" )

    input( type="password" dir="auto" placeholder="password" name="password" )

    input( type="password" dir="auto" placeholder="repet password" )

  button( type="submit" ) Register

  p.mb-0.dn Have an account? #[a.cursor-pointer( @click="$router.push('/account/login')" ) Login]

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // On Page Load
  mounted()
  {

    // Get Page Response
    let res = this.$route.query.res

    if (res === "exi")
    {
      //toast.error("System: This account is not exist!")
    }
  }
})

export default class Register extends Vue {}
</script>