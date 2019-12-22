<template>

	<div class='notif'>
		<v-snackbar color='success' :value='isSuccess' bottom elevation='6'>
			<v-row>
				<v-col class='grow align-center'>{{success}}</v-col>
			</v-row>
		</v-snackbar>

		<v-snackbar color='error' :value='isError' bottom elevation='6'>
			<v-row>
				<v-col class='grow align-center'>{{error}}</v-col>
			</v-row>
		</v-snackbar>
	</div>

</template>

<script>
	export default {
		name: 'notifications',
		props: ['error', 'success'],
		data() {
			return {
				isSuccess: false,
				isError: false,
				timeout: {},
				timeoutTime: 4000
			};
		},
		watch: {
			error: function() {
				this.isError = true;
			},
			success: function() {
				this.isSuccess = true;
			}
		},

		updated() {
			this.timeout = setTimeout(() => {
				clearTimeout(this.timeout);
				this.isSuccess = false;
				this.isError = false;

			}, this.timeoutTime);
		}
	};
</script>

<style scoped lang='scss'>

</style>
