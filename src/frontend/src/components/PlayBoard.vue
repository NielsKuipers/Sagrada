<template>
  <div class="board-wrapper">
    <p>{{ hehe }}</p>
    <button @click=epicPost()>click
    </button>
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
  computed: {
    console: () => console
  },
  methods: {
    epicPost() {
      const postopt = {
        method: 'POST',
        headers: {},
        body: this.hehe
      };

      fetch('/api/game/', postopt)
          .then(res => res.text())
          .then((data) => {
            console.log(data);
          })
    }
  }
}
</script>

<style scoped>

</style>