<!--suppress ALL -->
<template lang="pug">

#home
  .inSalesProducts.pt-4.pb-4.ps-5.pe-5

    form.input-group.searchInput.w-50.me-auto.ms-auto.d-flex.justify-content-between.position-relative(
      method="GET"
      action="/search"
    )

        input.form-control( placeholder="Search everything you want..." name="q" type="text" )
        img.input-group-text( src="../../assets/img/icons/icon_search.svg" alt="Search Icon" )

    p.sectionsTitle IN SALES PRODUCTS

    .row.m-0.pt-4.productsRowDisplay
      .col-3.p-2( v-for="product in inSalesProduct" :key="product" )
        ProductsCart( :product="product" )

    carousel.CardsSlider.pt-4.d-none( snap-align="center" :breakpoints="breakpoints" )

      Slide.p-2( v-for="product in inSalesProduct" :key="product" )
        ProductsCart( :product="product" )


      template( #addons )
        navigation



  .topSales.mt-5.pt-4.pb-4.ps-5.pe-5

    p.sectionsTitle.pt-4 TOP SALES

    .row.m-0.pt-4

      .col-6.topSales_special.topSalesWidth.d-flex.justify-content-center( v-if="specialProductExist" )

        .d-flex.flex-column.justify-content-between.mt-2.mb-2.w-100

          div
            img( src="../../assets/img/images/products_hoodi.png" )

            p.fst-italic {{ specialProduct.name }}
            p {{ specialProduct.publisher }}
            p.fw-bold {{ specialProduct.price.toLocaleString() }}$

          button.btn
            p Add to card

      .topSalesWidth( :class="{ 'col-12': !specialProductExist , 'col-6' : specialProductExist }" )

        .row.ps-2.productsRowDisplay
          .col-6.p-2( v-for="product in topSales" :key="product" )
            ProductsCart( :product="product" )

        carousel.CardsSlider.d-none( snap-align="center" :breakpoints="breakpoints" )

          Slide.p-2( v-for="product in topSales" :key="product" )
            ProductsCart( :product="product" )


          template(#addons)
            navigation


  .newest.mt-5.pt-4.pb-4.ps-5.pe-5

    p.sectionsTitle.pt-4 NEWEST

    carousel.mt-4.CardsSlider( snap-align="center" :breakpoints="breakpoints" )

      Slide.p-2( v-for="product in newestProduct" :key="product" )
        ProductsCart( :product="product" )

      template(#addons)
        navigation

  .row.mt-5.categoriesSection

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
import { Carousel, Slide, Navigation } from 'vue3-carousel'
import Payment from "@/views/dashboard/Payment.vue"
import ProductsCart from "@/components/ProductsCart.vue"
import axios from "axios"
import { getToken } from "@/csrfManager"


@Options({
  components: {
    Payment,
    Carousel,
    Slide,
    Navigation,
    ProductsCart
  },

  data()
  {
    return {
      windowWidth: window.innerWidth,

      breakpoints: {

        // Mobile
        0: {
          itemsToShow: 1,
        },

        // Tablet
        770: {
          itemsToShow: 2,
        },

        //desktop
        1120: {
          itemsToShow: 3,
        },
      },
      inSalesProduct: [],
      specialProductExist: false,
      specialProduct: {
        name: "",
        price: 0
      },
      topSales: [],
      newestProduct: []
    }
  },

  async mounted()
  {

    this.$nextTick(() =>
    {
      window.addEventListener('resize', this.onResize)
    })


    this.inSalesProduct = await new Promise(resolve =>
    {
      axios
          .get("/api/rest/product/get/off/8", {
            headers : {
              "_csrf" : getToken() as any
            }
          })
          .then(value => resolve(value.data))
          .catch(reason => console.log(reason))
    })

    this.specialProductExist = await new Promise(resolve =>
    {
      axios
          .get("/api/rest/product/get/specials/is", {
            headers : {
              "_csrf" : getToken() as any
            }
          })
          .then(async value =>
          {
            resolve(value.data)

            if (value.data)
            {
              this.specialProduct = await new Promise(resolve2 => {
                axios
                    .get("/api/rest/product/get/specials/random", {
                      headers: {
                        "_csrf": getToken() as any
                      }
                    })
                    .then(value2 => resolve2(value2.data))
                    .catch(reason2 => console.log(reason2))
              })

              this.topSales = await new Promise(resolve2 => {
                axios
                    .get("/api/rest/product/get/specials/4", {
                      headers: {
                        "_csrf": getToken() as any
                      }
                    })
                    .then(value2 => resolve2(value2.data))
                    .catch(reason2 => console.log(reason2))
              })
            }
            else
            {
              this.topSales = await new Promise(resolve2 => {
                axios
                    .get("/api/rest/product/get/specials/8", {
                      headers: {
                        "_csrf": getToken() as any
                      }
                    })
                    .then(value2 => resolve2(value2.data))
                    .catch(reason2 => console.log(reason2))
              })
            }
          })
          .catch(reason => console.log(reason))
    })

        this.newestProduct = await new Promise(resolve => {
          axios
              .get("/api/rest/product/get/newest", {
                headers: {
                  "_csrf": getToken() as any
                }
              })
              .then(value => resolve(value.data))
              .catch(reason => console.log(reason))
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
            .querySelector("#home > .inSalesProducts > form > input")!
            .setAttribute("placeholder", "Search...")
      }
      else
      {
        document
            .querySelector("#home > .inSalesProducts > form > input")!
            .setAttribute("placeholder", "Search everything you want...")
      }
    }
  },
})

export default class Home extends Vue {}
</script>

<style src="../../assets/sass/page/home.sass" lang="sass"/>
