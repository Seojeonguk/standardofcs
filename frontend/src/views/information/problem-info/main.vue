<template lang="">
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addProblemScript"
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
          <div
            class="
              column
              justify-center
              item-center
              q-mt-lg
              script-category script
            "
          >
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
            <div><b>왼쪽 카테고리에서 문제를 선택해주세요.</b></div>
            <div>
              <b>전체 카테고리 : 4개</b>
            </div>
            <div>
              <b>CSPS의 총 문제 개수 : 42개</b>
            </div>
            <div class="script-makeprob script">
              <div class="information-makeprob">문제만들기</div>
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
  name: "info-problem-main",
  setup() {
    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addProblemScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let make = document.querySelector(".script-makeprob");
      let category = document.querySelector(".script-category");
      if (state.add) {
        addBtn.classList.add("add-after");
        make.classList.remove("script");
        make.classList.add("add-script");
        make.addEventListener("click", () => {
          state.script =
            "직접 문제를 만들 수 있습니다. 문제는 관리자에 의해 승인되거나 거절될 수 있습니다.";
        });
        category.classList.remove("script");
        category.classList.add("add-script");
        category.addEventListener("click", () => {
          state.script = "카테고리를 선택하여 문제풀기를 시작할 수 있습니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        make.classList.remove("add-script");
        make.classList.add("script");
        category.classList.remove("add-script");
        category.classList.add("script");
      }
      state.add = !state.add;
    };
    return { state, addProblemScript };
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
.information-makeprob {
  font-size: 15px;
  padding: 0.8em 2em;
  border-radius: 2em;
  background-color: #696969;
  color: #dcdcdc;
  transition: all 0.7s ease-out;
  &:hover {
    background: none;
    transition: all 0.7s ease-out;
  }
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
