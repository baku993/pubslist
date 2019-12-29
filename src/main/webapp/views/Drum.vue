<template>
	<div class='drum'>
		<h1>I feel lucky</h1>

		<wheel :places='places'></wheel>
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
				places: []
			};
		},
		created() {
			authApi.get('/api/places').then(resp => {
				this.places = resp.data.filter(p => p.approved);
			}).catch(error => {
				// Add user notification here
				console.log(error);
			});
		}
	};
</script>
