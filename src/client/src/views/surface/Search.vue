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

            p.text-center Category

            div

          .content

            .category.d-flex.justify-content-start.align-items-center.cursor-pointer(
              :class="{ 'ac' : category === 'shirts' }"
              @click="setCategory('shirts')"
            )
              img( src="../../assets/img/icons/icon_arrow_right_black.svg" alt="> " )
              p.mb-0 Shirts

            .category.d-flex.justify-content-start.align-items-center.cursor-pointer(
              :class="{ 'ac' : category === 't-shirts' }"
              @click="setCategory('t-shirts')"
            )
              img( src="../../assets/img/icons/icon_arrow_right_black.svg" alt="> " )
              p.mb-0 T-Shirts

            .category.d-flex.justify-content-start.align-items-center.cursor-pointer(
              :class="{ 'ac' : category === 'shoes' }"
              @click="setCategory('shoes')"
            )
              img( src="../../assets/img/icons/icon_arrow_right_black.svg" alt="> " )
              p.mb-0 Shoes

            .category.d-flex.justify-content-start.align-items-center.cursor-pointer(
              :class="{ 'ac' : category === 'pants' }"
              @click="setCategory('pants')"
            )
              img( src="../../assets/img/icons/icon_arrow_right_black.svg" alt="> " )
              p.mb-0 Pants

            .category.d-flex.justify-content-start.align-items-center.cursor-pointer(
              :class="{ 'ac' : category === 'socks' }"
              @click="setCategory('socks')"
            )
              img( src="../../assets/img/icons/icon_arrow_right_black.svg" alt="> " )
              p.mb-0 Socks

            .category.d-flex.justify-content-start.align-items-center.cursor-pointer(
              :class="{ 'ac' : category === 'shorts' }"
              @click="setCategory('shorts')"
            )
              img( src="../../assets/img/icons/icon_arrow_right_black.svg" alt="> " )
              p.mb-0 Shorts



        .part2
          .header

            p.text-center Price Range

            div

          .content

            Slider( v-model="priceValue" :min="0" :max="999999999" :tooltips="false" )

            .inputs.d-flex.align-items-center.justify-content-between

              .left
                input( v-model="priceValue[0]" type="text" )

              .right
                input( v-model="priceValue[1]" type="text" )


        .part3
          .header

            p.text-center Brands

            div

          .content
            Multiselect( v-model="brand" v-bind="brandsOptions" )


    .right.flex-grow-1
      p( v-if="searchResult === null" ) Sorry, No Result Found
      p( v-else-if="searchResult.length === 0" ) Searching...
      p( v-else ) {{ searchResult.length }} Results

      div.line

      .results.text-center

        img(
          src="../../assets/img/animations/not_found.gif"
          v-if="searchResult === null"
          alt="Not Found"
        )

        img(
          src="../../assets/img/animations/loader.gif"
          v-else-if="searchResult.length === 0"
          alt="Searching..."
        )

        .res.d-flex.align-items-start.justify-content-start( v-else )
          | res



</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import Slider from "@vueform/slider"
import Multiselect from "@vueform/multiselect"
import axios from "axios"
import { getToken } from "@/csrfManager"
import { mapGetters } from "vuex"
import SockJS from "sockjs-client"
import Stomp from "webstomp-client"
import { watch } from "vue"

@Options({

  // Page Components
  components: {
    Slider,
    Multiselect
  },

  // Page Variables
  data()
  {
    return {
      searchResult: [],
      windowWidth: window.innerWidth,
      search: "",
      searchClient: null,
      searchSocket: null,
      brandsOptions: {
        mode: 'tags',
        placeholder: 'Select brands...',
        filterResults: false,
        minChars: 1,
        resolveOnLoad: false,
        delay: 0,
        searchable: true,
        createTag: true,
        options: async () =>
        {
          return await new Promise(resolve =>
          {
            axios
                .get("/api/rest/public/brands/get", {
                  headers: {
                    "_csrf" : getToken() as any,
                    "Authorization": this.getAuth
                  }
                })
                .then(value =>
                {
                  const res: string[] = value.data

                  resolve(res.map((item: any) =>
                  {
                    return { value: item["email"], label: item["name"] }
                  }))
                })
                .catch(reason => console.log(reason))
          })
        },
      },
      category: "",
      brand: [],
      priceValue: [0, 999999999],
    }
  },

  // On Page Load
  mounted()
  {
    if (this.$route.query.q == null || typeof this.$route.query.q === "undefined")
    {
      this.search = ""
    }
    else
    {
      this.search = this.$route.query.q
    }

    this.$nextTick(() =>
    {
      window.addEventListener('resize', this.onResize)
    })

    // Define this
    /* eslint-disable  @typescript-eslint/no-this-alias */
    const t = this

    // Connect To Socket Server
    this.searchSocket = new SockJS(location.origin + "/wst")

    // Define Stomp Client
    this.searchClient = Stomp.over(this.searchSocket)

    // Disable Stomp Logging
    this.searchClient.debug = () => { /**/ }

    // Connect Stomp
    this.searchClient.connect({}, () =>
    {
      // Subscribe Stomp
      this.searchClient.subscribe('/user/search/get', function (response: any)
      {
        const resp = JSON.parse(response.body)
        if (resp.length === 0)
        {
          t.searchResult = null
          console.log(null)
        }
        else
        {
          t.searchResult = resp
          console.log(resp)
        }
      })
      this.searchClient.send("/ss/do-search", "", { 'search': JSON.stringify(this.getSearchObject) })
      watch(() => this.getSearchObject, () =>
      {
        this.searchResult = []
        this.searchClient.send("/ss/do-search", "", { 'search': JSON.stringify(this.getSearchObject) })
      })
    })
  },

  // On Page Unload
  beforeDestroy()
  {
    window.removeEventListener('resize', this.onResize)
  },

  // Page Methods
  methods: {
    onResize()
    {
      this.windowWidth = window.innerWidth
    },

    // Set Product Category
    setCategory(category: string)
    {
      if (this.category === category)
      {
        this.category = ""
      }
      else
      {
        this.category = category
      }
    }
  },

  // Page Variable wathcer
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
    },
  },

  // Page Computed Varaibles
  computed: {

    // Get Vuex Getters
    ...mapGetters([
      "getAuth"
    ]),

    // Get Search Items As Object
    getSearchObject()
    {
      return {
        text: this.search,
        category: this.category,
        brands: this.brand,
        min_price: this.priceValue[0],
        max_price: this.priceValue[1],
      }
    }
  }
})

export default class Search extends Vue {}
</script>

<style src="../../assets/sass/page/search.sass" lang="sass"/>