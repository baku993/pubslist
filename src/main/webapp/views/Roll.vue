<template>
	<div class='roll'>
	<v-container class='fill-height flex-column'>
		<v-card class='d-flex place-card mx-auto'>
			<v-toolbar color='primary' flat class='flex-fill toolbar'>
				<v-toolbar-title class='headline mb-1'>Roll</v-toolbar-title>
				<v-spacer/>
			</v-toolbar>
			<v-img max-height='200' min-height='200'
				   :src='getImage()'
				   alt=''
				   class='white--text place-image align-end flex-fill'>
				<v-card-title class='headline'>{{roll.place.name}}</v-card-title>
			</v-img>
			<sharing :roll='roll'/>
			<v-card-text>

				<v-form ref='form'>
					<v-text-field
							:readonly='true'
							label='Name'
							name='name'
							type='text'
							required
							filled
							v-model='roll.place.name'/>

					<a v-bind:href='roll.place.url'>
						<v-textarea
							:readonly='true'
							label='Url'
							name='url'
							required
							filled
							auto-grow
							rows='2'
							v-model='roll.place.url'/>
					</a>

					<v-textarea
						:readonly='true'
						label='Description'
						name='description'
						filled
						auto-grow
						rows='2'
						v-model='roll.place.description'/>

					<v-textarea
						:readonly='true'
						label='Rolled by'
						name='Rolled by'
						filled
						auto-grow
						rows='2'
						v-model='roll.rolledBy'/>

					<v-textarea
							:readonly='true'
							label='Rolled at'
							name='Rolled at'
							filled
							auto-grow
							rows='2'
							v-model='roll.rolledAt'/>
				</v-form>
			</v-card-text>
		</v-card>
	</v-container>
	</div>
</template>

<script>

	import {mapGetters} from 'vuex';
	import {GET_USER} from '../constants';
	import authApi from '../auth/authApi';
	import Sharing from '../components/Sharing';

	export default {
		name: 'roll',
		components: {Sharing},
		props: ['id'],
		data() {
			return {
				roll: null,
				image: null
			};
		},
		computed: {
			...mapGetters([GET_USER]),
		},
		created() {
			authApi.get('/api/rolls/' + this.id).then(resp => {
				this.roll = resp.data;
				this.roll.rolledAt = new Date(resp.data.rolledAt).toLocaleString();
				this.image = resp.data.place.image;
			}).catch(() => {
				this.$toastr.e('Ups... Something went wrong');
			});
		},
		methods:{
			getImage() {
				return this.image ? this.image : require('../assets/dummy.jpg');
			},
			getCurrentUrl() {
				return window.location.pathname;
			}
		}
	};

</script>

<style scoped lang="scss">

	.container {
		justify-content: center;
	}

	.jumbotron {
		min-height: 150px;
		width: 100%;
		justify-content: space-between;
	}

	.toolbar {
		width: 100%;
	}

	.place-card {
		display: flex;
		width: 80%;
		flex-direction: column;
		align-items: center;
	}

	.place-image {
		background-color: grey;
		display: flex;
	}

	.actions {
		display: flex;
		width: 100%;
		justify-content: space-around;
	}

</style>
