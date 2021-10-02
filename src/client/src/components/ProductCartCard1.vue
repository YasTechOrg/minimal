<!--suppress ALL -->
<template lang="pug">

.productCartCard1.w-100.d-flex.justify-content-between.align-items-center

  .left.d-flex.align-items-center.justify-content-start
    img( src="../assets/img/images/d1.png" alt="p_image" )

    .tp.d-flex.flex-column.justify-content-center.align-items-start
      p {{ product["p_name"] }}

      .d-flex.justify-content-start.align-items-center
        p.mb-0 {{ "#" + product["p_code"] }}

        p.mb-0 {{ (product["p_price"] * product["p_num"]).toLocaleString() }}$

  .right.d-flex.align-items-center.justify-content-end

    span.material-icons.md-24.cursor-pointer.unselectable( @click="$emit('dec', product['id'])" )
      | remove

    p.mb-0 {{ product["p_num"] }}

    span.material-icons.md-24.cursor-pointer.unselectable( @click="$emit('inc', product['id'])" )
      | add

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

  // Element Props
  props: ["product"],
})

export default class ProductCartCard1 extends Vue {}
</script>

<style scoped src="../assets/sass/components/productCartCard1.sass" lang="sass"></style>