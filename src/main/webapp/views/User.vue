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
								<div class='crop'>
									<img :src='this.image' alt='' class='user-image'/>
								</div>
								<v-text-field
										label='User name'
										name='username'
										type='text'
										disabled
										v-model='original.username'/>

								<v-text-field
										@input='validateForm'
										label='First name'
										name='firstname'
										type='text'
										required
										:rules='nameRules'
										hint='More than 6 characters, letters only'
										v-model='firstName'/>

								<v-text-field
										@input='validateForm'
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
								<image-uploader @onImageUpload='onImageUpload'/>
							</v-form>

						</v-card-text>
						<v-card-actions>
							<v-spacer/>
							<v-btn @click='saveUser' :disabled='!(this.valid && Object.keys(this.updated).length > 0)' class='green'>Save</v-btn>
							<v-btn @click='goBack'>Close</v-btn>
						</v-card-actions>
					</v-card>
				</v-col>
			</v-row>
		</v-container>
	</div>

</template>

<script>

	import authApi from '../auth/authApi';
	import {GET_USER} from '../constants';
	import ImageUploader from '../components/ImageUploader';
	import {mapGetters} from 'vuex';

	export default {
		name: 'user',
		components: {ImageUploader},
		props: ['id'],
		data() {
			return {
				firstName: '',
				lastName: '',
				image: null,
				disabled: false,
				original: {},
				notifications: [],
				updated: {},
				valid: false,
				nameRules: [
					v => !!v || 'Name is required',
					v => /^[A-Za-z]+$/.test(v) || 'Only letters are allowed',
					v => (v && v.length < 14) || 'Name should be less than 14 characters'
				],
				errorMessage: '',
				successMessage: ''
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
			},
			image: function(val) {
				if (this.original.image !== val) {
					this.updated['image'] = val;
				}
			}
		},
		methods: {
			saveUser() {

				this.validateForm();

				if (this.valid && Object.keys(this.updated).length > 0) {
					// Save user
					authApi.patch('/api/users/' + this.id, this.updated).then(() => {
						this.updated = {};
						this.$toastr.s('User has been saved successfully');
					}).catch(error => {
						this.$toastr.e(error.message);
					});
				}

			},
			goBack() {
				this.$router.back();
			},
			validateForm() {
				return this.$refs.form.validate();
			},
			onImageUpload(dataUrl) {
				this.image = dataUrl;
			}
		},
		created() {
			// Fetch user
			authApi.get('/api/user/' + this.id).then(resp => {
				this.firstName = resp.data.name;
				this.lastName = resp.data.surname;
				this.image = resp.data.image;
				this.disabled = resp.data.disabled;
				this.original = resp.data;
				this.$forceUpdate();
			}).catch(error => {
				this.$toastr.e(error.message);
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

	.user-image {
		max-height: 200%;
		max-width: 200%;
		margin-left: auto;
		margin-right: auto;
		display: block;
	}

	.crop {
		overflow: hidden;
		min-width: 100%;
		max-height: 400px;
	}

</style>
