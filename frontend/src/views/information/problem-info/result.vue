<template>
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addResultScript"
      ></q-icon>
    </div>
    <div class="info-mini-wrap">
      <div class="left-wrap">
        <div class="open-left-wrap">
          <div>
            <q-btn flat><b>문제카테고리</b></q-btn>
          </div>
          <div class="s1_arrow" style="margin-bottom: 8px">
            <div class="scroll-arrow"></div>
            <div class="scroll-arrow"></div>
          </div>
          <div class="column justify-center item-center q-mt-lg">
            <q-btn unelevated flat><b>Vue</b> </q-btn>
            <q-btn unelevated flat><b>React</b> </q-btn>
            <q-btn unelevated flat><b>Java</b> </q-btn>
            <q-btn unelevated flat><b>JavaScript</b> </q-btn>
            <q-btn unelevated flat><b>데이터베이스</b> </q-btn>
            <q-btn unelevated flat><b>운영체제</b> </q-btn>
            <q-btn unelevated flat><b>네트워크</b> </q-btn>
            <q-btn unelevated flat><b>보안</b> </q-btn>
            <q-btn unelevated flat><b>알고리즘</b> </q-btn>
          </div>
        </div>
      </div>
      <div class="right-wrap">
        <div class="open-right-wrap">
          <div class="information-problem-right-wrap">
            <div style="margin: 1em 0 0 0">
              <b
                >1.<br />
                Vue에 대한 설명을 해주세요</b
              >
            </div>
            <div class="row">
              <div class="col script-result script" style="margin-left: 55px">
                <div><b>정답율</b></div>
                <apexchart
                  width="200"
                  :options="chartOptions"
                  type="donut"
                  :series="chartOptions.series"
                />
              </div>
              <div class="col script-mention script" style="margin: 0 35px">
                <div style="color: blue">
                  <b>언급 단어</b>
                </div>
                <b>어플리케이션 자바프레임워크</b>
                <div style="color: green"><b>미언급 단어</b></div>
                <b>뷰 프로그레시브</b>
              </div>
            </div>
            <br />
            <div class="row justify-center">
              <q-btn unelevated flat>저장하기</q-btn>
              <q-btn unelevated flat>뒤로가기</q-btn>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="info-script">{{ state.script }}</div>
  </div>
</template>
<script>
import { reactive } from "vue";
export default {
  name: "info-problem-result",
  setup() {
    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addResultScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let result = document.querySelector(".script-result");
      let mention = document.querySelector(".script-mention");
      if (state.add) {
        addBtn.classList.add("add-after");
        result.classList.remove("script");
        result.classList.add("add-script");
        result.addEventListener("click", () => {
          state.script =
            "언급 및 미언급단어로 분류된 문제의 정답율을 볼 수 있습니다.";
        });
        mention.classList.remove("script");
        mention.classList.add("add-script");
        mention.addEventListener("click", () => {
          state.script =
            "언급 및 미언급단어는 정답에 해당되는 단어만 표기됩니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        result.classList.remove("add-script");
        result.classList.add("script");
        mention.classList.remove("add-script");
        mention.classList.add("script");
      }
      state.add = !state.add;
    };
    return { state, addResultScript };
  },
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
        series: [50, 50],
      },
    };
  },
};
</script>
<style lang="scss">
.s1_arrow {
  display: inline-block;
  position: relative;
  top: 30px;
  margin: -35px;
  text-align: center;
  animation: arrow_down 2.5s infinite;
}
.scroll-arrow {
  width: 10px;
  height: 10px;
  border-right: 4px solid gray;
  border-bottom: 4px solid gray;
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
  -moz-transform: rotate(45deg);
  -o-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  animation: arrow-wave 1s infinite;
  animation-direction: alternate;
}
.information-problem-right-wrap {
  display: flex;
  height: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 16px;

  & > div {
    margin: 1em;
  }
}
</style>
