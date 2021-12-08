<template lang="">
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addDescriptionScript"
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
            <div>
              <b> Vue </b>
            </div>
            <div>
              <div>
                <b>
                  전체 문제 개수: 5
                  <vue3-autocounter
                    :startAmount="0"
                    :endAmount="5"
                    :duration="0.3"
                    :autoinit="true"
                  />
                </b>
              </div>
              <div>
                <b>React 시작전에 Vue 해볼래?</b>
              </div>
            </div>
            <div style="width: 100%" class="script-description script">
              <q-badge color="secondary">풀 문제 갯수: 5</q-badge>
              <q-slider :min="0" :max="5" markers label />
              <q-btn q-btn color="deep-orange" glossy label="시작하기"></q-btn>
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
  name: "info-problem-description",
  setup() {
    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addDescriptionScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let description = document.querySelector(".script-description");
      if (state.add) {
        addBtn.classList.add("add-after");
        description.classList.remove("script");
        description.classList.add("add-script");
        description.addEventListener("click", () => {
          state.script = "문제의 개수를 선택하여 시작할 수 있습니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        description.classList.remove("add-script");
        description.classList.add("script");
      }
      state.add = !state.add;
    };
    return { state, addDescriptionScript };
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
  font-size: 28px;

  & > div {
    margin: 1em;
  }
}
</style>
