<!--suppress ALL -->
<template lang="pug">

form#register.d-flex.justify-content-center.align-items-center.flex-column(
    method="POST"
    action="/api/controller/account/register"
  )

  img.cursor-pointer( src="../../assets/img/brand/logo.png" alt="Minimal" @click="$router.push('/')" )

  input( :value="require('@/csrfManager').getToken()" name="_csrf" hidden )

  p.mb-0.title Register

  .inputs

    input( type="text" dir="auto" placeholder="firstname" name="firstname" required )

    input( type="text" dir="auto" placeholder="lastname" name="lastname" required )

    input(
      type="text"
      dir="auto"
      placeholder="email"
      :class="{ 'err' : email.value !== '' && email.accept === false }"
      name="email"
      v-model="email.value"
      required
    )

    input(
      type="password"
      dir="auto"
      placeholder="password"
      :class="{ 'err' : password.value !== '' && password.accept === false }"
      name="password"
      v-model="password.value"
      required
    )

    input(
      type="password"
      dir="auto"
      placeholder="repet password"
      :class="{ 'err' : repetPassword.value !== '' && repetPassword.accept === false }"
      v-model="repetPassword.value"
      required
    )

  button( type="submit" :disabled="!access" ) Register

  .al1.alert.alert-danger.w-100( role="alert" v-if="res === 'exi'" ) This account is exist!

  p.mb-0.dn Have an account? #[a.cursor-pointer( @click="$router.push('/account/login')" ) Login]

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // Page Variables
  data()
  {
    return {
      res: this.$route.query.res,
      email: {
        accept: false,
        value: ""
      },
      password: {
        accept: false,
        value: ""
      },
      repetPassword: {
        accept: false,
        value: ""
      }
    }
  },

  // Page Computed Variables
  computed: {

    // Email Input Value
    emailInputValue()
    {
      return this.email.value
    },

    // Password Input Value
    passwordInputValue()
    {
      return this.password.value
    },

    // Password Input Value
    repetPasswordInputValue()
    {
      return this.repetPassword.value
    },

    // Access To Submit
    access(): boolean
    {
      if (this.email.accept && this.password.accept && this.repetPassword.accept)
      {
        return true
      }
      else
      {
        return false
      }
    }
  },

  // Page Variable Watcher
  watch: {

    // Watch Email Value
    emailInputValue()
    {
      this.email.accept = /^\w+([\\.-]?\w+)*@\w+([\\.-]?\w+)*(\.\w{2,3})+$/.test(this.email.value)
    },

    // Watch Password Value
    passwordInputValue()
    {
      this.password.accept = this.password.value.length > 5
    },

    // Watch Repet Password Value
    repetPasswordInputValue()
    {
      this.repetPassword.accept = this.password.value === this.repetPassword.value
    }
  }
})

export default class Register extends Vue {}
</script>