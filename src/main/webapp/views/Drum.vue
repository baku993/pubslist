<template>
	<div class='drum'>
		<h1>I feel lucky</h1>

		<wheel :places='places' @updateRolls='updateRolls'/>

		<span>Rolls History</span>

		<v-data-table
				:headers='headers'
				:items='rolls'
				disable-sort
				class='elevation-1'
				loading-text='Loading... Please wait'
		/>
	</div>
</template>


<script>
	import Wheel from '../components/Wheel';
	import authApi from '../auth/authApi';

	export default {
		name: 'drum',
		components: {Wheel},
		data() {
			return {
				places: [],
				headers: [
					{
						text: 'Place Name',
						align: 'left',
						sortable: false,
						value: 'place.name',
					},
					{ text: 'Roll Date', value: 'rolledAt' },
					{ text: 'Rolled By', value: 'rolledBy' }],
				rolls: []
			};
		},
		methods: {
			updateRolls(){
				authApi.get('/api/rolls').then(resp => {
					this.rolls = resp.data.sort(function(a,b){
						return Date.parse(b.rolledAt) - Date.parse(a.rolledAt);
					}).map(function(roll){
						roll.rolledAt = new Date(roll.rolledAt).toLocaleString();
						return roll;
					});
				}).catch(() => {
					this.$toastr.e('Ups... Something went wrong');
				});
			}
		},
		created() {
			authApi.get('/api/places').then(resp => {
				this.places = resp.data.filter(p => p.approved);
			}).catch(() => {
				this.$toastr.e('Ups... Something went wrong');
			});
			this.updateRolls();
		}
	};
</script>
