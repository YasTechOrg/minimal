<!--suppress ALL -->
<template lang="pug">

#categories
  .pageContainer

    p.pageTitle.mb-4 CATEGORIES

    .input-group.searchInput.d-flex.justify-content-between

      input.form-control( placeholder="Search Categories"  type="text" )
      img.input-group-text( src="../../assets/img/icons/icon_search.svg" alt="Search Icon" )

    .row.categoriesContainer.mt-4

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p Shirts

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p T-Shirts

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p College Shoes

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p Sport Shoes

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p High Heels

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p Pants

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p Socks

      .col-3.p-2
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p Shorts



</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

})

export default class Categories extends Vue {}
</script>

<style src="../../assets/sass/page/categories.sass" lang="sass"/>