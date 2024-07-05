<template>
  <div id="overlay">
    <div id="spinnerload"></div>
  </div>
</template>
<script>
import "@/preload.js";
export default {
  name: "spinnerload",
  props: {
    msg: String,
  },
};
</script>
<style>
@import url("@/assets/css/spinnerloadstyle.css");
</style>
