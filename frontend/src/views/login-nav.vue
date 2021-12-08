<template>
  <div v-if="!state.admin">
    <q-btn
      unelevated
      flat
      id="menuBtn1"
      class="menu-btn menus"
      @click="mvLoginInfo"
      >소개</q-btn
    >
    <q-btn
      unelevated
      flat
      id="menuBtn2"
      class="menu-btn menus"
      @click="mvProblem"
      >문제풀기</q-btn
    >
    <q-btn unelevated flat id="menuBtn3" class="menu-btn menus" @click="mvBoard"
      >게시판</q-btn
    >
    <q-btn
      unelevated
      flat
      id="menuBtn4"
      class="menu-btn menus"
      @click="mvMypage"
      >마이페이지</q-btn
    >
    <q-btn unelevated flat class="menu-btn" @click="mvLogout">로그아웃</q-btn>
  </div>
  <div v-else>
    <q-btn unelevated flat class="menu-btn menus click-menu">문제승인</q-btn>
    <q-btn unelevated flat class="menu-btn" @click="mvLogout">로그아웃</q-btn>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { reactive, onBeforeMount } from "vue";

export default {
  name: "login-nav",
  setup(props, { emit }) {
    const router = useRouter();
    const store = useStore();

    const state = reactive({
      user: null,
      admin: false,
    });

    onBeforeMount(() => {
      if (
        JSON.parse(localStorage.getItem("userInfo")).email == "admin@naver.com"
      ) {
        state.admin = true;
      }
    });

    const mvLoginInfo = () => {
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.remove("click-menu");
      localStorage.removeItem("menu");
      localStorage.setItem("menu", "menuBtn1");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.add("click-menu");

      router.push({ name: "info-information" });
    };

    const mvProblem = () => {
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.remove("click-menu");
      localStorage.removeItem("menu");
      localStorage.setItem("menu", "menuBtn2");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.add("click-menu");
      store
        .dispatch("root/requsetCategoryList")
        .then(
          (response) => {
            console.log(response);
            router.push({ name: "problem-info" });
          },
          (error) => {
            console.log(error);
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };

    const mvBoard = () => {
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.remove("click-menu");
      localStorage.removeItem("menu");
      localStorage.setItem("menu", "menuBtn3");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.add("click-menu");
      router.push({ name: "board-info" });
    };
    const mvMypage = () => {
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.remove("click-menu");
      localStorage.removeItem("menu");
      localStorage.setItem("menu", "menuBtn4");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.add("click-menu");
      store
        .dispatch("root/requsetMyChart")
        .then(
          (response) => {
            store.commit("root/setScoreHistory", response.data);
            store
              .dispatch("root/requsetMyChartByCategory", 3)
              .then((response) => {
                store.commit("root/setScoreHistoryByCategory", response.data);
                store
                  .dispatch("root/requsetCategoryList")
                  .then((response) => {
                    store.commit("root/setCategories", response.data);
                  })
                  .catch((error) => {
                    console.log(error);
                  });
                router.push({ name: "mypage-chart" });
              });
          },
          (error) => {
            console.log(error);
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };
    const mvLogout = () => {
      let flag = confirm("로그아웃하시겠습니까?");
      if (flag) {
        localStorage.removeItem("token");
        localStorage.removeItem("userInfo");
        localStorage.removeItem("menu");
        router.push("/");
        emit("logout");
      }
    };
    return {
      state,
      onBeforeMount,
      mvLoginInfo,
      mvProblem,
      mvBoard,
      mvMypage,
      mvLogout,
    };
  },
};
</script>
