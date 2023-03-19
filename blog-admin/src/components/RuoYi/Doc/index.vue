<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'blog-system',
  data() {
    return {
      url: 'https://github.com/iCedteas/blog-system'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
