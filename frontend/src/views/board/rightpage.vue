<template>
  <div class="right-wrap">
    <div class="open-right-wrap">
      <router-view style="height: 100%" :key="route.fullPath"></router-view>
    </div>
  </div>
</template>
<script>
import { useRoute } from "vue-router";

export default {
  name: "board-right",
  setup() {
    const route = useRoute();
    return {
      route,
    };
  },
};
</script>
