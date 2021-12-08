<template lang="">
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addSolveScript"
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
          <div class="information-problem-solve-right-wrap">
            <div class="information-solve-title">
              <b>
                1.<br />
                Vue에 대한 설명을 해주세요
              </b>
            </div>
            <div
              style="width: 125px; height: 125px"
              class="row justify-center items-center script-mike script"
            >
              <q-btn
                id="menuBtn1"
                round
                @click="startSpeechToTxt"
                color="red"
                class="speech-to-text"
              >
                <q-avatar size="120px">
                  <img src="https://i.ibb.co/qrtvzG3/mike.jpg" />
                </q-avatar>
              </q-btn>
            </div>

            <div class="information-solve-timer">0.00</div>

            <div class="script-usersay script">
              <div class="information-solve-usersay">
                <b
                  >웹 애플리케이션의 사용자 인터페이스를 만들기 위해 사용하는
                  오픈 소스 자바스크립트 프레임워크이다
                </b>
              </div>
            </div>

            <q-btn unelevated flat><b>Next</b></q-btn>
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
  name: "info-problem-solve",
  setup() {
    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addSolveScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let mike = document.querySelector(".script-mike");
      let usersay = document.querySelector(".script-usersay");
      if (state.add) {
        addBtn.classList.add("add-after");
        mike.classList.remove("script");
        mike.classList.add("add-script");
        mike.addEventListener("click", () => {
          state.script =
            "마이크를 눌러 음성으로 문제를 풀 수 있습니다. 마이크는 음성이 끊겼다 판단되었을 때 자동으로 중지합니다.";
        });
        usersay.classList.remove("script");
        usersay.classList.add("add-script");
        usersay.addEventListener("click", () => {
          state.script =
            "내 말이 정확히 입력되었는지 확인할 수 있습니다. 정확하지않다면, 마이크버튼을 다시눌러 재입력 할 수 있습니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        mike.classList.remove("add-script");
        mike.classList.add("script");
        usersay.classList.remove("add-script");
        usersay.classList.add("script");
      }
      state.add = !state.add;
    };
    return { state, addSolveScript };
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
.information-problem-solve-right-wrap {
  display: flex;
  height: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 28px;
}
.information-solve-title {
}
.information-speech-to-text {
}
.information-solve-timer {
  font-size: 10px;
}
.information-solve-usersay {
  font-size: 15px;
}
</style>
