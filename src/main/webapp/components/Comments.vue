<template>

	<div class='comments'>
		<v-divider/>

		<div class='headline'>Comments</div>

		<v-banner v-for='c in comments' icon='mdi-comment' light single-line elevation='1' :key='c.id'>
			<v-toolbar flat class='comment-toolbar' dense>
				<div>
					{{c.createdBy}}
				</div>
				<v-toolbar-title>{{c.text}}</v-toolbar-title>
			</v-toolbar>

			<template v-slot:actions>
				<v-btn text color='red' v-if='canDelete(c.createdBy)' @click='deleteComment(c.id)'>
					<v-icon>mdi-delete</v-icon>
				</v-btn>
			</template>
		</v-banner>

		<v-textarea ref='newComment'
					label='Add a comment'
					auto-grow
					clearable
					rows='1'
					name='newComment'
					class='new-comment'
					v-model='newComment'
					hint='Click add to add a new comment'></v-textarea>

		<v-btn @click='send()' color='primary'>Send</v-btn>
	</div>

</template>

<script>
	export default {
		name: 'comments',
		props: ['comments', 'user'],
		data() {
			return {
				newComment: ''
			};
		},
		methods: {
			send() {

				// Emit event
				this.$emit('add', this.newComment);

				// Clean the field
				this.newComment = '';

			},
			canDelete(author) {
				return this.user.username === author || this.user.role.includes('ADMIN');
			},
			deleteComment(id) {
				// Emit event
				this.$emit('delete', id);
			}
		}
	};
</script>

<style lang="scss">

	.comments {
		width: 100%;
		padding: 5px;
	}

	.comment-toolbar > div {
		flex-direction: column;
		align-items: flex-start;
	}

	.new-comment {
		margin-top: 20px !important;
	}

</style>
