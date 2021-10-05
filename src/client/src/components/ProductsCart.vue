<!--suppress ALL -->
<template lang="pug">

.productCards
  .topSection.d-flex.justify-content-between

    img( src="../assets/img/images/products_shirt.png" alt="Product")

    .options.d-flex.flex-column

      .d-flex.mb-1
        p.me-2 {{ product.like.length }}
        img( src="../assets/img/icons/icon_like.svg" alt="Like Icon")

      img.mb-2( src="../assets/img/icons/icon_share.svg" alt="Share Icon")
      img.me-1.mt-1( src="../assets/img/icons/icon_bookmark.svg" alt="bookmark Icon")

  .middleSection.d-flex.justify-content-between

    .details
      p {{ product.categories }}
      p {{ product.code }}
      p {{ product.publisher }}

    .colors

      .d-flex.pt-1

        div( v-for="(color,index) in product.colors" :key="color" )
          .outline.ms-2( :style="{'border': '1px solid' + color}" v-if="index===0" )
          .productColor( :style="{'background-color':color}" )

  .bottomSection.d-flex.justify-content-between

    p.mb-0
      span.offStyle.ms-1 {{ product.offValue.toLocaleString() }}$
      br
      span {{ product.price.toLocaleString() }}$

    button.btn
      img( src="../assets/img/icons/icon_plus.svg" alt="plus")

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({
  data(){
    return{
      props:["product"]
    }
  }
})
// ToDo : Bind Style
export default class ProductsCard extends Vue {}
</script>

<style scoped src="../assets/sass/components/productsCart.sass" lang="sass"></style>