<template lang="pug">

.appLayer
  LayoutManager
    router-view

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import LayoutManager from "@/components/LayoutManager.vue"

@Options({
  components: {
    LayoutManager
  }
})
export default class App extends Vue {}
</script>

<style src="./assets/scss/public/style.sass" lang="sass"/>