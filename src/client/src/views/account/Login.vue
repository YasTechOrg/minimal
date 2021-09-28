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

    input( type="text" dir="auto" placeholder="email" name="email" required )

    input( type="password" dir="auto" placeholder="password" name="password" required )

  button( type="submit" ) Login

  .al1.alert.alert-danger.w-100( role="alert" v-if="res === 'err'" )
    | Username or Password is incorrect!

  .al2.alert.alert-warning.w-100( role="alert" v-if="res === 'safe'" )
    | Sorry, this account has been secured! Please change your password to continue.

  .al3.alert.alert-danger.w-100( role="alert" v-if="res === 'acp'" )
    | This account has not been verified!

  .al4.alert.alert-danger.w-100( role="alert" v-if="res === 'c_err'" )
    | Verify code is incorrect!

  .al5.alert.alert-success.w-100( role="alert" v-if="res === 'd_reg'" )
    | Your account has been created, please verify your account via the email sent to you.

  .al6.alert.alert-success.w-100( role="alert" v-if="res === 'c_done'" )
    | Your account has been verified! please login.

  p.mb-0.dn Don't have an account? #[a.cursor-pointer( @click="$router.push('/account/register')" ) Register]

  p.mb-0.fp.cursor-pointer Forgot your password?

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // Page Variables
  data()
  {
    return {
      res: this.$route.query.res
    }
  },
})

export default class Login extends Vue {}
</script>