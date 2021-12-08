import axios from "axios";

const instance = axios.create({
  // baseURL: "http://localhost:8080",
  baseURL: "http://k5c101.p.ssafy.io/api",
  headers: {
    "Content-type": "application/json",
  },
});

function createInstance() {
  const instance = axios.create({
    // baseURL: "http://localhost:8080",
    baseURL: "http://k5c101.p.ssafy.io/api",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  });
  return instance;
}

export { createInstance };
export default instance;
