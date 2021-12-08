import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";
import store from "./lib/store";
import { Quasar } from "quasar";
import quasarUserOptions from "./quasar-user-options";
import VueApexCharts from "vue3-apexcharts";
import Vue3Autocounter from "vue3-autocounter";
import AOS from "aos";
import "aos/dist/aos.css";
import "fullpage.js/vendors/scrolloverflow"; // Optional. When using scrollOverflow:true

createApp(App)
  .use(Quasar, quasarUserOptions)
  .use(store)
  .use(router)
  .use(VueApexCharts)
  .use(AOS.init())
  .use("vue3-autocounter", Vue3Autocounter)
  .mount("#app");
