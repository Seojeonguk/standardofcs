<template>
  <q-dialog class="bookstyle-dialog" @hide="onReset" persistent>
    <div class="dialog-position-r">
      <div class="dialog-design-r">
        <div class="book-r">
          <div class="left-card-top-r row justify-end">
            <div class="left-card-top-left-r"></div>
            <div class="left-card-top-right-r"></div>
          </div>
          <div class="left-card-back-r">
            <div class="left-card-r"></div>
          </div>
          <div class="left-card-bottom-back-r row justify-end">
            <div class="left-card-bottom-r row">
              <div class="left-card-bottom-left-r"></div>
              <div class="left-card-bottom-right-r"></div>
            </div>
          </div>
        </div>
        <div class="book-r">
          <div class="right-card-top-r row">
            <div class="right-card-top-left-r"></div>
            <div class="right-card-top-right-r"></div>
          </div>
          <div class="right-card-back-r">
            <div class="right-card-r column">
              <q-btn
                class="self-end btnz-r"
                v-close-popup
                flat
                round
                dense
                icon="close"
              />
            </div>
          </div>
          <div class="right-card-bottom-back-r row">
            <div class="right-card-bottom-r row">
              <div class="right-card-bottom-left-r"></div>
              <div class="right-card-bottom-right-r"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="dialog-inner-r">
        <div class="dialog-width">
          <div class="register-wrap">
            <q-form
              class="q-gutter-md form-wrap"
              ref="regist_form"
              @submit="onSubmit"
              @reset="onReset"
            >
              <q-input
                dense
                v-model="state.form.name"
                :rules="state.rules.name"
                lazy-rules
                label="성함 *"
              />
              <div class="horizontal">
                <q-input
                  dense
                  class="horizontal-l"
                  v-model="state.form.nickName"
                  :rules="state.rules.nickName"
                  label="닉네임 *"
                />
                <div
                  @click="nickNameCheck"
                  class="
                    auth-nickname
                    register-btns
                    horizontal-r
                    disabled-check
                  "
                >
                  중복확인
                </div>
              </div>

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
              <div class="horizontal">
                <q-input
                  dense
                  class="horizontal-l"
                  v-model="state.form.email"
                  :rules="state.rules.email"
                  lazy-rules
                  type="email"
                  label="이메일 *"
                />
                <div
                  class="auth-email register-btns horizontal-r disabled-check"
                  @click="emailCheck"
                >
                  인증하기
                </div>
              </div>
              <div class="submit-btns">
                <q-btn
                  type="submit"
                  rounded
                  style="background: #495057; color: #ffffff"
                  label="회원가입"
                />
                <q-btn
                  class="q-ml-sm"
                  rounded
                  flat
                  style="color: #495057"
                  type="reset"
                  label="초기화"
                />
              </div>
            </q-form>
          </div>
          <complete-dialog
            v-model="state.regist_complete"
            @mvlogin="mvLogin"
          ></complete-dialog>
          <email-dialog
            v-model="state.email.dialog"
            :email="state.email.email"
            :authNumber="state.email.authNumber"
            @emailsuccess="emailSuccess"
          ></email-dialog>
        </div>
      </div>
    </div>
  </q-dialog>
</template>

<script>
import "@/styles/registdialog.scss";
import { ref, reactive, watch } from "vue";
import { useStore } from "vuex";
import { useQuasar } from "quasar";

import CompleteDialog from "./complete-dialog.vue";
import EmailDialog from "./email.vue";

