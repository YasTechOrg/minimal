<!--suppress ALL -->
<template lang="pug">

#categories
  .pageContainer

    p.pageTitle.mb-4 CATEGORIES

    .input-group.searchInput.d-flex.justify-content-between

      input.form-control( placeholder="Search Categories"  type="text" v-model="search" )
      img.input-group-text( src="../../assets/img/icons/icon_search.svg" alt="Search Icon" )

    .row.categoriesContainer.mt-4

      .col-3.p-2.cursor-pointer(
        v-for="category in filteredCategories"
        :key="category"
        @click="$router.push('/search?c=' + category['id'])"
      )
        .categoriesCart.d-flex.justify-content-center.align-items-center
          p {{ category["name"] }}

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({
  data()
  {
    return {

      search: "",

      categories: [
          { name : "Shirts", id: "shirts" },
          { name : "T-Shirts", id: "t-shirts" },
          { name : "College Shoes", id: "college_shoes" },
          { name : "Sport Shoes", id: "shirts" },
          { name : "High Heels", id: "high_heels" },
          { name : "Pants", id: "pants" },
          { name : "Socks", id: "socks" },
          { name : "Shorts", id: "shorts" },
      ]
    }
  },

  // Page Computed Variables
  computed: {

    filteredCategories()
    {
      return this.categories.filter((el: any) =>
      {
        return el.name.toLowerCase().indexOf(this.search.toLowerCase()) !== -1
      })
    }
  }
})

export default class Categories extends Vue {}
</script>

<style src="../../assets/sass/page/categories.sass" lang="sass"/>