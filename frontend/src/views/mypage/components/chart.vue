<template lang="">
  <div style="overflow: auto; width: 100%; height: 100%" data-aos="fade-up">
    <h3><b>최근기록</b></h3>
    <div class="row justify-center" data-aos="fade-left">
      <apexchart
        width="500"
        height="350"
        type="bar"
        :options="options"
        :series="series"
      />
    </div>

    <h3><b>과목별기록</b></h3>
    <div class="row justify-center item-center" data-aos="fade-left">
      <q-btn
        v-for="(category, index) in state.categories"
        :key="index"
        unelevated
        flat
        @click="selectCategory(index + 1)"
        ><b>{{ category.name }}</b>
        <q-tooltip>{{ category.description }}</q-tooltip>
      </q-btn>
      <br />
    </div>

    <div class="row justify-center">
      <apexchart
        width="500"
        height="350"
        type="bar"
        :options="options2"
        :series="series2"
      />
    </div>
  </div>
</template>
<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
export default {
  name: "problem-result",
  created() {
    this.state.scorehisory[0].forEach((score) => {
      this.options.xaxis.categories.push(score.createDate);
      this.series[0].data.push(parseInt(score.score));
    });
    this.state.scorehisoryByCategory[0].forEach((score) => {
      this.options2.xaxis.categories.push(score.createDate);
      this.series2[0].data.push(parseInt(score.score));
    });
  },
  data() {
    return {
      options: {
        xaxis: {
          categories: [],
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
          data: [],
        },
      ],
      options2: {
        xaxis: {
          categories: [],
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
          data: [],
        },
      ],
    };
  },

  setup() {
    const store = useStore();
    const state = reactive({
      categories: computed(() => store.getters["root/getCategories"]),
      scorehisory: computed(() => store.getters["root/getScoreHistory"]),
      scorehisoryByCategory: computed(
        () => store.getters["root/getScoreHistoryByCategory"]
      ),
    });

    return {
      state,
    };
  },
  methods: {
    selectCategory(index) {
      this.options2.xaxis.categories.length = 0;
      this.series2[0].data.length = 0;
      this.$store
        .dispatch("root/requsetMyChartByCategory", index)
        .then((response) => {
          console.log(response);
          response.data.forEach((datascore) => {
            this.options2.xaxis.categories.push(datascore.createDate);
            this.series2[0].data.push(parseInt(datascore.score));
          });
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },
    save() {
      console.log(this.state.scorehisoryByCategory);
    },
  },
};
</script>
<style></style>
