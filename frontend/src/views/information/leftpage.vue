<template>
  <div class="left-wrap">
    <div class="open-left-wrap">
      <div class="information">
        <div class="information-cs">
          <div class="information-main-title" @click="mvInformation">
            CS의 정석
          </div>
        </div>
        <div class="information-mypage">
          <div class="information-main" @click="mvMypageMain">마이페이지</div>
        </div>
        <div class="information-board">
          <div class="information-main" @click="mvBoardMain">게시판</div>
          <div class="information-btn">
            <div class="information-btn-service" @click="mvBoardWrite">
              글쓰기
            </div>
            <div class="information-btn-service" @click="mvBoardQuestion">
              글내용
            </div>
          </div>
        </div>
        <div class="information-problem">
          <div class="information-main" @click="mvProblemMain">문제풀기</div>
          <div class="information-btn">
            <div class="information-btn-service" @click="mvProblemDescription">
              시작
            </div>
            <div class="information-btn-service" @click="mvProblemSolve">
              풀기
            </div>
            <div class="information-btn-service" @click="mvProblemResult">
              결과
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import "@/styles/information.scss";
import { useRouter } from "vue-router";
export default {
  name: "info-left",
  setup() {
    const router = useRouter();
    const mvInformation = () => {
      router.push({ name: "info-information" });
    };
    const mvMypageMain = () => {
      router.push({ name: "info-mypage-main" });
    };
    const mvBoardMain = () => {
      router.push({ name: "info-board-main" });
    };
    const mvBoardWrite = () => {
      router.push({ name: "info-board-write" });
    };
    const mvBoardQuestion = () => {
      router.push({ name: "info-board-question" });
    };
    const mvProblemMain = () => {
      router.push({ name: "info-problem-main" });
    };
    const mvProblemDescription = () => {
      router.push({ name: "info-problem-description" });
    };
    const mvProblemSolve = () => {
      router.push({ name: "info-problem-solve" });
    };
    const mvProblemResult = () => {
      router.push({ name: "info-problem-result" });
    };
    return {
      mvInformation,
      mvMypageMain,
      mvBoardMain,
      mvBoardWrite,
      mvBoardQuestion,
      mvProblemMain,
      mvProblemDescription,
      mvProblemSolve,
      mvProblemResult,
    };
  },
};
</script>
<style scope>
.information {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 100%;
  height: 100%;
  padding: 2em 1em 4em 2em;
  font-weight: bold;
}
.information-main-title {
  font-size: 36px;
  margin: 1em 0em;
}
.information-main {
  font-size: 28px;
}
.information-btn-service {
  font-size: 21px;
}
.information-main-title:hover,
.information-main:hover,
.information-btn-service:hover {
  cursor: pointer;
  transform: scale(1.05);
}
</style>
