<template lang="pug">

#dashboard
  h1 hi

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { setAuth } from "@/authManager"

@Options({

  // On Page Load
  mounted()
  {
    // Get Token
    const token: string = this.$route.query.at

    // Check If Url Have Token
    if (token != null && token !== "")
    {
      setAuth(token)
      location.href = "/dashboard"
    }
  }
})

export default class Dashboard extends Vue {}
</script>
