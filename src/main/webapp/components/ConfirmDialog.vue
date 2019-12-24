<template>
	<v-dialog @keydown.esc='cancel' v-model='dialog'>
		<v-card>
			<v-toolbar dark dense flat>
				<v-toolbar-title class='white--text'>{{ title }}</v-toolbar-title>
			</v-toolbar>
			<v-card-text v-show='!!message' class='pa-4'>{{ message }}</v-card-text>
			<v-card-actions class='pt-0'>
				<v-spacer></v-spacer>
				<v-btn color='primary darken-1' text @click.native='agree'>Yes</v-btn>
				<v-btn color='grey' text @click.native='cancel'>Cancel</v-btn>
			</v-card-actions>
		</v-card>
	</v-dialog>
</template>
<script>
	export default {
		name: 'confirm-dialog',
		data () {
			return {
				dialog: false,
				title: null,
				message: null,
				resolve: null,
				reject: null
			};
		},
		methods: {
			open(title, message) {
				this.title=title;
				this.message=message;
				this.dialog=true;
				return new Promise((resolve, reject) => {
					this.resolve = resolve;
					this.reject = reject;
				});
			},
			agree() {
				this.resolve(true);
				this.dialog = false;
			},
			cancel() {
				this.resolve(false);
				this.dialog = false;
			}
		}
	};
</script>
