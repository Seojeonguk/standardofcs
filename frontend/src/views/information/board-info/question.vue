<template>
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addQuestionScript"
      ></q-icon>
    </div>
    <div class="info-mini-wrap">
      <div class="left-wrap">
        <div class="open-left-wrap">
          <div class="information-board-nav">
            <div class="information-board-btn">
              <div class="information-btn-information-board-info">
                게시판소개
              </div>
              <div class="row">
                <div class="script-board-write script">
                  <div class="information-btn-board-write">
                    <q-icon
                      class="information-write-icon write-icon"
                      name="border_color"
                    />
                    글작성
                  </div>
                </div>
              </div>
            </div>
            <div class="information-board-search">
              <q-input label="제목검색" dense>
                <template v-slot:before>
                  <q-icon name="search" />
                </template>
              </q-input>
            </div>
            <div class="script-board-title-list script">
              <div class="information-board-title-list">
                <div class="information-board-title-list-item">
                  <div class="information-question-list">
                    <q-icon class="information-question-list-icon" />
                    <div class="information-question-list-info">
                      <div class="information-list-title">React 질문있어요</div>
                      <div class="information-list-createdAt">2021-11-12</div>
                      <div class="information-list-userinfo">
                        <div class="information-list-user-image">
                          <img
                            class="information-user-image-circle"
                            :src="require('@/assets/malang.png')"
                          />
                        </div>
                        <div class="information-list-user-nickName">
                          React뿌수기
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="information-question-list">
                    <q-icon class="information-question-list-icon" />
                    <div class="information-question-list-info">
                      <div class="information-list-title">
                        Vue3 는 Vue2 라이브러리 지원 안되나요?
                      </div>
                      <div class="information-list-createdAt">2021-11-01</div>
                      <div class="information-list-userinfo">
                        <div class="information-list-user-image">
                          <img
                            class="information-user-image-circle"
                            :src="require('@/assets/malang.png')"
                          />
                        </div>
                        <div class="information-list-user-nickName">지원</div>
                      </div>
                    </div>
                  </div>
                  <div class="information-question-list">
                    <q-icon class="information-question-list-icon" />
                    <div class="information-question-list-info">
                      <div class="information-list-title">
                        How to use join query in database
                      </div>
                      <div class="information-list-createdAt">2021-10-31</div>
                      <div class="information-list-userinfo">
                        <div class="information-list-user-image">
                          <img
                            class="information-user-image-circle"
                            :src="require('@/assets/malang.png')"
                          />
                        </div>
                        <div class="information-list-user-nickName">
                          디비장인
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="information-question-list">
                    <q-icon class="information-question-list-icon" />
                    <div class="information-question-list-info">
                      <div class="information-list-title">
                        docker환경설정좀 봐주세요
                      </div>
                      <div class="information-list-createdAt">2021-10-21</div>
                      <div class="information-list-userinfo">
                        <div class="information-list-user-image">
                          <img
                            class="information-user-image-circle"
                            :src="require('@/assets/malang.png')"
                          />
                        </div>
                        <div class="information-list-user-nickName">
                          배포실패자
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="information-question-list">
                    <q-icon class="information-question-list-icon" />
                    <div class="information-question-list-info">
                      <div class="information-list-title">
                        Fonrtend Javascript animation is not working
                      </div>
                      <div class="information-list-createdAt">2021-10-15</div>
                      <div class="information-list-userinfo">
                        <div class="information-list-user-image">
                          <img
                            class="information-user-image-circle"
                            :src="require('@/assets/malang.png')"
                          />
                        </div>
                        <div class="information-list-user-nickName">
                          Front Master
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="right-wrap">
        <div class="open-right-wrap">
          <div class="information-board-question">
            <div class="information-question-title">React 질문 있어요</div>
            <div class="information-question-createdAt">2021-11-12</div>
            <div class="information-question-user">
              <div class="information-question-user-image">
                <img
                  :src="require('@/assets/malang.png')"
                  class="information-question-user-image-circle"
                />
              </div>
              <div class="information-question-user-nickName">React뿌수기</div>
            </div>
            <hr />
            <div class="information-question-info">
              <div id="information-viewer">
                내가 죽일놈이지 뭐~ 우리가 코딩할때면~ 전부 리엑트 탓이지 뭐~
                마치 죄인인 것처럼~
              </div>
              <!-- 코멘트 입력하기 -->
              <hr />
              <div class="script-comment script">
                <div class="question-information-comment">
                  <div class="information-question-information-answer">
                    <div class="information-answer">
                      <div class="information-answer-user-image">
                        <img
                          :src="require('@/assets/malang.png')"
                          class="information-answer-user-image-circle"
                        />
                      </div>
                      <div class="information-answer-top">
                        <div class="information-answer-user">
                          <div class="information-answer-user-nickname">
                            React뿌수기
                          </div>
                          <div class="information-answer-user-createdAt">
                            2021-11-13
                          </div>
                        </div>
                        <div class="information-answer-bottom">
                          <div class="information-answer-content">
                            아무도 댓글 안달아 주시나요...?
                          </div>
                          <div class="row justify-center">
                            <div class="information-comment">
                              <div class="information-comment-user-image">
                                <img
                                  :src="require('@/assets/malang.png')"
                                  class="information-comment-user-image-circle"
                                />
                              </div>
                              <div class="information-comment-top">
                                <div class="information-comment-user">
                                  <div
                                    class="information-comment-user-nickname"
                                  >
                                    React한테 당한사람
                                  </div>
                                  <div
                                    class="information-comment-user-createdAt"
                                  >
                                    2021-11-13
                                  </div>
                                </div>
                                <div class="information-comment-bottom">
                                  <div class="information-comment-content">
                                    진짜 리엑트 너무 어려운 것같아요
                                    해결방법찾으면 공유 부탁드립니다.
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="info-script">{{ state.script }}</div>
  </div>
