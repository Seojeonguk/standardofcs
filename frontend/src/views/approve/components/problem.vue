<template>
  <div class="approve-wrap">
    <div class="approve-problem">
      <div class="top-wrap">
        <span>{{ prob.description }}</span>
        <span> {{ prob.user }}</span>
      </div>
      <div class="bottom-wrap">
        <div class="ans-wrap">
          답 :
          <span v-for="(an, idx) in prob.answers" :key="idx" class="anss">
            {{ an }}
          </span>
        </div>
        <div class="btn-wrap">
          <button @click="sendAccept">승인</button>
          <button @click="sendRevoke">거절</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    prob: {
      type: Array,
    },
  },
  setup(props, { emit }) {
    const sendAccept = () => {
      emit("send-accept", {
        id: props.prob.id,
        problemStatus: "accept",
      });
    };

    const sendRevoke = () => {
      emit("send-revoke", {
        id: props.prob.id,
        problemStatus: "revoke",
      });
    };

    return {
      sendAccept,
      sendRevoke,
    };
  },
};
</script>
