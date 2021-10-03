<!--suppress ALL -->
<template lang="pug">

#search

  .searchInput.d-flex.align-items-center.justify-content-between

    input( placeholder="Search everything you want...." type="text" v-model="search" )

    img( src="../../assets/img/icons/icon_search_gray.svg" alt="Search" )

  .searchContainer.d-flex.align-items-start.justify-content-center

    .left.flex-grow-0

      .inner.w-100

        .part1
          .header

            p.mb-0 Category

            div

          .content




    .right.flex-grow-1


</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({
  data()
  {
    return {
      windowWidth: window.innerWidth,
      search: this.$route.query.q
    }
  },

  mounted()
  {
    this.$nextTick(() =>
    {
      window.addEventListener('resize', this.onResize)
    })
  },

  beforeDestroy()
  {
    window.removeEventListener('resize', this.onResize)
  },

  methods: {
    onResize()
    {
      this.windowWidth = window.innerWidth
    }
  },

  watch: {
    windowWidth()
    {
      if(this.windowWidth < 576)
      {
        document
            .querySelector("#search > .searchInput > input")!
            .setAttribute("placeholder", "Search...")
      }
      else
      {
        document
            .querySelector("#search > .searchInput > input")!
            .setAttribute("placeholder", "Search everything you want...")
      }
    }
  },
})

export default class Search extends Vue {}
</script>

<style src="../../assets/sass/page/search.sass" lang="sass"/>