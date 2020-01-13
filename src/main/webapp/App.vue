<template>
	<div id='app'>
		<v-app :dark='isDarkTheme'>
			<v-content>
				<navbar v-if='isUserLogged' @logout='logout'/>
				<router-view/>
			</v-content>
		</v-app>
	</div>
</template>

<script>
	import 'material-design-icons-iconfont/dist/material-design-icons.css';
	import '@mdi/font/css/materialdesignicons.css';
	import {mapActions, mapGetters} from 'vuex';
	import {IS_DARK, IS_LOGGED, SET_LOGGED_ACTION} from './constants';
	import Navbar from './components/Navbar';

	export default {
		components: {Navbar},
		computed: {
			...mapGetters([IS_LOGGED, IS_DARK])
		},
		methods: {
			...mapActions([SET_LOGGED_ACTION]),
			logout() {
				this.setUserLogged(false);
				this.$router.push('/');
			}
		}
	};
</script>

<style lang="scss">
	#app {
		font-family: "Avenir", Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
	}
</style>
