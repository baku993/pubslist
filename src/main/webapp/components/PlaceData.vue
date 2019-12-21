<template>

	<v-form v-model='valid' ref='form'>
		<v-text-field
				label='Name'
				name='name'
				type='text'
				counter
				hint='Specify name for the place'
				required
				filled
				v-model='data.name'/>

		<v-textarea
				label='Address'
				name='address'
				required
				filled
				auto-grow
				rows='2'
				hint='Specify address for the place'
				v-model='data.address'></v-textarea>

		<v-textarea
				label='Description'
				name='description'
				filled
				auto-grow
				rows='2'
				hint='Specify description for the place, optional'
				v-model='data.description'></v-textarea>
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
