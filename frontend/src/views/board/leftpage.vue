<template>
  <div class="left-wrap">
    <div class="open-left-wrap">
      <div class="board-nav">
        <div class="board-btn">
          <div class="btn-board-info" @click="mvInfo">게시판소개</div>
          <div class="row">
            <div class="btn-board-write" @click="mvWrite">
              <q-icon class="write-icon" name="border_color" /> 글작성
            </div>
          </div>
        </div>
        <div class="board-search">
          <q-input v-model="state.search_title" label="제목검색" dense>
            <template v-slot:before>
              <q-icon name="search" />
            </template>

            <template v-slot:append>
              <q-icon
                v-if="state.search_title !== ''"
                name="close"
                @click="state.search_title = ''"
              />
            </template>
          </q-input>
        </div>
        <div class="board-title-list" ref="scrollTargetRef">
          <q-infinite-scroll
            @load="load"
            :offset="25"
            :scroll-target="scrollTargetRef"
          >
            <question-list
              class="board-title-list-item"
              v-for="(question, index) in question_list"
              :key="index"
              :question="question"
            />
            <template v-if="state.search_title == ''" v-slot:loading>
              <div class="row justify-center q-my-md">
                <q-spinner-dots color="primary" size="40px" />
              </div>
            </template>
          </q-infinite-scroll>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/styles/board.scss";
import { ref, reactive, onMounted, watch } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import QuestionList from "./components/questionlist.vue";

export default {
  name: "board-left",
  components: { QuestionList },
  setup() {
    /*
    quest_list :: 현재 담겨있는 정보들
    state.quest_list :: 담겨진 모든 정보들
    state.quest_size :: 현재 잘라서 담아온 정보 사이즈
     */
    const scrollTargetRef = ref(null);
    const question_list = ref([]);
    const store = useStore();
    const router = useRouter();
    const state = reactive({
      word: "",
      search_title: "",
      question_list: [],
      question_size: 0,
    });
    const mvInfo = () => {
      router.push({ name: "board-info" });
    };
    const mvWrite = () => {
      router.push({ name: "board-write" });
    };

    watch(
      () => state.search_title,
      (search) => {
        if (search == "") {
          question_list.value = state.question_list.slice(
            0,
            state.question_size
          );
        } else {
          let list = state.question_list;
          question_list.value = list.filter((list) =>
            list.title.includes(search)
          );
        }
      }
    );

    onMounted(() => {
      store
        .dispatch("root/requestBoardList")
        .then(
          (response) => {
            state.question_list = response.data;
          },
          (error) => {
            console.log(error.response.data);
          }
        )
        .catch((error) => {
          console.log(error);
        });
    });

    const load = (index, done) => {
      setTimeout(() => {
        var size = state.question_size;
        var max_size = state.question_list.length;
        if (size + 10 <= max_size) {
          question_list.value.push(
            ...state.question_list.slice(size, size + 10)
          );
          state.question_size += 10;
        } else {
          question_list.value.push(
            ...state.question_list.slice(size, max_size)
          );
          state.question_size = max_size;
          done(true);
        }
        done();
      }, 500);
    };
    return {
      scrollTargetRef,
      question_list,
      state,
      mvInfo,
      mvWrite,
      load,
    };
  },
};
</script>
<style></style>
