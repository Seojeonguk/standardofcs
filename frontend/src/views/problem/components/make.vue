<template>
  <div class="makeProb-wrap">
    <div class="makeProb-title" data-aos="fade-up">
      <h2><b>문제만들기</b></h2>
    </div>
    <div class="">
      <form class="makeProb-form" v-on:submit.prevent autocomplete="off">
        <div>
          <label class="label" for="category">문제 카테고리</label>
          <select v-model="state.problem.categoryId" id="category">
            <option
              v-for="(category, idx) in state.categories"
              :key="idx"
              :value="category.id"
            >
              {{ category.name }}
            </option>
          </select>
        </div>
        <div>
          <label class="label" for="desc">문제</label>
          <input id="desc" v-model="state.problem.description" />
        </div>
        <div class="answer-wrap">
          <label class="label" for="answerinput">정답</label>
          <div>
            <div>
              <input
                id="answerinput"
                v-model="temp"
                @keydown.enter.prevent="addInput"
              />
              <!-- <input
                class="addBtn"
                type="button"
                value="추가"
                @click="addInput"
              /> -->
              <q-btn
                flat
                class="subbtn"
                type="submit"
                @click="addInput"
                color="brown-5"
                label="추가"
              />
            </div>
            <div
              v-for="(answer, index) in answers"
              :key="answer.id"
              class="answer"
            >
              <div class="ans-wrap">
                {{ answer.ans }}
                <button @click="removeAns(index)">삭제</button>
              </div>
            </div>
          </div>
        </div>
      </form>
      <q-btn
        class="subbtn"
        type="submit"
        @click="makeProblem"
        style="background: #696969; color: #ffffff"
        label="생성하기"
      />
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    const quasar = useQuasar();
    const temp = ref("");
    const answers = ref([]);
    const state = reactive({
      categories: store.getters["root/getCategories"],
      problem: {
        userId: null,
        categoryId: null,
        description: null,
        answers: [],
      },
    });

    const addInput = () => {
      if (temp.value == "") {
        makeAnswerError();
      } else {
        answers.value.push({
          id: Date.now(),
          ans: temp.value,
        });
        console.log(answers.value);
        temp.value = "";
      }
    };

    const removeAns = (index) => {
      answers.value.splice(index, 1);
    };

    const makeProblem = () => {
      if (state.problem.categoryId == null) {
        makeCategoryError();
      } else if (state.problem.description == null) {
        makeDescriptionError();
      } else if (
        state.problem.description.length <= 4 ||
        state.problem.description.length > 400
      ) {
        makeProblemLengthError(state.problem.description.length);
      } else if (answers.value.length == 0) {
        if (temp.value == "") {
          makeCorrectError("정답 입력은 필수입니다.");
        } else {
          makeCorrectError("정답 추가 버튼을 눌러주세요");
        }
      } else {
        state.problem.userId = JSON.parse(localStorage.getItem("userInfo")).id;
        for (var i = 0; i < answers.value.length; i++) {
          state.problem.answers.push(answers.value[i].ans);
        }
        store
          .dispatch("root/requsetProblemCreate", state.problem)
          .then((response) => {
            console.log(response);
            makeProblemSuccess();
          })
          .catch((err) => {
            console.log(err);
          });
      }
    };
    const makeAnswerError = () => {
      quasar
        .dialog({
          title: "문제 만들기",
          message: "답을 입력해주세요.",
        })
        .onOk(() => {
          console.log("OK");
        });
    };
    const makeCategoryError = () => {
      quasar
        .dialog({
          title: "문제 만들기",
          message: "카테고리를 지정해주세요.",
        })
        .onOk(() => {
          console.log("OK");
        });
    };
    const makeDescriptionError = () => {
      quasar
        .dialog({
          title: "문제 만들기",
          message: "문제설명은 필수입니다.",
        })
        .onOk(() => {
          console.log("OK");
        });
    };
    const makeProblemLengthError = (val) => {
      quasar
        .dialog({
          title: "문제 만들기",
          message:
            "문제 길이는 5글자 이상 400글자 이하입니다. 현재 글자는" +
            val +
            "글자입니다",
        })
        .onOk(() => {
          console.log("OK");
        });
    };
    const makeCorrectError = (val) => {
      quasar
        .dialog({
          title: "문제 만들기",
          message: val,
        })
        .onOk(() => {
          console.log("OK");
        });
    };
    const makeProblemSuccess = () => {
      quasar
        .dialog({
          title: "문제 만들기",
          message: "문제 생성이 완료되었습니다. 승인 후 등록됩니다.",
        })
        .onOk(() => {
          console.log("OK");
          state.problem.categoryId = null;
          state.problem.description = null;
          state.problem.answers = [];
          temp.value = "";
          answers.value = [];
          document.getElementById("answerinput").value = "";
          store.dispatch("root/requestAllProblem").then((response) => {
            store.commit("root/setAllProblemNum", response.data);
          });
          router.push({ name: "problem-info" });
        });
    };

    return {
      temp,
      answers,
      state,
      makeProblem,
      addInput,
      removeAns,
    };
  },
};
</script>
