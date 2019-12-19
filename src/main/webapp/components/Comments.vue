<template>

	<div class='comments'>
		<div class='headline'>Comments</div>

		<v-divider/>
	</div>
	
</template>

<script>
	export default {
		name: 'comments',
		props: ['comments', 'loggedUserName'],
	};
</script>

<style scoped lang="scss">

	.headline {
		margin-bottom: 50px;

	}

</style>
