<template>
  <div class="left-wrap">
    <div class="open-left-wrap">
      <div class="problem-left-wrap">
        <div>
          <div class="problem-info-btn" @click="mvProblemInfo">
            <b>문제카테고리</b>
          </div>
        </div>
        <div class="s1_arrow">
          <div class="scroll-arrow"></div>
          <div class="scroll-arrow"></div>
        </div>
        <div class="problem-category-list">
          <div
            class="problem-category-btns"
            v-for="(category, index) in state.categories"
            :key="index"
            @click="mvProblemDescription(category)"
          >
            <div
              class="problem-category-btn"
              v-if="index % 2 == 0"
              data-aos="fade-right"
            >
              {{ category.name }}
            </div>
            <div class="problem-category-btn" v-else data-aos="fade-left">
              {{ category.name }}
            </div>
            <q-tooltip>{{ category.description }}</q-tooltip>
          </div>
          <br />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "@/styles/problem.scss";
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "problem-left",
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      categories: computed(() => store.getters["root/getCategories"]),
    });

    const mvProblemDescription = (category) => {
      store
        .dispatch("root/requestProblemNumInCategory", category.id)
        .then((response) => {
          router.push({
            name: "problem-description",
            params: {
              id: category.id,
              description: category.description,
              name: category.name,
              size: response.data,
            },
          });
        });
    };
    const mvProblemInfo = () => {
      router.push({ name: "problem-info" });
    };

    return {
      state,
      mvProblemDescription,
      mvProblemInfo,
    };
  },
};
</script>
<style></style>
