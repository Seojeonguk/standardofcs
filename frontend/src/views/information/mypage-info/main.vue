<template lang="">
  <div class="info-wrap">
    <div class="info-add">
      <q-icon
        class="script-icon"
        name="help_outline"
        @click="addMypageScript"
      ></q-icon>
    </div>
    <div class="info-mini-wrap">
      <div class="left-wrap">
        <div class="open-left-wrap">
          <div class="information-mypage-left">
            <div class="information-profile-wrap">
              <img
                class="information-profile-img"
                :src="require('@/assets/malang.png')"
              />
              <span class="information-user-name text-h6 text-bold"
                >디두는 말랑이</span
              >
            </div>
            <div class="information-mypage-menu script">
              <div id="leftBtn1" class="myleft click-myleft">차트</div>
              <div id="leftBtn2" class="myleft">프로필사진 수정</div>
              <div id="leftBtn3" class="myleft">나의 게시글</div>
            </div>
          </div>
        </div>
      </div>
      <div class="right-wrap">
        <div class="open-right-wrap">
          <div style="overflow: auto; width: 100%; height: 100%">
            <div class="first-chart script" style="margin: 1em 0 0 0">
              <h6 style="margin: 0"><b>최근기록</b></h6>
              <div class="row justify-center">
                <apexchart
                  width="500"
                  height="125"
                  type="bar"
                  :options="options"
                  :series="series"
                />
              </div>
            </div>
            <div class="second-chart script" style="margin: 0.5em 0 0 0">
              <h6 style="margin: 0"><b>과목별기록</b></h6>
              <div class="row justify-center item-center">
                <q-btn unelevated flat><b>Vue</b> </q-btn>
                <q-btn unelevated flat><b>React</b> </q-btn>
                <q-btn unelevated flat><b>Java</b> </q-btn>
                <q-btn unelevated flat><b>JavaScript</b> </q-btn>
                <br />
              </div>

              <div class="row justify-center">
                <apexchart
                  width="500"
                  height="125"
                  type="bar"
                  :options="options2"
                  :series="series2"
                />
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
  name: "info-mypage-main",

  setup() {
    const state = reactive({
      script: "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.",
      add: true,
    });
    const addMypageScript = () => {
      let addBtn = document.querySelector(".script-icon");
      let mypage_menu = document.querySelector(".information-mypage-menu");
      let chart1 = document.querySelector(".first-chart");
      let chart2 = document.querySelector(".second-chart");
      if (state.add) {
        addBtn.classList.add("add-after");
        mypage_menu.classList.remove("script");
        mypage_menu.classList.add("add-script");
        mypage_menu.addEventListener("click", () => {
          state.script =
            "마이페이지에서는 프로필 수정, 나의 게시글 목록을 볼 수 있고 \n 문제풀이에 대한 기록을 차트로 볼 수 있습니다.";
        });
        chart1.classList.remove("script");
        chart1.classList.add("add-script");
        chart1.addEventListener("click", () => {
          state.script = "풀었던 문제의 최근 기록을 확인할 수 있습니다.";
        });
        chart2.classList.remove("script");
        chart2.classList.add("add-script");
        chart2.addEventListener("click", () => {
          state.script = "풀었던 문제를 과목별로 기록을 확인할 수 있습니다.";
        });
      } else {
        state.script = "? 버튼을 눌러 기능에 대한 설명을 볼 수 있습니다.";
        addBtn.classList.remove("add-after");
        mypage_menu.classList.remove("add-script");
        mypage_menu.classList.add("script");
        chart1.classList.remove("add-script");
        chart1.classList.add("script");
        chart2.classList.remove("add-script");
        chart2.classList.add("script");
      }
      state.add = !state.add;
    };
    return { state, addMypageScript };
  },
  created() {
    // this.state.scorehisory[0].forEach((score) => {
    //   this.options.xaxis.categories.push(score.createDate);
    //   this.series[0].data.push(parseInt(score.score));
    // });
    // this.state.scorehisoryByCategory[0].forEach((score) => {
    //   this.options2.xaxis.categories.push(score.createDate);
    //   this.series2[0].data.push(parseInt(score.score));
    // });
  },
  data() {
    return {
      options: {
        xaxis: {
          categories: ["Vue", "React", "Java", "JavaScript"],
        },
        colors: ["#FEDD36"],
        fill: {
          type: "gradient",
          gradient: {
            shadeIntensity: 1,
            type: "vertical",
            opacityFrom: 0.3,
            opacityTo: 0.9,
            colorStops: [
              {
                offset: 0,
                color: "#a18cd1",
                opacity: 1,
              },
              {
                offset: 100,
                color: "#fbc2eb",
                opacity: 1,
              },
            ],
          },
        },
        plotOptions: {
          bar: {
            columnWidth: "30%",
            endingShape: "rounded",
            dataLabels: { position: "top" },
          },
        },
      },
      series: [
        {
          name: "점수",
          data: ["33", "55", "77", "100"],
        },
      ],
      options2: {
        xaxis: {
          categories: ["Vue", "React", "Java", "JavaScript"],
        },
        colors: ["#FEDD36"],
        fill: {
          type: "gradient",
          gradient: {
            shadeIntensity: 1,
            type: "vertical",
            opacityFrom: 0.3,
            opacityTo: 0.9,
            colorStops: [
              {
                offset: 0,
                color: "#a18cd1",
                opacity: 1,
              },
              {
                offset: 100,
                color: "#fbc2eb",
                opacity: 1,
              },
            ],
          },
        },
        plotOptions: {
          bar: {
            columnWidth: "30%",
            endingShape: "rounded",
            dataLabels: { position: "top" },
          },
        },
      },
      series2: [
        {
          name: "점수",
          data: ["33", "55", "77", "100"],
        },
      ],
    };
  },
};
</script>
<style lang="scss">
.information-mypage-left {
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  padding: 2em;
}

.information-profile-wrap {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.information-profile-img {
  width: 80%;
  border-radius: 50%;
  border: 1px solid black;
  overflow: hidden;
}

.information-user-name {
  margin: 0.5em 0 1em;
}

.information-mypage-menu {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 12px;
}

.information-mypage-menu div {
  margin: 0.2em 0;
  width: 80%;
}

.information-mypage-menu div:hover {
  cursor: pointer;
  transform: scale(1.05);
}
</style>
