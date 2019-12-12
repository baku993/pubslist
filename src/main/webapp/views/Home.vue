<template>
	<div class='home'>
		<div class='headline'>Your favorite places</div>
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
				:places='filteredPlaces'
		></places-list>
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
						filtered = this.places.filter(p => p.audit.createdBy === this.getUser.id && p.approved);
						break;
					case 2:
						filtered = this.places.filter(p =>
							!p.approved && ((p.createdBy === this.getUser.id) ||
							this.getUser.role.includes('ADMIN'))
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
</style>
