<template lang="">
  <div class="question-list" @click="mvQuestion">
    <q-icon class="question-list-icon" name="auto_stories" />
    <div class="question-list-info">
      <div class="list-title">
        {{ question.title }}
      </div>
      <div class="list-createdAt">
        {{ question.createdAt }}
      </div>
      <div class="list-userinfo">
        <div class="list-user-image">
          <img class="user-image-circle" :src="question.user.image" />
        </div>
        <div class="list-user-nickName">
          {{ question.user.nickName }}
          <q-tooltip>{{ question.user.nickName }}</q-tooltip>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { useRouter } from "vue-router";

export default {
  name: "question-list",
  props: {
    question: Object,
  },
  setup(props) {
    const router = useRouter();

    const mvQuestion = () => {
      router.push({ path: `/home/board/question/${props.question.id}` });
    };
    return {
      useRouter,
      mvQuestion,
    };
  },
};
</script>
<style></style>
