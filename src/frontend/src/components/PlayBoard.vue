<template>
  <div class="board-wrapper">
    <div v-for="index in 4" :key="index">
      <a v-for="index in 5" :key="index">
        b
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "PlayBoard"
}
</script>

<style scoped>

</style>