export default {
  name: "register-right",
  components: {
    CompleteDialog,
    EmailDialog,
  },
  setup(props, { emit }) {
    const regist_form = ref(null);
    const store = useStore();
    const quasar = useQuasar();
    const state = reactive({
      regist_complete: false,
      email: {
        dialog: false,
        email: "",
        authNumber: "",
      },
      form: {
        name: "",
        nickName: "",
        nickName_yet: "",
        nickName_success: false,
        pass: "",
        passcheck: "",
        email: "",
        email_yet: "",
        email_success: false,
      },
      rules: {
        name: [
          (val) => val != null || "필수입력 항목입니다.",
          (val) => val.length > 0 || "필수입력 항목입니다.",
        ],
        nickName: [
          (val) => isDisabledNickName(val),
          (val) => val != null || "필수입력 항목입니다.",
          (val) =>
            (val.length >= 2 && val.length <= 16) ||
            "2~16자리의 닉네임을 설정해주세요.",
        ],
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
        email: [
          (val) => isDisabledEmail(val),
          (val) => (val !== null && val.length > 0) || "필수입력 항목입니다.",
          (val) => isValidEmail(val) || "이메일형식에 맞지 않습니다.",
        ],
      },
    });
    /*ㅡㅡㅡㅡㅡ 검증 ㅡㅡㅡㅡㅡ*/
    const isDisabledNickName = (val) => {
      let auth_nickname = document.querySelector(".auth-nickname");
      if (val !== null && val.length >= 2 && val.length <= 16) {
        auth_nickname.classList.remove("disabled-check");
      } else {
        auth_nickname.classList.add("disabled-check");
      }
    };
    const isValidEmail = (val) => {
      // eslint-disable-next-line
      const emailPattern = /^[a-zA-Z0-9_]+@[a-zA-Z]+\.[a-zA-Z]{2,4}$/;
      return emailPattern.test(val);
    };
    const isDisabledEmail = (val) => {
      let auth_email = document.querySelector(".auth-email");
      if (val !== null && val.length > 0 && isValidEmail(val)) {
        auth_email.classList.remove("disabled-check");
      } else {
        auth_email.classList.add("disabled-check");
      }
    };
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
    watch(
      () => [state.form.nickName],
      () => {
        console.log(state.form.nickName, state.form.nickName_yet);
        if (state.form.nickName_yet != state.form.nickName) {
          state.form.nickName_success = false;
        } else {
          state.form.nickName_success = true;
        }
      }
    );
    watch(
      () => [state.form.email],
      () => {
        console.log(state.form.email, state.form.email_yet);
        if (state.form.email_yet != state.form.email) {
          state.form.email_success = false;
        } else {
          state.form.email_success = true;
        }
      }
    );

    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const nickNameCheck = () => {
      if (state.form.nickName == null || state.form.nickName.length < 2) {
        nickNameError("닉네임을 입력해주세요.");
      } else {
        store
          .dispatch("root/requestUserNickNameCheck", state.form.nickName)
          .then(
            (response) => {
              console.log(response);
              state.form.nickName_yet = state.form.nickName;
              state.form.nickName_success = true;
              nickNameSuccess();
            },
            (error) => {
              console.log(error.response.data);
              nickNameError("중복된 닉네임입니다.");
            }
          )
          .catch((error) => {
            console.log(error);
          });
      }
    };

    const emailCheck = () => {
      showLoading();
      console.log("emailcheck", state.form.email);
      store
        .dispatch("root/requestUserCheckEmail", state.form.email)
        .then(
          (response) => {
            console.log(response);
            state.email.email = state.form.email;
            state.email.authNumber = response.data.message;
            state.email.dialog = true;
            hideLoading();
          },
          (error) => {
            console.log(error);
            if (error.response.status == "404") {
              emailError("이미 가입된 이메일입니다.");
              state.form.email = null;
            }
            hideLoading();
          }
        )
        .catch((error) => {
          console.log(error);
        });
    };
    const emailSuccess = () => {
      state.form.email_yet = state.form.email;
      state.email.dialog = false;
      state.form.email_success = true;
    };

    const onSubmit = () => {
      regist_form.value.validate().then((success) => {
        if (!state.form.nickName_success) {
          nickNameSuccessError();
        } else if (!state.form.email_success) {
          emailSuccessError();
        } else {
          if (success) {
            store
              .dispatch("root/requestUserRegist", {
                name: state.form.name,
                nickName: state.form.nickName,
                pass: state.form.pass,
                email: state.email.email,
              })
              .then(
                (response) => {
                  console.log(response);
                  state.regist_complete = true;
                },
                (error) => {
                  console.log(error.response.data);
                  emailError(error.response.data.message);
                }
              )
              .catch((error) => {
                console.log(error);
              });
          }
        }
      });
    };

    const onReset = () => {
      state.form.name = null;
      state.form.nickName = null;
      state.form.nickName_yet = "";
      state.form.nickName_success = false;
      state.form.pass = null;
      state.form.passcheck = null;
      state.form.email = null;
      state.form.email_success = false;
      state.email.email = "";
      state.email.authNumber = "";
    };

    /*ㅡㅡㅡㅡㅡ 다이얼로그 ㅡㅡㅡㅡㅡ*/
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
    const nickNameSuccess = () => {
      quasar
        .dialog({
          title: "닉네임 중복확인",
          message: "사용가능한 닉네임입니다!",
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
    const nickNameError = (msg) => {
      quasar
        .dialog({
          title: "닉네임 중복확인",
          message: msg,
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
    const emailError = (data) => {
      quasar
        .dialog({
          title: "이메일 중복",
          message: data,
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
    const nickNameSuccessError = () => {
      quasar
        .dialog({
          title: "필수사항!",
          message: "닉네임 중복체크가 필요합니다!",
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
    const emailSuccessError = () => {
      quasar
        .dialog({
          title: "필수사항!",
          message: "이메일 인증이 필요합니다!",
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

    /*ㅡㅡㅡㅡㅡ MoVe ㅡㅡㅡㅡㅡ*/
    const mvLogin = () => {
      state.regist_complete = false;
      onReset();
      emit("mvlogin");
    };

    return {
      regist_form,
      state,
      /* 버튼 */
      onSubmit,
      onReset,
      nickNameCheck,
      emailCheck,
      emailSuccess,
      /* 다이얼로그 */
      showLoading,
      hideLoading,
      nickNameSuccess,
      nickNameError,
      nickNameSuccessError,
      emailError,
      emailSuccessError,
      /* move */
      mvLogin,
    };
  },
};
</script>
