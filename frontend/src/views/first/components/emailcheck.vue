<template lang="">
  <q-dialog class="bookstyle-dialog" persistent>
    <div class="book">
      <div class="left-card-top row justify-end">
        <div class="left-card-top-left"></div>
        <div class="left-card-top-right"></div>
      </div>
      <div class="left-card-back">
        <div class="left-card">
          <div class="emailcheck-card-title">비밀번호찾기</div>
          <div class="emailcheck-card-left-info">
            <div>
              {{ email }}로<br />
              인증번호를 발송했습니다.
            </div>
            <div>
              최소1분~최대5분의<br />
              소요시간이 있으니 기다려주세요
            </div>
          </div>
        </div>
      </div>
      <div class="left-card-bottom-back row justify-end">
        <div class="left-card-bottom row">
          <div class="left-card-bottom-left"></div>
          <div class="left-card-bottom-right"></div>
        </div>
      </div>
    </div>
    <div class="book">
      <div class="right-card-top row">
        <div class="right-card-top-left"></div>
        <div class="right-card-top-right"></div>
      </div>
      <div class="right-card-back">
        <div class="right-card emailcheck-right-card">
          <q-btn
            class="emailcheck-card-close-btn self-end"
            v-close-popup
            flat
            round
            dense
            icon="close"
          />
          <div class="emailcheck-card-right-info">
            <div class="emailcheck-card-right-info-top">
              <div>
                인증번호는<br />
                <span id="emailcheck-timer"></span> 뒤에<br />
                폐기됩니다.
              </div>
              <div>다시 요청하려면<br />아래버튼을 눌러주세요</div>
            </div>
            <div class="emailcheck-card-right-info-bottom">
              <q-input dense v-model="state.auth" label="인증번호 *"> </q-input>
              <div class="emailcheck-card-right-info-bottom-btns">
                <div
                  class="emailcheck-card-btn re-auth-btn disabled-check"
                  @click="emailAuthentication"
                >
                  재요청
                </div>
                <div
                  class="emailcheck-card-btn auth-btn"
                  @click="authentication"
                >
                  인증
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="right-card-bottom-back row">
        <div class="right-card-bottom row">
          <div class="right-card-bottom-left"></div>
          <div class="right-card-bottom-right"></div>
        </div>
      </div>
    </div>
  </q-dialog>
</template>
<script>
import "@/styles/logindialog.scss";
import "@/styles/emailcheck.scss";
import { reactive, watch } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";
export default {
  name: "login-emailcheck",
  props: {
    email: String,
    authNumber: String,
  },
  setup(props, { emit }) {
    const quasar = useQuasar();
    const store = useStore();
    const state = reactive({
      timer_content: "",
      auth: "",
      auth_yet: "",
    });
    const emailAuthentication = () => {
      showLoading();
      console.log(props.email);
      store
        .dispatch("root/requestUserSendEmail", {
          email: props.email,
        })
        .then((response) => {
          console.log(response.data);
          state.auth_yet = response.data.message;
          hideLoading();
          ResendAuthSuccess();
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const authentication = () => {
      console.log(props.authNumber, state.auth_yet, state.auth);
      if (state.auth_yet == "") {
        if (props.authNumber == state.auth) {
          authSuccess();
        } else {
          authFail();
        }
      } else {
        if (state.auth_yet == state.auth) {
          authSuccess();
        } else {
          authFail();
        }
      }
    };
    /*ㅡㅡㅡㅡㅡ 다이얼로그 ㅡㅡㅡㅡㅡ*/
    const authSuccess = () => {
      quasar
        .dialog({
          title: "이메일 인증",
          message: "인증에 성공하였습니다.",
        })
        .onOk(() => {
          emit("openupdatepw", state.auth);
          state.auth = "";
          state.auth_yet = "";
          state.timer_content = "";
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const authFail = () => {
      quasar
        .dialog({
          title: "이메일 인증",
          message: "인증번호가 올바르지 않습니다.",
        })
        .onOk(() => {
          console.log("OK");
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const ResendAuthSuccess = () => {
      quasar
        .dialog({
          title: "인증번호 요청",
          message: "인증번호를 재요청 했습니다.",
        })
        .onOk(() => {
          countDown("emailcheck-timer", 180);
          console.log("OK");
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const showLoading = () => {
      quasar.loading.show({
        message: "재요청 중입니다",
        boxClass: "bg-grey-2 text-grey-9",
        spinnerColor: "#495057",
      });
    };
    const hideLoading = () => {
      quasar.loading.hide();
    };
    const countDown = (id, time) => {
      var timer;
      var now = 0;
      function showRemaining() {
        now += 1;
        var distDt = time - now;
        if (distDt < 0) {
          clearInterval(timer);
          return;
        }
        var minutes = Math.floor(distDt / 60);
        var seconds = distDt % 60;
        console.log(minutes, seconds);
        state.timer_content = minutes + ":" + seconds;
        if (document.getElementById(id) == null) {
          clearInterval(timer);
          return;
        }
        document.getElementById(id).textContent = state.timer_content;
      }
      timer = setInterval(showRemaining, 1000);
    };
    watch(
      () => state.timer_content,
      () => {
        let re_auth_btn = document.querySelector(".re-auth-btn");
        let auth_btn = document.querySelector(".auth-btn");
        if (re_auth_btn != null && auth_btn != null) {
          if (state.timer_content == "0:0") {
            auth_btn.classList.add("disabled-check");
            re_auth_btn.classList.remove("disabled-check");
          } else {
            re_auth_btn.classList.add("disabled-check");
            auth_btn.classList.remove("disabled-check");
          }
        }
      }
    );

    watch(
      () => props.email,
      () => {
        if (props.email != "") {
          countDown("emailcheck-timer", 180);
        }
      }
    );
    return {
      state,
      emailAuthentication,
      authentication,
      ResendAuthSuccess,
      showLoading,
      hideLoading,
    };
  },
};
</script>
<style lang=""></style>
