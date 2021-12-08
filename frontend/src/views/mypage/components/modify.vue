<template>
  <div class="modify-wrap">
    <span class="modify-title"><b>프로필 수정하기</b></span>
    <div class="form-wrap">
      <div class="modify-image">
        <img class="modi-img" :src="state.imageurl" />
        <label for="chooseFile" class="upload-btn">사진변경</label>
        <input
          type="file"
          id="chooseFile"
          name="chooseFile"
          accept="image/*"
          style="display: none"
          @change="loadf"
        />
      </div>
    </div>
    <div class="submit-wrap">
      <q-btn
        class="subbtn"
        type="submit"
        @click="modify"
        style="background: #696969; color: #ffffff"
        label="수정하기"
      />
    </div>
  </div>
</template>
<script>
import { reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";

export default {
  name: "modify",
  setup() {
    onMounted(() => {
      document.getElementById("leftBtn1").classList.remove("click-myleft");
      document.getElementById("leftBtn2").classList.add("click-myleft");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.remove("click-menu");
      localStorage.removeItem("menu");
      localStorage.setItem("menu", "menuBtn4");
      document
        .getElementById(localStorage.getItem("menu"))
        .classList.add("click-menu");
    });
    const quasar = useQuasar();
    const router = useRouter();
    const store = useStore();
    const state = reactive({
      user: null,
      imageurl: null,
      file: null,
    });

    state.user = JSON.parse(localStorage.getItem("userInfo"));
    state.imageurl = state.user.image;

    /*ㅡㅡㅡㅡㅡ 버튼 ㅡㅡㅡㅡㅡ*/
    const loadf = () => {
      var file = document.getElementById("chooseFile");
      state.imageurl = URL.createObjectURL(file.files[0]);
    };

    const modify = () => {
      const formData = new FormData();
      const img = document.getElementById("chooseFile").files[0];
      formData.append("email", state.user.email);
      if (img == null) {
        profilePictureChange();
      } else {
        formData.append("image", img);
      }
      store
        .dispatch("root/requestUserModify", formData)
        .then((response) => {
          if (response.data.message === "Success") {
            profileChangeSuccess();
          }
          // router.push({ name: "mypage-chart" });
        })
        .catch((error) => {
          console.log(error);
        });
    };
    const profilePictureChange = () => {
      quasar
        .dialog({
          title: "프로필 사진",
          message: "이미지가 첨부되지 않아 기본이미지로 변경합니다.",
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
    const profileChangeSuccess = () => {
      quasar
        .dialog({
          title: "프로필 사진",
          message: "변경이 완료되었습니다.",
        })
        .onOk(() => {
          console.log("OK");
          store
            .dispatch("root/requsetUserInfo", state.user.token)
            .then((response) => {
              var userinfo = {
                id: response.data.id,
                name: response.data.name,
                nickname: response.data.nickname,
                email: response.data.email,
                image: response.data.image,
                token: state.user.token,
              };
              localStorage.removeItem("userInfo");
              localStorage.setItem("userInfo", JSON.stringify(userinfo));
              state.user = JSON.parse(localStorage.getItem("userInfo"));
              router.go();
            })
            .catch((error) => {
              console.log(error);
            });
        })
        .onCancel(() => {
          console.log("Cancel");
        })
        .onDismiss(() => {
          console.log("I am triggered on both OK and Cancel");
        });
    };

    return {
      onMounted,
      state,
      loadf,
      modify,
    };
  },
};
</script>
