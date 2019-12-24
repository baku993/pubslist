<template>

	<v-container class='fill-height flex-column'>
		<v-card class='d-flex place-card mx-auto'>
			<v-toolbar color='primary' flat class='flex-fill toolbar'>
				<v-toolbar-title class='headline mb-1'>{{id ? 'Edit place' : 'Suggest a place'}}</v-toolbar-title>
				<v-spacer/>
			</v-toolbar>
			<v-img max-height='200' min-height='200'
				   src='../assets/dummy.jpg'
				   class='white--text place-image align-end flex-fill'>
				<v-card-title class='headline'>{{place.name}}</v-card-title>
			</v-img>

			<!-- Image & buttons -->
			<v-row align='center' class='flex-fill jumbotron'>
				<v-card-actions class='actions'>
					<div class='common'>
						<v-btn icon @click='addToFavorites()'>
							<v-icon>mdi-heart</v-icon>
						</v-btn>
						<v-btn text @click='vote()'>Vote</v-btn>
						<v-chip class='ma-2'>{{place.votes || 0}}</v-chip>
					</div>
					<div class='manage'>
						<v-btn color='blue darken-2' v-if='canApprove' @click='approvePlace'>Approve
						</v-btn>
						<v-btn color='red darken-2' v-if='canDelete' @click='deletePlace'>Delete
						</v-btn>
						<v-btn color='green darken-2' v-if='!isEditing' @click='isEditing = !isEditing'>Edit
						</v-btn>
						<v-btn v-else @click='save' :class='{ grey: !valid, green: valid }' :disabled='!valid'>Save
						</v-btn>
						<v-btn @click='close'>Close</v-btn>
					</div>
				</v-card-actions>
			</v-row>

			<v-card-text>

				<!--Form with fields-->
				<place-data :data='fieldsData' @updated='updateFields'></place-data>

			</v-card-text>

			<!--Comments-->
			<comments :comments='comments'
					  :user='getUser'
					  @add='addNewComment'
					  @delete='deleteComment'>
			</comments>
		</v-card>
		<confirm-dialog ref='confirm'></confirm-dialog>
		<notifications :error='errorMessage' :success='successMessage'></notifications>
	</v-container>

</template>

<script>

	import authApi from '../auth/authApi';

	import PlaceData from '../components/PlaceData';
	import Comments from '../components/Comments';
	import {mapGetters} from 'vuex';
	import {GET_USER} from '../constants';
	import Notifications from '../components/Notifications';
	import ConfirmDialog from '../components/ConfirmDialog';

	export default {
		name: 'place',
		props: ['id'],
		components: {ConfirmDialog, Notifications, Comments, PlaceData},
		data() {
			return {
				place: {},
				isEditing: false,
				valid: false,
				dialog: false,
				comments: [],
				successMessage: '',
				errorMessage: '',
				confirmationText: ''
			};
		},
		computed: {
			...mapGetters([GET_USER]),
			fieldsData() {
				return {
					name: this.place.name,
					address: this.place.address,
					description: this.place.description
				};
			},
			canApprove() {
				// Current user is admin and place is not approved
				return !this.place.approved && this.getUser.role && this.getUser.role.includes('ADMIN');
			},
			canDelete() {
				// Place is not approved and user is owner or user is admin
				return !this.place.approved && (this.getUser.role.includes('ADMIN')
					|| this.getUser.username === this.place.createdBy);
			}
		},
		methods: {
			save() {
				if (this.id) {
					authApi.patch('/api/places', this.place).then(() => {
						this.successMessage = 'Place has been successfully updated';
					}).catch(error => {
						this.errorMessage = error.message;
					});
				} else {
					authApi.post('/api/places', this.place).then(() => {
						this.successMessage = 'Place has been successfully created';
					}).catch(error => {
						this.errorMessage = error.message;
					});
				}
			},
			addToFavorites() {
				console.log('Favorites', this.id);
				this.successMessage = 'Place has been added to favorites';
			},
			vote() {
				console.log('Vote', this.id);
				this.successMessage = 'You voted for this place';
			},
			close() {
				this.$router.back();
			},
			approvePlace() {
				this.$refs.confirm.open('Approve', 'Are you sure?').then((confirm) => {
					if (confirm){
						authApi.patch('/api/places/'+ this.id, { approved: true }).then(() => {
							this.place.approved = true;
							this.$forceUpdate();
							this.successMessage = 'Place has been successfully updated';
						}).catch(error => {
							this.errorMessage = error.message;
						});
					}
				});
			},
			deletePlace() {
				this.$refs.confirm.open('Delete', 'Are you sure?').then((confirm) => {
					if (confirm){
						authApi.delete('/api/places/' + this.id).then(() => {
							this.successMessage = 'Place has been successfully deleted';
							this.dialog = true;
							this.close();
						}).catch(error => {
							this.errorMessage = error.message;
						});
					}
				});
			},
			updateFields(fields) {
				// Is Changed?
				// Should be valid already
				// Update fields
				console.log(fields);
			},
			addNewComment(value) {
				// Construct new comment
				const comment = {
					text: value,
					placeId: this.id
				};

				// Send value to the server
				authApi.post('/api/comments', comment).then(() => {
					// If successful, show notification
					this.successMessage = 'Comment has been added';
					this.loadComments();
				}).catch(error => {
					this.errorMessage = error.message;
				});
			},
			deleteComment(id) {
				authApi.delete('/api/comments/' + id).then(() => {
					// If successful, show notification
					this.successMessage = 'Comment has been deleted';
					this.comments = this.comments.filter(c => c.id !== id);
				}).catch(error => {
					this.errorMessage = error.message;
				});
			},
			loadComments() {
				// Load comments
				authApi.get('/api/comments/' + this.id).then(resp => {
					this.comments = resp.data;
				}).catch(error => {
					this.errorMessage = error.message;
				});
			},
			showTestCard () {
				this.$dialog.show(TestCard);
			}
		},
		created() {
			if (this.id) {
				// Load place
				authApi.get('/api/places/' + this.id).then(resp => {
					this.place = resp.data;
					this.loadComments();
				}).catch(error => {
					this.errorMessage = error.message;
				});
			}
		}
	};
</script>

<style scoped lang="scss">

	.container {
		justify-content: center;
	}

	.jumbotron {
		min-height: 150px;
		width: 100%;
		justify-content: space-between;
	}

	.toolbar {
		width: 100%;
	}

	.place-card {
		display: flex;
		width: 80%;
		flex-direction: column;
		align-items: center;
	}

	.place-image {
		background-color: grey;
		display: flex;
	}

	.actions {
		display: flex;
		width: 100%;
		justify-content: space-around;
	}

</style>
