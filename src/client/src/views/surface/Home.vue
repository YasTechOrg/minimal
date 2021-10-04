<!--suppress ALL -->
<template lang="pug">

#home
  .inSalesProducts.pt-4.pb-4.ps-5.pe-5

    .input-group.searchInput.w-50.me-auto.ms-auto.d-flex.justify-content-between.position-relative

      input.form-control( placeholder="Search everything you want..." )
      input.form-control.d-none( placeholder="Search..." )
      img.input-group-text( src="../../assets/img/icons/icon_search.svg" alt="Search Icon" )

    p.sectionsTitle IN SALES PRODUCTS

    .row.m-0.pt-4.productsRowDisplay
      .col-3.p-2( v-for="i in 8" :key="i" )
        ProductsCart

    carousel.CardsSlider.pt-4.d-none( snap-align="center" :breakpoints="breakpoints" )

      Slide.p-2( v-for="i in 8" :key="i" )
        ProductsCart


      template( #addons )
        navigation



  .topSales.mt-5.pt-4.pb-4.ps-5.pe-5

    p.sectionsTitle.pt-4 TOP SALAES

    .row.m-0.pt-4

      .col-6.topSales_special.topSalesWidth.d-flex.justify-content-center

        .d-flex.flex-column.justify-content-between

          div
            img( src="../../assets/img/images/products_hoodi.png" )

            p.fst-italic Boys Special Hoodies
            p ADIDAS
            p.fw-bold 20,000$

          button.btn
            p Add to card

      .col-6.topSalesWidth

        .row.ps-2.productsRowDisplay
          .col-6.p-2( v-for="i in 4" :key="i" )
            ProductsCart

        carousel.CardsSlider.d-none( snap-align="center" :breakpoints="breakpoints" )

          Slide.p-2( v-for="i in 8" :key="i" )
            ProductsCart


          template(#addons)
            navigation


  .newest.mt-5.pt-4.pb-4.ps-5.pe-5

    p.sectionsTitle.pt-4 NEWEST

    carousel.mt-4.CardsSlider( snap-align="center" :breakpoints="breakpoints" )

      Slide.p-2( v-for="i in 8" :key="i" )
        ProductsCart

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

@Options({
  components: {
    Payment,
    Carousel,
    Slide,
    Navigation,
    ProductsCart
  },
  data() {
    return {
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
    }
  },

})

export default class Home extends Vue {}
</script>

<style src="../../assets/sass/page/home.sass" lang="sass"/>
