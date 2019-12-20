import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import SignUp from '../views/SignUp.vue';
import Place from '../views/Place.vue';
import store from '../store/index';

Vue.use(VueRouter);

const routes = [
	{
		path: '/signup',
		name: 'signup',
		component: SignUp
	},
	{
		path: '/login',
		name: 'login',
		component: Login
	},
	{
		path: '/home',
		name: 'home',
		component: Home,
		meta: {
			requiresAuth: true
		}
	},
	{
		path: '/place/:id',
		name: 'place',
		component: Place,
		props: true,
		meta: {
			requiresAuth: true
		}
	},
	{
		path: '/admin',
		name: 'admin',
		meta: {
			requiresAuth: true
		},
		component: () =>
				import(/* webpackChunkName: "admin" */ '../views/Admin.vue')
	},
	{
		path: '/user/:id',
		name: 'user',
		meta: {
			requiresAuth: true
		},
		props: true,
		component: () =>
				import(/* webpackChunkName: "user" */ '../views/User.vue')
	},
	{
		path: '/about',
		name: 'about',
		meta: {
			requiresAuth: true
		},
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () =>
				import(/* webpackChunkName: "about" */ '../views/About.vue')
	},
	// All unknown routes should go to Login
	{
		path: '*',
		redirect: '/login'
	}
];

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
});

router.beforeEach((to, from, next) => {
	const currentUser = store.state.isUserLogged;
	const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

	if (requiresAuth && !currentUser) {
		next('login');
	} else if (!requiresAuth && currentUser) {
		next('home');
	} else {
		next();
	}
});

export default router;
