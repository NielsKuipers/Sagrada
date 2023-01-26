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
        .then((res) => res.text())
        .then((data) => {
          this.hehe = data;
          console.log(data);
        })
  },
}
</script>

<style scoped>

</style>