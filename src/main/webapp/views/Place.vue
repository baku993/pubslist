<template>

	<v-container class='fill-height' fluid>
		<!-- Image & buttons -->
		<v-row align='center' class='fill-width'>
		</v-row>

		<!--Form with fields-->
		<place-data :data='fieldsData'></place-data>

		<!--Comments-->
		<comments :comments='comments'></comments>

	</v-container>

</template>

<script>

	import authApi from '../auth/authApi';

	import PlaceData from '../components/PlaceData';
	import Comments from '../components/Comments';

	export default {
		name: 'placeView',
		components: {Comments, PlaceData},
		props: ['id'],
		data() {
			return {
				data: {}
			};
		},
		computed: {
			fieldsData() {
				return Object.keys(this.data).filter(k => k !== 'image' && k !== 'comments');
			},
			comments() {
				return this.data.comments || [];
			}
		},
		created() {
			if (this.props) {
				// Load place
				authApi.get('/api/places/' + this.id).then(resp => {
					this.data = resp.data;
				}).catch(error => {
					// Add user notification here
					console.log(error);
				});
			}
		}
	};
</script>

<style scoped lang="scss">

</style>