</template>
<script>
import { reactive } from "vue";
export default {
  name: "info-information-board-question",
  setup() {
    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addQuestionScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let comment = document.querySelector(".script-comment");
      if (state.add) {
        addBtn.classList.add("add-after");
        comment.classList.remove("script");
        comment.classList.add("add-script");
        comment.addEventListener("click", () => {
          state.script = "댓글 및 대댓글 작성이 가능합니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        comment.classList.remove("add-script");
        comment.classList.add("script");
      }
      state.add = !state.add;
    };
    return { state, addQuestionScript };
  },
};
</script>
<style lang="scss">
.information-board-question {
  text-align: start !important;
  height: 100%;
  padding: 2em 4em;
  font-size: 15px;

  hr {
    border-top: 1px double #696969;
  }

  .information-question-title {
    height: 15%;
    font-size: 28px;
    height: 10%;
    font-weight: bold;
    margin-top: 0.5em;
  }
  .information-question-user {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
    margin-bottom: 0.75em;
    .information-question-user-image {
      overflow: hidden;
      border-radius: 50%;
      margin-right: 0.25em;
      .information-question-user-image-circle {
        display: block;
        width: 18px;
        height: 18px;
      }
    }
    .information-question-user-nicnName {
      width: 25%;
      margin-right: 0.5em;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
  .information-question-createdAt {
    display: flex;
    justify-content: flex-end;
  }
  .information-question-info {
    overflow: auto;
    height: 75%;
    margin-right: 0.5em;
  }
}
.information-question-information-answer {
  width: 100%;
  margin-top: 1em;
  font-size: 15px;
  .information-answer {
    width: 100%;
    display: flex;
    flex-direction: row;
    .information-answer-user-image {
      width: 5%;
      overflow: hidden;
      .information-answer-user-image-circle {
        border-radius: 50%;
        width: 18px;
        height: 18px;
      }
    }
    .information-answer-top {
      width: 95%;
      display: flex;
      flex-direction: column;
      font-size: 12px;
      .information-answer-user {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        padding: 0.5em 0.5em 0.5em 1em;
        position: relative;
        background: #696969;
        border-top-left-radius: 0.5em;
        border-top-right-radius: 0.5em;
        margin-left: 0.5em;
        color: #dcdcdc;
      }
      .information-answer-user:after {
        content: "";
        position: absolute;
        left: 0;
        top: 50%;
        width: 0;
        height: 0;
        border: 1em solid transparent;
        border-right-color: #696969;
        border-left: 0;
        margin-top: -1em;
        margin-left: -0.5em;
      }
    }
    .information-answer-bottom {
      margin-top: -1em;
      margin-left: 0.5em;
      padding-top: 1em;
      border: 1px solid #696969;
      border-radius: 0.5em;
      .information-answer-content {
        padding: 0.5em 0.5em 0.5em 1em;
      }
    }
  }
}

.information-comment {
  width: 100%;
  margin-top: 1em;
  padding-left: 4em;
  display: flex;
  flex-direction: row;
  .information-comment-user-image {
    width: 5%;
    overflow: hidden;
    .information-comment-user-image-circle {
      border-radius: 50%;
      width: 18px;
      height: 18px;
    }
  }
  .information-comment-top {
    width: 95%;
    font-size: 12px;
    display: flex;
    flex-direction: column;
    .information-comment-user {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      padding: 0.5em 0.5em 0.5em 1em;
      position: relative;
      background: #696969;
      border-top-left-radius: 0.5em;
      margin-left: 0.5em;
      color: #dcdcdc;
    }
    .information-comment-user:after {
      content: "";
      position: absolute;
      left: 0;
      top: 50%;
      width: 0;
      height: 0;
      border: 1em solid transparent;
      border-right-color: #696969;
      border-left: 0;
      margin-top: -1em;
      margin-left: -0.5em;
    }
    .information-comment-bottom {
      margin-top: -1em;
      margin-left: 0.5em;
      padding-top: 1em;
      border: 1px solid #696969;
      border-radius: 0.5em;
      .information-comment-content {
        padding: 0.5em 0.5em 0.5em 1em;
      }
    }
  }
}
.information-board-nav {
  height: 100%;
  overflow: auto;
}

.information-board-btn {
  width: 100%;
  font-size: 21px;

  .information-btn-information-board-info {
    font-weight: bold;
    margin: 0.5em;
  }
  .information-btn-board-write {
    margin: 0;
    font-size: 12px;
    text-align: left;
    .write-icon {
      margin-right: 0.15em;
      font-size: 18px;
    }
  }
}
.information-board-search {
  width: 100%;
  margin-bottom: 0.125em;
  font-size: 12px;
}

.information-board-title-list {
  width: 100%;
}
.information-board-title-list-item {
  width: 100%;
  margin-bottom: 1em;
}
.information-question-list {
  display: flex;
  flex-direction: row;
  width: 100%;
  font-size: 18px;

  .information-question-list-icon {
    margin-right: 0.15em;
  }
}
.information-question-list:hover {
  cursor: pointer;
  color: black;
  background: rgba(105, 105, 105, 0.3);
  opacity: 0.7;
}
.information-question-list-info {
  text-align: start;
  width: 90%;
  font-size: 12px;
}
.information-list-title {
  height: 1em;
  width: 100%;
  font-size: 15px;
}
.information-list-createdAt {
  font-size: 5px;
  margin-top: 0.5em;
}
.information-list-userinfo {
  width: 100%;
  margin-top: 0.25em;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;

  .information-list-user-image {
    overflow: hidden;
    border-radius: 50%;
    margin-right: 0.25em;
    .information-user-image-circle {
      display: block;
      width: 18px;
      height: 18px;
    }
  }
  .information-list-user-nickName {
    width: 25%;
    margin-right: 0.5em;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
}
</style>
