<template>
	<v-row class='toolbar'>
		<div class='nav'>
			<div class='nav__logo'>
				<img alt='Pubs logo' src='../assets/logo.png'/>
			</div>
			<div class='nav__menu'>
				<router-link exact active-class='router-active-link' to='/home'>Home</router-link>
				|
				<div class='admin' v-if='getUser.role && getUser.role.includes("ADMIN")'>
					<router-link exact active-class='router-active-link'
								 to='/admin'>Admin</router-link>
					|
				</div>
				<router-link exact active-class='router-active-link' to='/about'>About</router-link>
			</div>
			<v-menu bottom offset-y>
				<template v-slot:activator='{ on }'>
					<v-avatar class='account' size='62' v-on='on' ref='avatar'>
						<span class='white--text font-weight-bold'>{{getUserAvatar}}</span>
					</v-avatar>
				</template>
				<v-list>
					<v-list-item v-for='(item, i) in items' :key='i' @click='logout'>
						<v-list-item-title>{{ item }}</v-list-item-title>
					</v-list-item>
				</v-list>
			</v-menu>
		</div>
	</v-row>
</template>

<script>

	import authApi from '../auth/authApi';
	import {GET_USER, SET_USER_ACTION} from '../constants';
	import {mapGetters} from 'vuex';
	import randomColor from 'randomcolor';

	export default {
		name: 'navbar',
		props: [],
		data() {
			return {
				items: ['Logout']
			};
		},
		computed: {
			...mapGetters([GET_USER]),
			getUserAvatar() {
				let avatar = 'Avatar';
				if (Object.keys(this.getUser).length > 0) {
					avatar = (this.getUser.avatar || this.getUser.name.charAt(0).toUpperCase() +
						this.getUser.surname.charAt(0).toUpperCase());
				}
				return avatar;
			}
		},
		methods: {
			logout() {
				this.$emit('logout');
			}
		},
		created() {

			const color = randomColor();
			const self = this;
			authApi.get('/api/user')
				.then(async response => {
					await self.$store.dispatch(SET_USER_ACTION, response.data);
					self.$refs.avatar.$el.style.backgroundColor = color;
				})
				.catch(error => {
					// Add user notification here
					console.log(error);
				});
		}
	};
</script>

<style lang="scss" scoped>
	.toolbar {
		justify-content: space-between;
		padding: 10px 10px;
		box-shadow: 0 3px 5px 0 rgba(36, 50, 66, 0.2);
		margin-bottom: 20px;
		border-radius: 2px;
	}

	.account {
		cursor: pointer;
		position: absolute;
		right: 20px;
		top: 10px;
	}

	.nav {
		padding: 20px;
		flex: 1;
		display: flex;
		flex-direction: row;
		justify-content: center;

		&__logo img {
			left: 10px;
			width: 60px;
			top: 10px;
			height: 60px;
			position: absolute;
		}

		&__menu {
			flex: 1;
		}

		a {
			font-weight: bold;
			color: #2c3e50;

			&.router-active-link {
				color: #42b983;
			}
		}

		.admin {
			display: inline;
		}
	}
</style>
