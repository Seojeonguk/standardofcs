<template>
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addBoardScript"
      ></q-icon>
    </div>
    <div class="info-mini-wrap">
      <div class="left-wrap">
        <div class="open-left-wrap">
          <!-- <div class="information-info-action-btn" @click="addInfoAction">
            <q-icon></q-icon>
          </div> -->
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
          <div class="information-board-info">
            <div class="information-info-content information-first">
              게시판은 <br /><span class="information-span">CS의 정석</span>을
              이용하는 사용자를 위해 만들었습니다
            </div>
            <div class="information-info-content information-second">
              CS지식을 서로 질문하고 공유해 주세요!
            </div>
            <div class="information-info-content information-box">
              게시판 사용에 대한 소개보러가기
            </div>
            <div class="information-info-content information-third">
              <div>무분별한 욕설 및 비방글은 허용하지 않습니다.</div>
              <div>타인에게 불쾌감을 불러 일으키는 글은 자제해주세요.</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="info-script">{{ state.script }}</div>
  </div>
</template>
<script>
import "@/styles/information.scss";
import { reactive } from "vue";
export default {
  name: "info-board-main",
  setup() {
    document
      .getElementById(localStorage.getItem("menu"))
      .classList.remove("click-menu");
    localStorage.removeItem("menu");
    localStorage.setItem("menu", "menuBtn1");
    document
      .getElementById(localStorage.getItem("menu"))
      .classList.add("click-menu");

    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addBoardScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let write = document.querySelector(".script-board-write");
      // let search = document.querySelector(".script-board-search");
      let list = document.querySelector(".script-board-title-list");

      if (state.add) {
        addBtn.classList.add("add-after");
        write.classList.remove("script");
        write.classList.add("add-script");
        write.addEventListener("click", () => {
          state.script = "게시판 이용 중 언제든지 글을 작성할 수 있습니다.";
        });
        list.classList.remove("script");
        list.classList.add("add-script");
        list.addEventListener("click", () => {
          state.script = "선택하여 글을 볼 수 있습니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        write.classList.remove("add-script");
        write.classList.add("script");
        list.classList.remove("add-script");
        list.classList.add("script");
      }
      state.add = !state.add;
    };
    return {
      state,
      addBoardScript,
    };
  },
};
</script>
<style scope lang="scss">
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
.information-board-info {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 2em 1em 2em 1em;
}
.information-info-content {
  font-size: 12px;
  font-weight: bold;
  .information-span {
    text-shadow: 2px 2px 0 #696969, 2px -2px 0 #696969, -2px 2px 0 #696969,
      -2px -2px 0 #696969, 2px 0 0 #696969, -2px 0 0 #696969, 0 2px 0 #696969,
      0 -2px 0 #696969;
    color: #dcdcdc;
  }
  &.information-box {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 50%;
    height: 2em;
    margin: 0 auto;
    border: 2px solid #696969;
    border-radius: 1em;
  }
  &.information-first {
    margin: 0.5em 0em;
    font-size: 24px;
  }
  &.information-second {
    margin: 1em 0em;
    font-size: 18px;
  }
  &.information-third {
    margin: 2em 0em 1em 0em;
    color: #696969;
  }
}
</style>
