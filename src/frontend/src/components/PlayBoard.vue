<template>
  <div class="board-wrapper">
    <p>{{hehe}}</p>
  </div>
</template>

<script>
export default {
  name: "PlayBoard",
  data() {
    return {
      hehe: ''
    }
  },
  mounted() {
    fetch("/api/game/1")
        .then((res) => {
          return res.text();
        })
        .then((data) => {
          this.hehe = data;
        })
  },
}
</script>

<style scoped>

</style>