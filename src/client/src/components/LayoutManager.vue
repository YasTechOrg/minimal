<template lang="pug">

.layoutManager

  slot

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({

})

export default class LayoutManager extends Vue {}
</script>
