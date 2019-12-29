<template>

	<v-form v-model='valid' ref='form' >
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
	</v-form>
	
</template>

<script>
	export default {
		name: 'place-data',
		props: ['readonly'],
		data() {
			return {
				name: '',
				address: '',
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
				this.$emit('updateFields',this.updated,this.$refs.form.validate());
			},
			assignDefaults(fields){
				this.original.name = fields.name;
				this.original.address = fields.address;
				this.original.description = fields.description;
				this.name = fields.name;
				this.address = fields.address;
				this.description = fields.description;
			}
		},
		watch: {
			name: function(val) {
				if (this.original.name !== val) {
					this.updated['name'] = val;
				}
			},
			address: function(val) {
				if (this.original.address !== val) {
					this.updated['address'] = val;
				}
			},
			description: function(val) {
				if (this.original.description !== val) {
					this.updated['description'] = val;
				}
			}
		}
	};
</script>

<style scoped lang='scss'>

</style>
