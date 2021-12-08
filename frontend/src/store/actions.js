import axios from "@/lib/axios";
import { createInstance } from "@/lib/axios";
// http://localhost:8080/swagger-ui.html

// ! {state}, console삭제하면 payload제대로 안넘어감
/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 유저 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 생성하기
export async function requestUserRegist({ state }, payload) {
  console.log("requestUserRegist", state, payload);
  const url = "/user";
  const body = payload;
  return await axios.post(url, body);
}

// 로그인하기
export async function requestUserLogin({ state }, payload) {
  console.log("requestUserLogin", state, payload);
  const url = "/user/login";
  const body = payload;
  return await axios.post(url, body);
}

// 닉네임 중복검사
export async function requestUserNickNameCheck({ state }, payload) {
  console.log("requestUserNickNameCheck", state, payload);
  const url = `/user/${payload}`;
  return await axios.get(url);
}

// 유저정보 가져오기
export async function requsetUserInfo({ state }, payload) {
  console.log("requsetUserInfo", state, payload);
  const url = "/user/me";
  const header = {
    headers: {
      Authorization: `Bearer ${payload}`,
    },
  };
  return await axios.get(url, header);
}

// 사용자 비밀번호 찾기(이메일 발송)
export async function requestUserSendEmail({ state }, payload) {
  console.log("requestUserSendEmail", state, payload);
  const url = "/user/findpass";
  const body = payload;
  return await axios.post(url, body);
}

// 사용자 비밀번호 찾기(비밀번호 변경)
export async function requestUserFixPw({ state }, payload) {
  console.log("requestUserFixPw", state, payload);
  const url = "/user/fixpass";
  const body = payload;
  return await axios.post(url, body);
}
export async function requestUserCheckEmail({ state }, payload) {
  console.log("requestUserCheckEmail", state, payload);
  const url = `/user/dupl/${payload}`;
  return await axios.get(url);
}

// 사용자 정보 변경하기
export async function requestUserModify({ state }, payload) {
  console.log("requestUserModify", state, payload);
  const url = "/user/modify";
  const body = payload;
  return await axios.post(url, body);
}

// 유저정보 리스트 가져오기
export async function requestUserList() {
  const url = "/user/all";
  return await axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 카테고리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 모든 카테고리 정보 가져오기
export async function requsetCategoryList() {
  const url = "/category";
  return await axios.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 문제 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 문제만들기
export async function requsetProblemCreate({ state }, payload) {
  console.log("requsetProblemCreate", state, payload);
  const url = "/problem/make";
  const body = payload;
  console.log(body);
  return await axios.post(url, body);
}

// 카테고리별 문제 리스트 가져오기
export async function requestProblemList({ state }, payload) {
  console.log("requestProblemList", state, payload);
  const url = `/problem/${payload.id}/${payload.page}`;
  return await axios.get(url);
}

//카테고리 문제 개수 가져오기
export async function requestProblemNumInCategory({ state }, payload) {
  console.log("requestProblemList", state, payload);
  const url = `/problem/category/${payload}`;
  return await axios.get(url);
}

// 문제 정답 맞추기
export async function requestProblemCheckAnswer({ state }, payload) {
  console.log("requestProblemCheckAnswer", state, payload);
  const url = "/problem/checkanswer";
  return await axios.post(url, payload);
}

//총 문제 개수
export async function requestAllProblem({ state }) {
  console.log("requestAllProblem", state);
  const url = "/problem/all";
  return await axios.get(url);
}

// 승인 대기중인 문제 리스트 가져오기
export async function requestProblemWait() {
  const url = "/problem/wait";
  return await axios.get(url);
}

// 관리자가 문제상태 변경하기
export async function requestProblemEvaluate({ state }, payload) {
  console.log("requestProblemEvaluate", state, payload);
  const url = "/problem/evaluate";
  const body = payload;
  return await axios.post(url, body);
}

// 게시판 댓글 작성
export async function requestWriteComment({ state }, payload) {
  console.log("requestProblemEvaluate", state);
  const url = "/comment";
  console.log(payload);
  return await axios.post(url, payload);
}

// 게시판 글 삭제
export async function requestDelete({ state }, payload) {
  console.log("requestDelete", state);
  const url = "/board/delete/" + payload;
  return await axios.delete(url);
}

// 문제결과 저장하기
export async function requestSaveResult({ state }, payload) {
  console.log("requestProblemEvaluate", state, payload);
  const instance = createInstance();
  const url = "/score";
  return await instance.post(url, payload);
}

// 나의 차트가져오기
export async function requsetMyChart({ state }) {
  console.log("requsetMyChart", state);
  const instance = createInstance();
  const url = "/score";
  return await instance.get(url);
}

export async function requsetMyChartByCategory({ state }, payload) {
  console.log("requsetMyChartByCategory", state);
  const instance = createInstance();
  const url = `/score/${payload}`;
  return await instance.get(url);
}

/* ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 게시판 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ */
// 게시판 한개 가져오기
export async function requsetBoardInfo({ state }, payload) {
  console.log("requsetBoardInfo", state, payload);
  const url = `/board/${payload}`;
  return await axios.get(url);
}

// 게시판 리스트 가져오기
export async function requestBoardList() {
  const url = "/board";
  return await axios.get(url);
}

export async function requestBoardWrite({ state }, payload) {
  console.log("requestBoardWrite", state, payload);
  const url = "/board";
  console.log(payload);
  return await axios.post(url, payload);
}

export async function requestImageSave({ state }, payload) {
  console.log("requestBoardWrite", state, payload);
  const url = "/board/image";
  console.log(payload);
  return await axios.post(url, payload);
}

// 사용자가 작성한 글 조회
export async function requestUserBoardList({ state }, payload) {
  console.log("requestUserBoardList", state, payload);
  const url = `/board/user/${payload}`;
  return await axios.get(url);
}
