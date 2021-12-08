<template lang="">
  <div class="question-answer">
    <div class="answer">
      <div class="answer-user-image">
        <img :src="state.answer.user.image" class="answer-user-image-circle" />
      </div>
      <div class="answer-top">
        <div class="answer-user">
          <div class="answer-user-nickname">
            {{ state.answer.user.nickName }}
          </div>
          <div class="answer-user-createdAt">
            {{ state.answer.createdAt }}
          </div>
        </div>
        <div class="answer-bottom">
          <div class="answer-content">
            {{ state.answer.content }}
          </div>
          <div v-if="state.comment != null" class="row justify-center">
            <answer-comment
              v-for="(comment, index) in state.comment"
              :key="index"
              :comment="comment"
            ></answer-comment>
            <div class="answer-new-comment">
              <div class="answer-new-comment-inner">
                <div class="answer-new-comment-top">
                  <div>댓글달기</div>
                  <div class="new-comment-btns">
                    <div class="new-comment-btn" @click="onReset()">초기화</div>
                    <div class="new-comment-btn" @click="onSubmit()">저장</div>
                  </div>
                </div>
                <div class="answer-new-comment-bottom">
                  <textarea
                    class="answer-new-comment-bottom-content"
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
  </div>
</template>
<script>
import "@/styles/question.scss";
import AnswerComment from "./components/comment.vue";

import { reactive } from "vue";
import { useStore } from "vuex";
import { useRouter, useRoute } from "vue-router";
export default {
  name: "question-answer",
  components: {
    AnswerComment,
  },
  props: {
    answer: Object,
    comment: Array,
  },
  data() {
    return {
      newAnswer: "",
    };
  },
  methods: {
    resize() {
      let object = document.querySelector(".answer-new-comment-bottom-content");
      console.log("resize", object);
      object.style.height = 12 + object.scrollHeight + "px";
    },
  },
  setup(props) {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const state = reactive({
      newAnswer: "",
      answer: null,
      comment: null,
    });
    const setAnswer = () => {
      state.answer = props.answer;
    };
    setAnswer();
    const setComment = () => {
      let comment = props.comment;
      state.comment = comment.filter(
        (comment) => state.answer.id == comment.parentId
      );
      console.log(state.comment);
    };
    setComment();

    const onSubmit = () => {
      console.log(state.newAnswer);
      store
        .dispatch("root/requestWriteComment", {
          content: state.newAnswer,
          userId: localStorage.getItem("userId"),
          parentId: state.answer.id,
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
      let object = document.querySelector(".answer-new-comment-bottom-content");
      object.style.height = "2em";
    };
    return {
      state,
      onSubmit,
      onReset,
    };
  },
};
</script>
<style lang=""></style>
