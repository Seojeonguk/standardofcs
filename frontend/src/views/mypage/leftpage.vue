<template>
  <div class="left-wrap">
    <div class="open-left-wrap">
      <div class="mypage-left">
        <div class="profile-wrap">
          <img class="profile-img" :src="state.user.image" />
          <span class="user-name text-h4 text-bold">{{
            state.user.nickname
          }}</span>
        </div>
        <div class="mypage-menu text-h6">
          <div id="leftBtn1" class="myleft click-myleft" @click="mvChart">
            차트
          </div>
          <div id="leftBtn2" class="myleft" @click="mvModify">
            프로필사진 수정
          </div>
          <div id="leftBtn3" class="myleft" @click="mvBoard">나의 게시글</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { onBeforeMount, onUpdated, reactive } from "vue";
import { useRouter } from "vue-router";
import "../../styles/mypage.scss";

export default {
  name: "mypage-left",
  setup() {
    const router = useRouter();

    const state = reactive({
      user: null,
    });
    state.user = JSON.parse(localStorage.getItem("userInfo"));
    console.log(state.user);
    onUpdated(() => {
      state.user = JSON.parse(localStorage.getItem("userInfo"));
      console.log(state.user);
    });

    const mvChart = () => {
      removeBolder();
      addBolder("leftBtn1");
      router.push({ name: "mypage-chart" });
    };
    const mvModify = () => {
      removeBolder();
      addBolder("leftBtn2");
      router.push({ name: "mypage-modify" });
    };
    const mvBoard = () => {
      removeBolder();
      addBolder("leftBtn3");
      router.push({ name: "mypage-board" });
    };

    const removeBolder = () => {
      const myleft = document.getElementsByClassName("myleft");
      for (var i = 0; i < myleft.length; i++) {
        myleft[i].classList.remove("click-myleft");
      }
    };
    const addBolder = (id) => {
      const btn = document.getElementById(id);
      btn.classList.add("click-myleft");
    };
    return {
      state,
      onBeforeMount,
      mvChart,
      mvModify,
      mvBoard,
    };
  },
};
</script>
