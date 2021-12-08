<template>
  <q-dialog persistent>
    <div class="dialog-position">
      <div class="dialog-design">
        <div class="book">
          <div class="left-card-top row justify-end">
            <div class="left-card-top-left"></div>
            <div class="left-card-top-right"></div>
          </div>
          <div class="left-card-back">
            <div class="left-card"></div>
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
            <div class="right-card column">
              <q-btn
                class="self-end btnz"
                v-close-popup
                flat
                round
                dense
                icon="close"
              />
            </div>
          </div>
          <div class="right-card-bottom-back row">
            <div class="right-card-bottom row">
              <div class="right-card-bottom-left"></div>
              <div class="right-card-bottom-right"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="dialog-inner">
        <div>
          <div class="inner-wrap login-wrap">
            <div class="complate-content first">
              🎉회원가입이 완료되었습니다🎉
            </div>
            <div class="complate-content second">
              CS의 정석에 오신것을 환영합니다
            </div>
            <div class="finish-regist">
              <div class="finish-regist-btn-login" @click="mvLogin">
                로그인하기
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </q-dialog>
</template>
<script>
import "@/styles/complate.scss";
export default {
  name: "register-complete-dialog",
  setup(props, { emit }) {
    const mvLogin = () => {
      emit("mvlogin");
    };
    return {
      mvLogin,
    };
  },
};
</script>
<style lang=""></style>
