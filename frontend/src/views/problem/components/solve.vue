<template>
  <div class="problem-right-wrap">
    <h4 data-aos="fade-up">
      <b>
        {{ parseInt($route.query.num) + 1 }}.
        {{ state.problems[$route.query.num].description }}
      </b>
    </h4>

    <q-btn round @click="startSpeechToTxt" color="red" class="speech-to-text">
      <q-avatar size="120px">
        <img src="https://i.ibb.co/qrtvzG3/mike.jpg" />
      </q-avatar>
    </q-btn>

    <br />
    <b>
      <vue3-autocounter
        ref="counter"
        :startAmount="0"
        :endAmount="300"
        :duration="300"
        separator=","
        decimalSeparator="."
        :decimals="2"
        :autoinit="false"
      />
    </b>

    <p>
      <b>{{ lastTranscription }}</b>
    </p>

    <q-btn
      unelevated
      flat
      @click="checkAnswer($route.query.id, $route.query.num, lastTranscription)"
      ><b>Next</b></q-btn
    >
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, reactive } from "vue";
import Vue3autocounter from "vue3-autocounter";

export default {
  name: "login-nav",
  data() {
    return {
      runtimeTranscription_: "",
      transcription_: [],
      lastTranscription: "",
      lang_: "ko-KR",
      img: "mic_outline",
    };
  },
  components: {
    "vue3-autocounter": Vue3autocounter,
  },
  setup() {
    const store = useStore();
    const state = reactive({
      problems: computed(() => store.getters["root/getSelectedProblems"]),
    });
    return {
      state,
    };
  },
  watch: {
    recordFlag: function () {
      this.img = "mic_off_outline";
    },
  },
  methods: {
    startSpeechToTxt() {
      // initialisation of voicereco
      this.$refs.counter.start();
      this.recordFlag = false;
      // window.SpeechRecognition =
      //   window.SpeechRecognition || window.webkitSpeechRecognition;
      const recognition = new window.SpeechRecognition();
      recognition.lang = this.lang_;
      recognition.interimResults = true;

      // event current voice reco word
      recognition.addEventListener("result", (event) => {
        var text = Array.from(event.results)
          .map((result) => result[0])
          .map((result) => result.transcript)
          .join("");
        this.runtimeTranscription_ = text;
      });
      // end of transcription
      recognition.addEventListener("end", () => {
        this.recordFlag = true;
        this.transcription_.push(this.runtimeTranscription_);
        this.lastTranscription = this.runtimeTranscription_;
        this.runtimeTranscription_ = "";
        recognition.stop();
        this.$refs.counter.pause();
      });
      recognition.start();
    },
    checkAnswer(id, num) {
      const payload = {
        id: id,
        myAnswer: this.lastTranscription,
      };
      (this.lastTranscription = ""),
        this.$store
          .dispatch("root/requestProblemCheckAnswer", payload)
          .then((response) => {
            this.$store.commit("root/setProblemResults", response);
            num++;
            if (num < this.state.problems.length) {
              this.$router.push({
                name: "problem-solve",
                query: {
                  num: num,
                  id: this.state.problems[num].id,
                },
              });
            } else {
              this.$router.push({
                name: "problem-result",
              });
            }
          });
    },
  },
};
</script>
<style scoped>
.speech-to-text {
  /* border: 1px solid black; */
  z-index: 10;
}
.speech-to-text:hover {
  cursor: pointer;
}
</style>
