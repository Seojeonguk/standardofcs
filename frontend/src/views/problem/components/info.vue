<template lang="">
  <div class="problem-right-wrap">
    <div class="problem-main">
      <div class="problem-main-info first" data-aos="fade-up">
        <b>왼쪽 카테고리에서 문제를 선택해주세요.</b>
      </div>
      <div class="problem-main-info second" data-aos="fade-left">
        <b>전체 카테고리 : {{ state.categories.length }}</b>
      </div>
      <div class="problem-main-info second" data-aos="fade-right">
        <b>CSPS의 총 문제 개수 : {{ state.problemNum }}</b>
      </div>
      <div class="problem-main-make-btn" @click="makeprob">문제만들기</div>
      <div class="problem-main-bottom">
        <div class="problem-main-bottom-content first">
          문제를 풀기위해서 <span>마이크 권한</span>이 필요합니다.
        </div>
        <div class="problem-main-bottom-content second">
          좌측 상단의 마이크권한에서 <span>"허용"</span>을 눌러주세요.
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { computed, reactive } from "vue";

export default {
  name: "login-nav",
  data() {
    return {};
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      categories: computed(() => store.getters["root/getCategories"]),
      problemNum: computed(() => store.getters["root/getProblemNum"]),
    });

    const makeprob = () => {
      router.push({ name: "problem-make" });
    };
    const mikeRecognition = () => {
      console.log("내가여기클릭했소");
      window.SpeechRecognition =
        window.SpeechRecognition || window.webkitSpeechRecognition;
      const recognition = new window.SpeechRecognition();
      recognition.lang = "ko-KR";
      recognition.start();
    };
    mikeRecognition();
    return {
      state,
      makeprob,
      mikeRecognition,
    };
  },
  methods: {
    check() {
      console.log(this.state.problemNum);
    },
  },
};
</script>
<style lang=""></style>
