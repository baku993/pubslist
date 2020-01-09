<template>

	<div class='notif'>
		<v-snackbar color='success' :value='isSuccess' bottom elevation='6'>
			<v-row>
				<v-col class='grow align-center'>{{message}}</v-col>
			</v-row>
		</v-snackbar>

		<v-snackbar color='error' :value='isError' bottom elevation='6'>
			<v-row>
				<v-col class='grow align-center'>{{message}}</v-col>
			</v-row>
		</v-snackbar>
	</div>

</template>

<script>
	export default {
		name: 'notifications',
		props: ['alerts'],
		data() {
			return {
				point: [],
				isSuccess: false,
				isShowing: false,
				isError: false,
				index: 0,
				message: '',
				timeoutId: false,
				timeoutTime: 4000
			};
		},
		watch: {
			alerts: function() {
				if (!this.isShowing){
					this.isShowing = true;
					this.checkNotifications();
				}

			}
		},
		methods: {
			checkNotifications() {
				if (this.alerts[this.index]['type'] === 'error') {
					this.isError = true;
				} else {
					this.isSuccess = true;
				}
				this.message = this.alerts[this.index]['message'];
				this.waitToHide();
			},
			waitToHide() {
				// Waiting for to hide notification
				this.timeoutId = setTimeout(() => {
					this.isSuccess = false;
					this.isError = false;
					this.index = this.index + 1;
					// Waiting to show next notification in queue
					setTimeout(() => {
						if (this.alerts.length > this.index) {
							this.checkNotifications();
						} else {
							this.isShowing = false;
						}
					},500);
				},this.timeoutTime);
			}
		}
	};
</script>

<style scoped lang='scss'>

</style>
