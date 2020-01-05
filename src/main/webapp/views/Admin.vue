<template>

	<div class='admin'>

		<!--Tabs-->
		<v-tabs class='tabs__elem d-flex'>
			<v-tab @click='tabSelected = 0'>Users</v-tab>
			<v-tab @click='tabSelected = 1'>Other</v-tab>
		</v-tabs>

		<!--List of users-->
		<v-list v-if='tabSelected === 0'>

			<v-list-item
					v-for='user in users'
					:key='user.id'
					@click='editUser(user.id)'>

					<v-avatar class='account' size='48' ref='avatar' :color='randomColor()'>
						<span v-if='user.image==null' class='white--text font-weight-bold'>{{getUserAvatar(user)}}</span>
						<img v-else :src='getUserAvatar(user)'/>
					</v-avatar>

				<v-list-item-content>
					<v-list-item-title>
						{{user.name + ' ' + user.surname}}
						<span v-if='user.id === getUser().id' style='font-weight: 700'>(You)</span>
					</v-list-item-title>
				</v-list-item-content>

				<v-list-item-action class='icons'>
					<v-icon hover v-if='user.disabled' color='red'>remove_circle_outline</v-icon>
				</v-list-item-action>

			</v-list-item>
		</v-list>

		<!--Other-->
		<div v-if='tabSelected === 1' class='headline'>Nothing here yet</div>
	</div>

</template>

<script>

	import authApi from '../auth/authApi';
	import randomColor from 'randomcolor';
	import {GET_USER} from '../constants';
	import {mapGetters} from 'vuex';

	export default {
		name: 'admin',
		data() {
			return {
				tabSelected: 0,
				users: []
			};
		},
		methods: {
			...mapGetters([GET_USER]),
			getUserAvatar(user) {
				let avatar;
				if (user.image) {
					avatar = (user.image);
				} else {
					avatar = user.name.charAt(0).toUpperCase() + user.surname.charAt(0).toUpperCase();
				}
				return avatar;
			},
			randomColor() {
				return randomColor();
			},
			editUser(id) {
				this.$router.push({name: 'user', params: { id: id } });
			}
		},
		created() {
			// Load all users
			authApi.get('/api/users').then(resp => {
				this.users = resp.data;
			}).catch(error => {
				// Add user notification here
				console.log(error);
			});
		}
	};
</script>

<style lang="scss" scoped>

	.admin {

		.tabs__elem {
			margin-bottom: 50px;

		}

		.icons {
			display: flex;
			flex-direction: row;
		}

	}

</style>
