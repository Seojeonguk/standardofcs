<template>
  <div class="board-write">
    <q-input
      class="board-write-form"
      v-model="state.title"
      counter
      maxlength="125"
      label="제목"
    ></q-input>
    <div class="board-write-editor" ref="ref_editor"></div>
    <div class="board-write-btn">
      <div @click="writeBoard">작성</div>
    </div>
    <div id="viewer"></div>
  </div>
</template>

<script>
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";

import Editor from "@toast-ui/editor";
import Viewer from "@toast-ui/editor/dist/toastui-editor-viewer";

import { ref, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";

export default {
  name: "board-write",
  setup() {
    const store = useStore();
    const quasar = useQuasar();
    const state = reactive({
      title: "",
      editor: null,
      viewer: null,
    });

    const ref_editor = ref(null);
    const router = useRouter();

    onMounted(() => {
      state.editor = new Editor({
        el: ref_editor.value,
        height:
          document.getElementsByClassName("board-write-editor").clientHeight,
        initialEditType: "markdown",
        previewStyle: "vertical",
      });
      state.editor.addHook("addImageBlobHook", (blob, callback) => {
        const formData = new FormData();
        formData.append("multipartFile", blob);
        store
          .dispatch("root/requestImageSave", formData)
          .then((response) => {
            callback(response.data, "그림");
          })
          .catch((error) => {
            console.log(error.response.data);
          });
      });
    });
    const previewBoard = () => {
      state.viewer = new Viewer({
        el: document.querySelector("#viewer"),
        initialValue: state.editor.getMarkdown(),
      });
    };

    const writeBoard = () => {
      var editor_text = state.editor.getMarkdown();
      var user = JSON.parse(localStorage.getItem("userInfo"));
      if (state.title == "") {
        boardWriteError();
        return;
      }
      store
        .dispatch("root/requestBoardWrite", {
          userId: user.id,
          title: state.title,
          description: editor_text,
        })
        .then((response) => {
          console.log(response);
          boardWriteSuccess(response);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const uploadImage = async (blob) => {
      const formData = new FormData();
      formData.append("multipartFile", blob);
      let url = "이미지";
      await store
        .dispatch("root/requestImageSave", formData)
        .then((response) => {
          url = response.data;
          return url;
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    };

    const BoardWrite = () => {
      return (
        <Editor
          previewStyle="vertical"
          height="600px"
          hooks={{
            addImageBlobHook: (blob, callback) => {
              const img_url = uploadImage(blob);
              callback(img_url, "alt_text");
            },
          }}
        />
      );
    };
    const boardWriteError = () => {
      quasar
        .dialog({
          title: "게시판 작성",
          message: "제목이 입력되지 않았습니다.",
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

    const boardWriteSuccess = (response) => {
      quasar
        .dialog({
          title: "게시판 작성",
          message: "작성이 완료되었습니다.",
        })
        .onOk(() => {
          console.log("OK");
          const url = "/home/board/question/" + response.data.id;
          localStorage.setItem("reload", true);
          router.push({ path: url });
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
      ref_editor,
      onMounted,
      previewBoard,
      writeBoard,
      BoardWrite,
    };
  },
};
</script>
