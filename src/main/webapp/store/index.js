import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const state = {
  isUserLogged: false,
  userToken: ""
};

// mutations are operations that actually mutates the state.
// each mutation handler gets the entire state tree as the
// first argument, followed by additional payload arguments.
// mutations must be synchronous and can be recorded by plugins
// for debugging purposes.
const mutations = {
  setUserLogged(state, newValue) {
    state.isUserLogged = newValue;
    if (!newValue) state.userToken = "";
  },
  setUserToken(state, userToken) {
    state.userToken = userToken;
    state.isUserLogged = true;
  }
};

// actions are functions that cause side effects and can involve
// asynchronous operations.
const actions = {
  setUserLogged: ({ commit }, value) => commit("setUserLogged", value),
  setUserToken: ({ commit }, userToken) => commit("setUserToken", userToken)
};

// getters are functions
const getters = {
  isUserLogged: state => state.isUserLogged,
  getUserToken: state => state.userToken
};

const store = new Vuex.Store({
  state,
  getters,
  actions,
  mutations
});

export default store;
