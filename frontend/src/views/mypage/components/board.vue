<template lang="">
  <div class="mypage-board-wrap">
    <div v-if="state.error != ''" class="board-error">
      <div>{{ state.error }}</div>
    </div>
    <div v-else class="mypage-board-lists">
      <div
        v-for="(board, idx) in state.boards"
        :key="board.id"
        class="mypage-board-list"
      >
        <span class="boardlist-1">{{ idx + 1 }}</span>
        <span class="boardlist-2" @click="mvdetail(board.id)">
          {{ board.title }}</span
        >
        <span class="boardlist-3">{{ board.createdAt }}</span>
      </div>
    </div>
  </div>
</template>
<script>
import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

export default {
  setup() {
    const store = useStore();
    const router = useRouter();

    const state = reactive({
      user: JSON.parse(localStorage.getItem("userInfo")),
      error: "",
      boards: [],
    });
    const getUserBoard = () => {
      store
        .dispatch("root/requestUserBoardList", state.user.id)
        .then(
          (response) => {
            state.boards = response.data;
          },
          (error) => {
            state.error = error.response.data.message;
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };

    getUserBoard();

    const mvdetail = (id) => {
      const url = "/home/board/question/" + id;
      router.push({ path: url });
    };

    return {
      state,
      getUserBoard,
      mvdetail,
    };
  },
};
</script>
<style lang=""></style>
