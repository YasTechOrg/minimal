<template lang="pug">

#single
  | {{ $route.query.id }}

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({
})

export default class Single extends Vue {}
</script>

<style src="../../assets/sass/page/about.sass" lang="sass"/>