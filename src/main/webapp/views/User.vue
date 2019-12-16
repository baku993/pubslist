<template>

	<div class='user'>
		<v-container>
			<v-row align='center' justify='center'>
				<v-col cols='12' sm='8' md='4'>
					<v-card class='elevation-12'>
						<v-toolbar color='orange' flat>
							<v-toolbar-title class='headline mb-1'>Edit user</v-toolbar-title>
							<v-spacer/>
						</v-toolbar>
						<v-card-text>
							<v-form v-model='valid' ref='form'>
								<v-text-field
										label='User name'
										name='username'
										type='text'
										disabled
										v-model='original.username'/>

								<v-text-field
										label='First name'
										name='firstname'
										type='text'
										required
										:rules='nameRules'
										hint='More than 6 characters, letters only'
										v-model='firstName'/>

								<v-text-field
										label='Last name'
										name='lastname'
										type='text'
										required
										:rules='nameRules'
										hint='More than 6 characters, letters only'
										v-model='lastName'/>

								<v-text-field
										label='Role'
										name='role'
										type='text'
										disabled
										v-model='original.role'/>

								<v-checkbox v-model='disabled' label='Disabled?'/>
							</v-form>
						</v-card-text>
						<v-card-actions>
							<v-spacer/>
							<v-btn @click='saveUser' :class='Object.keys(this.updated) ? &apos;green&apos; : &apos;grey&apos;'>Save
							</v-btn>
							<v-btn @click='goBack'>Cancel</v-btn>
						</v-card-actions>
					</v-card>
				</v-col>
			</v-row>
		</v-container>
		<v-alert type='success' v-if='isSaved' dense prominent>

			<v-row align='center'>
				<v-col class='grow'>User has been saved successfully</v-col>
				<v-col class='shrink'>
					<v-btn @click='goBack' class='grey'>Go back</v-btn>
				</v-col>
			</v-row>
		</v-alert>

		<v-alert type='error' v-if='isError' dismissible dense prominent>
			Oops. Something wrong happen
		</v-alert>
	</div>

</template>

<script>

	import authApi from '../auth/authApi';

	export default {
		name: 'user',
		props: ['id'],
		data() {
			return {
				firstName: '',
				lastName: '',
				disabled: false,
				original: {},
				updated: {},
				valid: false,
				nameRules: [
					v => !!v || 'Name is required',
					v => (v && v.length >= 6) || 'Name must be more than 6 characters',
					v => /^[A-Za-z]+$/.test(v) || 'Only letters are allowed',
					v => (v && v.length < 14) || 'Name should be less than 14 characters'
				],
				isError: false,
				isSaved: false
			};
		},
		watch: {
			firstName: function(val) {
				if (this.original.name !== val) {
					this.updated['name'] = val;
				}
			},
			lastName: function(val) {
				if (this.original.surname !== val) {
					this.updated['surname'] = val;
				}
			},
			disabled: function(val) {
				if (this.original.disabled !== val) {
					this.updated['disabled'] = val;
				}
			}
		},
		methods: {
			saveUser() {
				this.validateForm();

				if (Object.keys(this.updated)) {
					// Save user
					authApi.patch('/api/users/' + this.id, this.updated).then(resp => {
						console.log('User saved, status=' + resp.status);
						this.isSaved = true;
					}).catch(error => {
						// Add user notification here
						console.log(error);
						this.isError = true;
					});
				}

			},
			goBack() {
				this.$router.back();
			},
			validateForm() {
				this.$refs.form.validate();
			}
		},
		created() {
			// Fetch user
			authApi.get('/api/user/' + this.id).then(resp => {
				this.firstName = resp.data.name;
				this.lastName = resp.data.surname;
				this.disabled = resp.data.disabled;
				this.original = resp.data;
			}).catch(error => {
				// Add user notification here
				console.log(error);
				this.isError = true;
			});
		}
	};
</script>

<style scoped lang="scss">

	.user {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-evenly;
		height: 90%;
	}

</style>
