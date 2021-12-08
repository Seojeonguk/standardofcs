import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "first-main",
    component: () => import("../views/first/main.vue"),
  },
  {
    path: "/home",
    name: "main-login",
    component: () => import("../views/main/login.vue"),
    children: [
      {
        path: "/home/info",
        name: "information",
        component: () => import("../views/information/info.vue"),
        children: [
          {
            path: "/home/info",
            name: "info-information",
            component: () => import("../views/information/information.vue"),
          },
          {
            path: "/home/info/board/main",
            name: "info-board-main",
            component: () => import("../views/information/board-info/main.vue"),
          },
          {
            path: "/home/info/board/question",
            name: "info-board-question",
            component: () =>
              import("../views/information/board-info/question.vue"),
          },
          {
            path: "/home/info/board/write",
            name: "info-board-write",
            component: () =>
              import("../views/information/board-info/write.vue"),
          },
          {
            path: "/home/info/mypage/main",
            name: "info-mypage-main",
            component: () =>
              import("../views/information/mypage-info/main.vue"),
          },
          {
            path: "/home/info/problem/main",
            name: "info-problem-main",
            component: () =>
              import("../views/information/problem-info/main.vue"),
          },
          {
            path: "/home/info/problem/description",
            name: "info-problem-description",
            component: () =>
              import("../views/information/problem-info/description.vue"),
          },
          {
            path: "/home/info/problem/solve",
            name: "info-problem-solve",
            component: () =>
              import("../views/information/problem-info/solve.vue"),
          },
          {
            path: "/home/info/problem/result",
            name: "info-problem-result",
            component: () =>
              import("../views/information/problem-info/result.vue"),
          },
        ],
      },
      {
        path: "/home/mypage",
        name: "mypage",
        component: () => import("../views/mypage/mypage.vue"),
        children: [
          {
            path: "/home/mypage",
            name: "mypage-chart",
            component: () => import("../views/mypage/components/chart.vue"),
          },
          {
            path: "/home/mypage/board",
            name: "mypage-board",
            component: () => import("../views/mypage/components/board.vue"),
          },
          {
            path: "/home/mypage/modify",
            name: "mypage-modify",
            component: () => import("../views/mypage/components/modify.vue"),
          },
        ],
      },
      {
        path: "/home/problem",
        name: "problem",

        component: () => import("../views/problem/problem.vue"),
        children: [
          {
            path: "/home/problem",
            name: "problem-info",
            component: () => import("../views/problem/components/info.vue"),
          },
          {
            path: "/home/problem/description",
            name: "problem-description",
            props: true,
            component: () =>
              import("../views/problem/components/description.vue"),
          },
          {
            path: "/home/problem/solve",
            name: "problem-solve",
            component: () => import("../views/problem/components/solve.vue"),
            // children: [{}],
          },
          {
            path: "/home/problem/result",
            name: "problem-result",
            component: () => import("../views/problem/components/result.vue"),
          },
          {
            path: "/home/problem/make",
            name: "problem-make",
            component: () => import("../views/problem/components/make.vue"),
          },
        ],
      },
      {
        path: "/home/board",
        name: "board",
        component: () => import("../views/board/board.vue"),
        children: [
          {
            path: "/home/board",
            name: "board-info",
            component: () => import("../views/board/components/boardinfo.vue"),
          },
          {
            path: "/home/board/question/:id",
            name: "board-question",
            props: true,
            component: () => import("../views/board/components/question.vue"),
          },
          {
            path: "/home/board/write",
            name: "board-write",
            component: () => import("../views/board/components/write.vue"),
          },
        ],
      },
      {
        path: "/home/approve",
        name: "approve",
        component: () => import("../views/approve/approve.vue"),
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
