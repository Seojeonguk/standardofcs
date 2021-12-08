<template lang="">
  <div style="overflow: auto; width: 100%; height: 100%">
    <teamplate
      v-for="(category, index) in state.problemResults"
      :key="index"
      unelevated
    >
      <h4>
        <b>{{ index + 1 }}. {{ state.problems[index].description }}</b>
      </h4>
      <div class="row">
        <div class="col" style="margin-left: 155px">
          <h6 data-aos="fade-up"><b>정답율</b></h6>
          <apexchart
            width="340"
            :options="chartOptions"
            type="donut"
            :series="state.series[index]"
          />
        </div>
        <div class="col" style="margin: 85px">
          <h5 style="color: blue" data-aos="fade-up"><b>언급 단어</b></h5>
          <teamplate v-for="item in category.proper" v-bind:key="item">
            <b>{{ item }}</b>
            &nbsp;
          </teamplate>
          <h5 style="color: green"><b>미언급 단어</b></h5>
          <teamplate v-for="item in category.wrong" v-bind:key="item">
            <b>{{ item }}</b>
            &nbsp;
          </teamplate>
        </div>
      </div>
    </teamplate>
    <br />
    <q-btn unelevated flat @click="save()">저장하기</q-btn>
    <q-btn unelevated flat @click="back()">뒤로가기</q-btn>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
export default {
  name: "problem-result",
  data() {
    return {
      chartOptions: {
        chart: {
          width: 380,
          type: "pie",
        },
        labels: ["언급", "미언급"],
        responsive: [
          {
            breakpoint: 480,
            options: {
              chart: {
                width: 200,
              },
              legend: {
                position: "bottom",
              },
            },
          },
        ],
      },
    };
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    const quasar = useQuasar();
    const state = reactive({
      problemResults: computed(() => store.getters["root/getProblemResults"]),
      problems: computed(() => store.getters["root/getSelectedProblems"]),
      series: computed(() => store.getters["root/getSeries"]),
      category: computed(() => store.getters["root/getSelectedCategory"]),
    });
    const back = () => {
      //console.log(this.state.problemResults);
      router.push({ name: "problem-info" });
    };
    const save = () => {
      let proper = 0;
      for (let i = 0; i < state.problemResults.length; i++) {
        if (
          state.problemResults[i].proper.length >=
          state.problemResults[i].wrong.length
        ) {
          proper++;
        }
      }
      let scoreTmp = parseInt((proper * 100) / state.problemResults.length);
      const payload = {
        category_id: state.category,
        score: scoreTmp,
      };
      store
        .dispatch("root/requestSaveResult", payload)
        .then((response) => {
          console.log(response);
          if (response.data.message == "Success") {
            saveSuccess();
          }
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    };

    const backToInfo = () => {
      store.commit("root/setProblemResultsInit");
      router.push({
        name: "problem",
      });
    };
    const saveSuccess = () => {
      quasar
        .dialog({
          title: "문제 풀기 결과",
          message:
            "저장이 완료되었습니다. 저장된 결과는 마이페이지에서 확인가능합니다.",
        })
        .onOk(() => {
          console.log("OK");
          // router.push({ name: "problem-info" });
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
      save,
      back,
      backToInfo,
    };
  },
};
</script>
<style></style>
