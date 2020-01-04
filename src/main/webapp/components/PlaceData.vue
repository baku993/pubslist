<template>

	<v-form v-model='valid' ref='form'>
		<v-text-field
				@input='validateForm'
				:readonly='readonly'
				:rules='nameRules'
				label='Name'
				name='name'
				type='text'
				counter
				hint='Specify name for the place'
				required
				filled
				v-model='name'/>

		<v-textarea
				@input='validateForm'
				:readonly='readonly'
				:rules='addressRules'
				label='Address'
				name='address'
				required
				filled
				auto-grow
				rows='2'
				hint='Specify address for the place'
				v-model='address'/>

		<v-textarea
				@input='validateForm'
				:readonly='readonly'
				label='Description'
				name='description'
				filled
				auto-grow
				rows='2'
				hint='Specify description for the place, optional'
				v-model='description'/>

		<image-uploader v-if='!readonly' @onImageUpload='onImageUpload' :valid='valid'/>
	</v-form>

</template>

<script>

	import ImageUploader from '../components/ImageUploader';

	export default {
		name: 'place-data',
		components: {ImageUploader},
		props: ['readonly', 'data'],
		data() {
			return {
				name: '',
				address: '',
				image: null,
				description: '',
				original: {},
				updated: {},
				valid: false,
				nameRules: [
					v => !!v || 'Name is required',
					v => (v && v.length < 25) || 'Name should be less than 25 characters'
				],
				addressRules: [
					v => !!v || 'Address is required'
				]
			};
		},
		methods: {
			validateForm() {
				this.$emit('updateFields', this.updated, this.$refs.form.validate());
			},
			onImageUpload(dataUrl, isValid) {
				if (this.image !== dataUrl) {
					this.updated['image'] = dataUrl;
				}
				this.image = dataUrl;
				this.valid = isValid;
				this.validateForm();
			}
		},
		watch: {
			name: function(val, old) {
				if (old !== val) {
					this.updated['name'] = val;
				}
			},
			address: function(val, old) {
				if (old !== val) {
					this.updated['address'] = val;
				}
			},
			description: function(val, old) {
				if (old !== val) {
					this.updated['description'] = val;
				}
			},
			data: function(val) {
				this.name = val.name;
				this.address = val.address;
				this.description = val.description;
			}
		}
	};
</script>

<style scoped lang='scss'>

</style>
