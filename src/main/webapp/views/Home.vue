<template>
	<div class='home'>
		<div class='tabs'>
			<v-tabs class='tabs__elem d-flex'>
				<v-tab @click='tabSelected = 0'>All</v-tab>
				<v-tab @click='tabSelected = 1'>My</v-tab>
				<v-tab @click='tabSelected = 2'>Pending</v-tab>
			</v-tabs>
		</div>
		<places-list
				@liked='addToLiked'
				@vote='voteForPlace'
				@open='openPlace'
				:places='filteredPlaces'>
		</places-list>

		<v-btn absolute dark fab bottom right color='primary' @click='newPlace'>
			<v-icon>mdi-plus</v-icon>
		</v-btn>
	</div>
</template>

<script>
	import PlacesList from '../components/PlacesList';
	import authApi from '../auth/authApi';
	import {GET_USER} from '../constants';
	import {mapGetters} from 'vuex';

	export default {
		name: 'home',
		components: {PlacesList},
		data() {
			return {
				tabSelected: 0,
				places: []
			};
		},
		computed: {
			...mapGetters([GET_USER]),
			filteredPlaces() {
				let filtered;
				switch (this.tabSelected) {
					case 0:
					default:
						filtered = this.places.filter(p => p.approved);
						break;
					case 1:
						filtered = this.places.filter(p => p.createdBy === this.getUser.username && p.approved);
						break;
					case 2:
						filtered = this.places.filter(p =>
							!p.approved && (this.getUser.role.includes('ADMIN')
							|| p.createdBy === this.getUser.username)
						);
						break;
				}
				return filtered;
			}
		},
		methods: {
			voteForPlace(id) {
				console.log('Vote for place', id);
			},
			addToLiked(id) {
				console.log('Add to liked places', id);
			},
			openPlace(id) {
				this.$router.push({name: 'place', params: {id: id}});
			},
			newPlace() {
				this.$router.push({name: 'newplace'});
			}
		},
		created() {
			authApi.get('/api/places').then(resp => {
				this.places = resp.data;
			}).catch(error => {
				// Add user notification here
				console.log(error);
			});
		}
	};
</script>

<style lang="scss">

	.home {

		min-height: 85%;
		position: relative;

		.tabs {
			margin-top: 50px;
			padding-bottom: 20px;
		}

		.tabs__elem {
			justify-content: center;

			& .v-tabs-bar {
				background-color: inherit !important;
			}
		}
	}
</style>
