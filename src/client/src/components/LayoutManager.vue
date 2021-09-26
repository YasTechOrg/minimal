<!--suppress ALL -->
<template lang="pug">

#loader-wrapper.d-flex.justify-content-center.align-items-center.d-none

  img( src="../assets/img/animations/loader.gif" alt="loader" )

.layoutManager

  header.d-flex.align-items-center.justify-content-center( v-if="pageLayout === 'surface'" data-main )

    .inner.d-flex.align-items-center.justify-content-between.desktop

      .left.d-flex.align-items-center.justify-content-start

        img( src="../assets/img/brand/logo.png" alt="Minimal" class="cursor-pointer" @click="$router.push('/')" )

        .navigation.d-flex.align-items-center.justify-content-start

          router-link( to="/" active-class="selected" ) Home

          router-link( to="/categories" active-class="selected" ) Categories

          router-link( to="/shops" active-class="selected" ) Shops

          router-link( to="/about" active-class="selected" ) About Us

          router-link( to="/contact" active-class="selected" ) Contact Us


      .right

        button( @click="$router.push('/account/login')" ) Login

    .inner.d-flex.justify-content-between.w-100.align-items-center.mobile.d-none

      .left.d-flex.justify-content-start.align-items-center

        img( src="../assets/img/images/menu.png" alt="menu" )

      .logo.d-flex.justify-content-center.align-items-center

        img( src="../assets/img/brand/logo.png" alt="Minimal" class="cursor-pointer" @click="$router.push('/')" )

      .right.d-flex.justify-content-end.align-items-center

        button.d-flex.justify-content-center.align-items-center( @click="$router.push('/account/login')" )
          img( src="../assets/img/images/login_white.png" alt="login" )

  main( v-if="pageLayout === 'surface'" data-surface )
    slot

  main( v-if="pageLayout === 'account'" data-account )
    slot

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // App Watchers
  watch: {

    // Set Layout Title
    $route: {
      immediate: true,
      handler(to)
      {
        // Set Title
        document.title = to.meta.title || `Minimal`

        // Is Router Loaded
        if (typeof this.$route.name !== 'undefined')
        {

          // Set Title
          document.title = to.meta.title || `${this.$route.name} | Minimal`

          // Load Page
          this.load()
        }
      }
    },
  },

  // App Methods
  methods: {

    // On App Load
    async load()
    {
      document.getElementById("loader-wrapper")!.classList.remove("d-none")

      await new Promise(resolve => setTimeout(resolve, 1000))

      document.getElementById("loader-wrapper")!.classList.add("d-none")
    }
  },

  // App Computed Variables
  computed: {

    // Get Page Layout
    pageLayout()
    {
      return this.$route.meta["layout"]
    },
  }
})

export default class LayoutManager extends Vue {}
</script>

<style src="../assets/scss/layout/header.sass" lang="sass"/>
<style src="../assets/scss/layout/account.sass" lang="sass"/>
<style src="../assets/scss/layout/surface.sass" lang="sass"/>