<template>
  <div class="right-wrap">
    <div class="inner-wrap">
      <div class="approve-title">문제리스트</div>
      <div class="approve-list-wrap">
        <Probs
          v-for="(prob, idx) in problems"
          :key="idx"
          :prob="prob"
          @send-accept="sendAccept"
          @send-revoke="sendRevoke"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { onBeforeMount, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
import Probs from "./components/problem.vue";

export default {
  name: "approve-right",
  components: {
    Probs,
  },
  setup() {
    const store = useStore();
    const router = useRouter();
    const quasar = useQuasar();
    const problems = ref([]);

    onBeforeMount(() => {
      getProblems();
    });

    const getProblems = () => {
      store
        .dispatch("root/requestProblemWait")
        .then((response) => {
          problems.value = response.data;
          console.log(problems.value);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const sendAccept = (data) => {
      store
        .dispatch("root/requestProblemEvaluate", data)
        .then((response) => {
          console.log(response);
          evaluateAccept();
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const sendRevoke = (data) => {
      store
        .dispatch("root/requestProblemEvaluate", data)
        .then((response) => {
          console.log(response);
          evaluateRevoke();
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const evaluateAccept = () => {
      quasar
        .dialog({
          title: "문제 승인 처리",
          message: "문제가 승인되었습니다.",
        })
        .onOk(() => {
          console.log("OK");
          router.go();
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const evaluateRevoke = () => {
      quasar
        .dialog({
          title: "문제 승인 처리",
          message: "문제 승인이 거절되었습니다.",
        })
        .onOk(() => {
          console.log("OK");
          router.go();
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    return {
      onBeforeMount,
      problems,
      sendAccept,
      sendRevoke,
      evaluateAccept,
      evaluateRevoke,
    };
  },
};
</script>
