<template>
  <div v-if="state.error != ''">{{ state.error }}</div>
  <div v-else>
    <div class="board-question" v-if="state.question != null">
      <div class="question-header">
        <div class="question-title">
          {{ state.question.title }}
        </div>
        <div class="question-delete">
          <div
            class="question-delete-btn"
            v-if="state.question.user.id == state.user.id"
            @click="deleteBoard()"
          >
            삭제
          </div>
        </div>
      </div>
      <div class="question-createdAt">{{ state.question.createdAt }}</div>
      <div class="question-user">
        <div class="question-user-image">
          <img
            :src="state.question.user.image"
            class="question-user-image-circle"
          />
        </div>
        <div class="question-user-nickName">
          {{ state.question.user.nickName }}
        </div>
      </div>
      <hr />
      <div class="question-info">
        <div class="question-description" id="viewer"></div>
        <!-- 코멘트 입력하기 -->
        <hr />
        <div class="question-comment">
          <question-answer
            class="question-comment-info"
            v-for="(answer, index) in state.question.answerComment"
            :key="index"
            :answer="answer"
            :comment="state.question.coComment"
          ></question-answer>
          <div class="question-new-answer">
            <div class="question-new-answer-inner">
              <div class="question-new-answer-top">
                <div>댓글달기</div>
                <div class="new-answer-btns">
                  <div class="new-answer-btn" @click="onReset()">초기화</div>
                  <div class="new-answer-btn" @click="onSubmit()">저장</div>
                </div>
              </div>
              <div class="question-new-answer-bottom">
                <textarea
                  class="question-new-answer-bottom-content"
                  v-model="state.newAnswer"
                  @keydown.enter="resize()"
                  @keyup.enter="resize()"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/styles/board.scss";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import Viewer from "@toast-ui/editor/dist/toastui-editor-viewer";
import QuestionAnswer from "./components/answer.vue";

import { computed, reactive, onUpdated, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
import { useQuasar } from "quasar";

export default {
  name: "board-question",
  components: {
    QuestionAnswer,
  },
  data() {
    return {
      answer: "",
    };
  },
  methods: {
    resize() {
      let object = document.querySelector(
        ".question-new-answer-bottom-content"
      );
      console.log("resize", object);
      object.style.height = 12 + object.scrollHeight + "px";
    },
  },
  setup() {
    const route = useRoute();
    const store = useStore();
    const router = useRouter();
    const quasar = useQuasar();

    const state = reactive({
      newAnswer: "",
      error: "",
      question: null,
      viewer: null,
      user: computed(() => JSON.parse(localStorage.getItem("userInfo"))),
    });

    if (localStorage.getItem("reload")) {
      localStorage.removeItem("reload");
      router.go();
    }

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

    const getQuestion = () => {
      store
        .dispatch("root/requsetBoardInfo", route.params.id)
        .then(
          (response) => {
            console.log(response.data);
            state.question = response.data;
          },
          (error) => {
            state.error = error.response.data.message;
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };
    getQuestion();

    onUpdated(() => {
      state.viewer = new Viewer({
        el: document.querySelector("#viewer"),
        initialValue: state.question.description,
        height: document.querySelector(".question-info").clientHeight,
      });
    });

    const onSubmit = () => {
      console.log(state.newAnswer);
      store
        .dispatch("root/requestWriteComment", {
          content: state.newAnswer,
          userId: localStorage.getItem("userId"),
          parentId: 0,
          boardId: route.params.id,
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error.response.data);
        });
      router.go();
    };

    const onReset = () => {
      state.newAnswer = "";
      let object = document.querySelector(
        ".question-new-answer-bottom-content"
      );
      object.style.height = "2em";
    };

    const deleteBoard = () => {
      store
        .dispatch("root/requestDelete", route.params.id)
        .then((response) => {
          if (response.data == "success") {
            boardDeleteSuccess();
          }
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    };
    const boardDeleteSuccess = () => {
      quasar
        .dialog({
          title: "게시판 삭제",
          message: "삭제되었습니다.",
        })
        .onOk(() => {
          console.log("OK");
          localStorage.setItem("reload", true);
          router.push({ name: "board-info" });
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };

    return {
      state,
      onMounted,
      onSubmit,
      onReset,
      deleteBoard,
    };
  },
};
</script>
<style></style>
