import Vue from 'vue';
import Vuex from 'vuex';
import {
	IS_DARK,
	IS_LOADING,
	SET_DARK_ACTION,
	SET_LOADING_ACTION,
	SET_LOGGED_ACTION,
	SET_TOKEN_ACTION,
	SET_USER_ACTION,
	USER_TOKEN_KEY
} from '../constants';

Vue.use(Vuex);

const state = {
	isUserLogged: false,
	isLoading: false,
	userToken: '',
	user: {},
	isDark: false
};
// Check token in session storage
const isStorageSupported = window.sessionStorage;

// mutations are operations that actually mutates the state.
// each mutation handler gets the entire state tree as the
// first argument, followed by additional payload arguments.
// mutations must be synchronous and can be recorded by plugins
// for debugging purposes.
const mutations = {
	setUserLogged(state, newValue) {
		state.isUserLogged = newValue;
		if (!newValue) {
			state.userToken = '';
		}
	},
	setUser(state, user) {
		state.user = user;
	},
	setUserToken(state, userToken) {
		state.userToken = userToken;
		state.isUserLogged = true;
	},
	setDarkTheme(state, value) {
		state.isDark = value;
	},
	setIsLoading(state, value) {
		state.isLoading = value;
	}
};

// actions are functions that cause side effects and can involve
// asynchronous operations.
const actions = {
	setUserLogged: ({ commit }, value) => {
		commit(SET_LOGGED_ACTION, value);
		if (!value) {
			store.dispatch(SET_TOKEN_ACTION, undefined);
		}
	},
	setUser: ({ commit }, value) => {
		commit(SET_USER_ACTION, value);
	},
	setDarkTheme: ({ commit }, value) => {
		if (isStorageSupported) {
			sessionStorage.setItem(IS_DARK, value);
		}
		commit(SET_DARK_ACTION, value);
	},
	setIsLoading: ({ commit }, value) => {
		if (isStorageSupported) {
			sessionStorage.setItem(IS_LOADING, value);
		}
		commit(SET_LOADING_ACTION, value);
	},
	setUserToken: ({ commit }, userToken) => {
		if (!userToken) {
			if (isStorageSupported) {
				sessionStorage.removeItem(USER_TOKEN_KEY);
			} else {
				commit(SET_TOKEN_ACTION, undefined);
			}
		} else {
			if (isStorageSupported) {
				sessionStorage.setItem(USER_TOKEN_KEY, userToken);
			}
			commit(SET_TOKEN_ACTION, userToken);
		}
	}
};

// getters are functions
const getters = {
	isUserLogged: state => state.isUserLogged,
	getUserToken: state => state.userToken,
	getUser: state => state.user,
	isDarkTheme: state => state.isDark,
	isLoading: state => state.isLoading
};

const store = new Vuex.Store({
	state,
	getters,
	actions,
	mutations
});

export default store;
