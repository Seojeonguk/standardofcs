<template lang="">
  <q-dialog class="bookstyle-dialog" persistent>
    <div class="book">
      <div class="left-card-top row justify-end">
        <div class="left-card-top-left"></div>
        <div class="left-card-top-right"></div>
      </div>
      <div class="left-card-back">
        <div class="left-card">
          <div class="emailcheck-card-title">이메일 인증</div>
          <div class="emailcheck-card-left-info">
            <div>
              {{ email }}로<br />
              인증번호를 발송했습니다.
            </div>
            <div>
              최소1분~최대5분의<br />
              소요시간이 있습니다<br />
              기다려주세요
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
              <div>다시 요청하려면<br />재인증 버튼을 눌러주세요</div>
            </div>
            <div class="emailcheck-card-right-info-bottom">
              <q-input dense v-model="state.auth" label="인증번호 *"> </q-input>
              <div class="emailcheck-card-right-info-bottom-btns">
                <div class="emailcheck-card-btn" @click="emailAuthentication">
                  재요청
                </div>
                <div class="emailcheck-card-btn" @click="authentication">
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
import { reactive } from "vue";
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
      auth: "",
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
          state.auth = response.data.message;
          hideLoading();
          ResendAuthSuccess();
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const authentication = () => {
      console.log(props.authNumber, state.auth);
      if (props.authNumber === state.auth) {
        authSuccess();
      } else {
        authFail();
      }
    };
    /*ㅡㅡㅡㅡㅡ 다이얼로그 ㅡㅡㅡㅡㅡ*/
    const ResendAuthSuccess = () => {
      quasar
        .dialog({
          title: "인증번호 요청",
          message: "인증번호를 재요청 했습니다.",
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
    const authSuccess = () => {
      quasar
        .dialog({
          title: "이메일 인증",
          message: "인증에 성공하였습니다.",
        })
        .onOk(() => {
          emit("emailsuccess");
          state.auth = "";
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
    return {
      state,
      emailAuthentication,
      authentication,
      /* 다이얼로그 */
      authSuccess,
      authFail,
      ResendAuthSuccess,
      showLoading,
      hideLoading,
    };
  },
};
</script>
<style lang=""></style>
