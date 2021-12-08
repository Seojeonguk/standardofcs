<template lang="">
  <div class="comment">
    <div class="comment-user-image">
      <img :src="state.comment.user.image" class="comment-user-image-circle" />
    </div>
    <div class="comment-top">
      <div class="comment-user">
        <div class="comment-user-nickname">
          {{ state.comment.user.nickName }}
        </div>
        <div class="comment-user-createdAt">{{ state.comment.createdAt }}</div>
      </div>
      <div class="comment-bottom">
        <div class="comment-content">
          {{ state.comment.content }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/styles/question.scss";
import { reactive } from "vue";
export default {
  name: "question-answer-comment",
  props: {
    comment: Object,
  },
  setup(props) {
    const state = reactive({
      comment: null,
    });
    const setComment = () => {
      state.comment = props.comment;
    };
    setComment();
    return {
      state,
    };
  },
};
</script>
<style lang=""></style>
