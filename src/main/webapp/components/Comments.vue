<template>

	<div class='comments'>
		<v-divider/>

		<div class='headline'>Comments</div>

	</div>
	
</template>

<script>
	export default {
		name: 'comments',
		props: ['comments', 'loggedUserName'],
	};
</script>

<style scoped lang="scss">

	.comments {
		width: 100%;
		padding: 5px;
	}

	.headline {
		margin-top: 50px;

	}

</style>
