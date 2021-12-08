<template lang="">
  <div class="problem-right-wrap">
    <div>
      <h2 data-aos="fade-left">
        <b>
          {{ name }}
        </b>
      </h2>
    </div>
    <div>
      <h4 data-aos="fade-right">
        <b>
          전체 문제 개수:
          <vue3-autocounter
            ref="counter"
            :startAmount="0"
            :endAmount="Number(size)"
            :duration="0.3"
            :autoinit="true"
          />
        </b>
      </h4>
      <h4 data-aos="fade-left">
        <b>{{ description }}</b>
      </h4>
    </div>
    <q-badge color="secondary">풀 문제 갯수: {{ standard }}</q-badge>
    <q-slider v-model="standard" :min="0" :max="Number(size)" markers label />
    <q-btn
      @click="selectProblem"
      q-btn
      color="deep-orange"
      glossy
      label="시작하기"
    ></q-btn>
  </div>
</template>
<script>
import Vue3autocounter from "vue3-autocounter";

export default {
  name: "problem-description",
  props: {
    id: String,
    description: String,
    name: String,
    size: Number,
  },
  data() {
    return {
      standard: 1,
    };
  },
  components: {
    "vue3-autocounter": Vue3autocounter,
  },

  setup() {},

  methods: {
    selectProblem() {
      if (this.standard == 0) {
        alert("문제가 선택되지 않았습니다.");
        return;
      }
      const payload = {
        id: this.id,
        page: this.standard,
      };
      this.$store
        .dispatch("root/requestProblemList", payload)
        .then((response) => {
          this.$store.commit("root/setProblemResultsInit");
          this.$store.commit("root/setSelctedCategory", this.id);
          this.$store.commit("root/setSelctedProblems", response.data);
          this.$router.push({
            name: "problem-solve",
            query: { num: 0, id: response.data[0].id },
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
<style scoped>
body {
  justify-content: center;
}
</style>
