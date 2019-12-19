<template>

	<v-form v-model='valid' ref='form'>
	</v-form>
	
</template>

<script>
	export default {
		name: 'place-data',
		props: ['data'],
		data() {
			return {
				valid: false
			};
		}
	};
</script>

<style scoped lang='scss'>

</style>
