<template lang="">
  <q-dialog class="bookstyle-dialog" persistent>
    <div class="book">
      <div class="left-card-top row justify-end">
        <div class="left-card-top-left"></div>
        <div class="left-card-top-right"></div>
      </div>
      <div class="left-card-back">
        <div class="left-card">
          <div class="findpw-card-title">비밀번호찾기</div>
          <div class="findpw-card-info">
            본인의<br />
            이메일을<br />
            적어주세요
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
        <div class="right-card findpw-right-card">
          <q-btn
            class="findpw-card-close-btn self-end"
            v-close-popup
            flat
            round
            dense
            icon="close"
          />
          <q-form class="findpw-card-form" ref="email_form">
            <q-input
              class="findpw-card-email"
              dense
              v-model="state.email"
              :rules="state.rules.email"
              lazy-rules
              type="email"
              label="이메일"
            />
            <div class="findpw-card-btns">
              <div class="findpw-card-btn" @click="emailAuthentication">
                인증요청
              </div>
            </div>
          </q-form>
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
import "@/styles/findpw.scss";
import { reactive } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";
export default {
  name: "login-findpw",
  setup(props, { emit }) {
    const store = useStore();
    const quasar = useQuasar();
    // const email_form = ref(null);

    const state = reactive({
      email: "",
      rules: {
        email: [
          (val) => (val != null && val !== "") || "필수입력 항목입니다.",
          (val) => isValidEmail(val) || "이메일형식에 맞지 않습니다.",
        ],
      },
    });
    const isValidEmail = (val) => {
      // eslint-disable-next-line
      const emailPattern = /^[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
      return emailPattern.test(val) || "이메일 형식에 맞지 않습니다.";
    };

    const emailAuthentication = () => {
      showLoading();
      store
        .dispatch("root/requestUserSendEmail", {
          email: state.email,
        })
        .then(
          (response) => {
            hideLoading();
            requsetAuth(response.data);
          },
          (error) => {
            if (error.response.data.status == "404") {
              emailNotFountError();
            } else if (state.email == null || state.email == "") {
              emailInputError();
            } else {
              emailError();
            }
            hideLoading();
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };
    const requsetAuth = (authNumber) => {
      console.log(state.email);
      emit("openemailcheck", {
        email: state.email,
        authNumber: authNumber.message,
      });
    };
    /* ㅡㅡㅡㅡㅡㅡㅡㅡ 다이얼로그 ㅡㅡㅡㅡㅡㅡㅡㅡ */
    const showLoading = () => {
      quasar.loading.show({
        message: "요청 중입니다",
        boxClass: "bg-grey-2 text-grey-9",
        spinnerColor: "#495057",
      });
    };
    const hideLoading = () => {
      quasar.loading.hide();
    };
    const emailError = () => {
      quasar
        .dialog({
          title: "이메일 인증",
          message: "예기치 못한 오류입니다. 잠시후 다시 시도해주세요.",
        })
        .onOk(() => {
          console.log("OK");
          state.email = null;
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const emailInputError = () => {
      quasar
        .dialog({
          title: "이메일 인증",
          message: "이메일을 입력해주세요.",
        })
        .onOk(() => {
          console.log("OK");
          state.email = null;
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };
    const emailNotFountError = () => {
      quasar
        .dialog({
          title: "이메일 인증",
          message: "존재하지 않는 이메일입니다.",
        })
        .onOk(() => {
          console.log("OK");
          state.email = null;
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
      emailAuthentication,
      requsetAuth,
      /*다이얼로그*/
      showLoading,
      hideLoading,
      emailError,
      emailInputError,
      emailNotFountError,
    };
  },
};
</script>
<style lang=""></style>
