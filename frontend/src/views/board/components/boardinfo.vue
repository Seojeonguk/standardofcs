<template lang="">
  <div class="board-info">
    <div class="info-content first">
      게시판은 <br /><span>CS의 정석</span>을 이용하는 사용자를 위해
      만들었습니다
    </div>
    <div class="info-content second">CS지식을 서로 질문하고 공유해 주세요!</div>
    <div class="info-content box" @click="mvBoardInfo">
      게시판 사용에 대한 소개보러가기
    </div>
    <div class="info-content third">
      <div>무분별한 욕설 및 비방글은 허용하지 않습니다.</div>
      <div>타인에게 불쾌감을 불러 일으키는 글은 자제해주세요.</div>
    </div>
  </div>
</template>
<script>
import "@/styles/board.scss";
import { onMounted } from "vue";
import { useRouter } from "vue-router";
export default {
  name: "board-info",
  setup() {
    onMounted(() => {
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.remove("click-menu");
      localStorage.removeItem("menu");
      localStorage.setItem("menu", "menuBtn3");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.add("click-menu");
    });

    const router = useRouter();

    if (localStorage.getItem("reload")) {
      localStorage.removeItem("reload");
      router.go();
    }

    const mvBoardInfo = () => {
      router.push({ name: "info-board-main" });
    };
    return { mvBoardInfo, onMounted };
  },
};
</script>
<style lang=""></style>
