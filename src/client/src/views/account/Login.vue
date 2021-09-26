<!--suppress ALL -->
<template lang="pug">

form#login.d-flex.justify-content-center.align-items-center.flex-column(
    method="POST"
    action="/api/controller/account/login"
  )

  img( src="../../assets/img/brand/logo.png" alt="Minimal" )

  input( :value="require('@/csrfManager').getToken()" name="_csrf" hidden )

  p.mb-0.title Login

  .inputs

    input( type="text" dir="auto" placeholder="email" name="email" )

    input( type="password" dir="auto" placeholder="password" name="password" )

  button( type="submit" ) Login

  p.mb-0.dn Don't have an account? #[a.cursor-pointer( @click="$router.push('/account/register')" ) Register]

  p.mb-0.fp.cursor-pointer Forgot your password?

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // On Page Load
  mounted()
  {

    // Get Page Response
    let res = this.$route.query.res

    if (res === "err")
    {
      //toast.error("System: User is not exists!", defaultToastSetting)
    }

    if (res === "safe")
    {
      //toast.warning("System : Sorry, this account has been secured! Please change your password to continue.", defaultToastSetting)
    }

    if (res === "acp")
    {
      //toast.warning("System : This account has not been verified!", defaultToastSetting)
    }

    if (res === "d_reg")
    {
      //toast.success("System : Your account has been created, please verify your account via the email sent to you.", defaultToastSetting)
    }
  }
})

export default class Login extends Vue {}
</script>