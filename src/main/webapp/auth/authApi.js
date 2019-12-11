/**
 * Special axios instance with token passed
 * @author victor
 * @date 11.12.2019
 * @project: pubslist
 */

import axios from "axios";
import store from "./../store/index";

const authInstance = axios.create({});

authInstance.interceptors.request.use(config => {
  config.headers.common["Authorization"] =
    "Bearer " + store.getters["getUserToken"];

  return config;
});

export default authInstance;
