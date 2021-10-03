<!--suppress ALL -->
<template lang="pug">

#categories
  .pageContainer

    p.pageTitle.mb-4 CATEGORIES

    .input-group.searchInput.d-flex.justify-content-between

      input.form-control( placeholder="Search Categories"  type="text" )
      img.input-group-text( src="../../assets/img/icons/icon_search.svg" alt="Search Icon" )

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

})

export default class Categories extends Vue {}
</script>

<style src="../../assets/sass/page/categories.sass" lang="sass"/>