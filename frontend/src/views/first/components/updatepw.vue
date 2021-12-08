<template lang="">
  <q-dialog class="bookstyle-dialog" persistent>
    <div class="book">
      <div class="left-card-top row justify-end">
        <div class="left-card-top-left"></div>
        <div class="left-card-top-right"></div>
      </div>
      <div class="left-card-back">
        <div class="left-card">
          <div class="updatepw-card-title">새 비밀번호 설정</div>
          <div class="updatepw-card-left-info">
            영문, 숫자, 특수문자로<br />
            8자리 이상 비밀번호 설정
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
        <div class="right-card updatepw-right-card">
          <q-btn
            class="updatepw-card-close-btn self-end"
            v-close-popup
            flat
            round
            dense
            icon="close"
          />
          <div class="updatepw-card-right-info">
            <q-form ref="updatepw_form">
              <q-input
                dense
                v-model="state.form.pass"
                :rules="state.rules.pass"
                type="password"
                label="비밀번호 *"
              />

              <q-input
                dense
                v-model="state.form.passcheck"
                :rules="state.rules.passcheck"
                type="password"
                label="비밀번호 확인 *"
              />
              <div class="update-card-right-info-bottom-btns">
                <div class="update-card-right-info-bottom-btn" @click="onReset">
                  초기화
                </div>
                <div
                  class="update-card-right-info-bottom-btn"
                  @click="onSubmit"
                >
                  변경
                </div>
              </div>
            </q-form>
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
import "@/styles/updatepw.scss";
import { ref, reactive } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";
export default {
  name: "login-updatepw",
  props: {
    email: String,
    authNumber: String,
  },
  setup(props, { emit }) {
    const updatepw_form = ref(null);
    const store = useStore();
    const quasar = useQuasar();
    const state = reactive({
      form: {
        pass: "",
        passcheck: "",
      },
      rules: {
        pass: [
          (val) => val !== null || "필수입력 항목입니다.",
          (val) =>
            isValidPass(val) ||
            "영문, 숫자, 특수문자를 포함한 8자리 이상의 비밀번호를 생성해주세요!",
        ],
        passcheck: [
          (val) => (val !== null && val.length > 0) || "필수입력 항목입니다.",
          (val) => isValidPassCheck(val) || "입력한 비밀번호와 맞지 않습니다.",
        ],
      },
    });

    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isValidPass = (val) => {
      const passPattern =
        /^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
      return passPattern.test(val);
    };
    const isValidPassCheck = (val) => {
      if (state.form.pass != val) {
        console.log(state.form.pass, val);
        return false;
      } else {
        return true;
      }
    };
    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const onSubmit = () => {
      console.log(props.authNumber, state.form.pass);
      updatepw_form.value.validate().then((success) => {
        if (success) {
          store
            .dispatch("root/requestUserFixPw", {
              key: props.authNumber,
              pass: state.form.pass,
            })
            .then(
              (response) => {
                console.log(response);
                fixpwSuccess();
              },
              (error) => {
                console.log(error);
              }
            )
            .catch((error) => {
              console.log(error);
            });
        }
      });
    };
    const onReset = () => {
      state.form.pass = null;
      state.form.passcheck = null;
    };
    /*ㅡㅡㅡㅡㅡ 다이얼로그 ㅡㅡㅡㅡㅡ*/
    const fixpwSuccess = () => {
      quasar
        .dialog({
          title: "새 비밀번호 설정",
          message: "비밀번호가 변경되었습니다!",
        })
        .onOk(() => {
          onReset();
          emit("mvlogin");
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };

    return {
      updatepw_form,
      state,
      onSubmit,
      onReset,
    };
  },
};
</script>
<style lang=""></style>
