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
      p.cursor-pointer(
        @click="$router.push(`/single?id=${ product['id'] }`)"
      ) {{ product.name }}
      p {{ '#' + product.code }}
      p {{ product.publisher }}

    .colors

      .d-flex.pt-1.justify-content-start.align-items-center.flex-row-reverse

        div.ms-1(
          v-for="( color, index ) in product.colors"
          :class="{ 'outline' : index === 0 }"
          :key="color"
        )
          .productColor(
            :style="{ 'background-color': color }"
          )

  .bottomSection.d-flex.justify-content-between

    p.mb-0.text-start
      span.offStyle {{ product.offValue.toLocaleString() }}$
      br
      span {{ product.price.toLocaleString() }}$

    button.btn( @click="addToCart" )
      img( src="../assets/img/icons/icon_plus.svg" alt="plus")

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  props: ["product"],

  methods: {

    addToCart()
    {
      this.$store.commit("addToCart", {
        p_id : this.product["id"],
        p_name: this.product["name"],
        p_image: this.product["image"],
        p_code: this.product["code"],
        p_price: this.product["price"],
      })
    }
  }
})
export default class ProductsCard extends Vue {}
</script>

<style scoped src="../assets/sass/components/productsCart.sass" lang="sass"></style>