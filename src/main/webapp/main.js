import Vue from 'vue';
import App from './App.vue';
import './registerServiceWorker';
import router from './router';
import vuetify from './VuetifyConf';
import store from './store/index';
import {IS_DARK, SET_DARK_ACTION, SET_TOKEN_ACTION, USER_TOKEN_KEY} from './constants';
import Toastr from 'vue-toastr';

Vue.config.productionTip = false;


// Check token in session storage
if (window.sessionStorage) {
	const userToken = sessionStorage.getItem(USER_TOKEN_KEY);
	const isDark = sessionStorage.getItem(IS_DARK) === 'true';
	// Use it if exists
	if (userToken) {
		store.dispatch(SET_TOKEN_ACTION, userToken);
	}
	if (isDark) {
		store.dispatch(SET_DARK_ACTION, isDark);
	}
}

initApp();

function initApp() {
	const SocialSharing = require('vue-social-sharing');
	new Vue({
		router,
		vuetify,
		store,
		render: h => h(App)
	}).$mount('#app');

	Vue.use(Toastr, {
		defaultPosition: 'toast-bottom-center',
		defaultProgressBar: false
	});
	Vue.use(SocialSharing);
}
