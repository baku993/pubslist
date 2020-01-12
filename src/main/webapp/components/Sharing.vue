<template>
	<social-sharing v-bind:url='getUrl()'
					v-bind:title='roll.place.name'
					v-bind:description='"Are you ready to drink?\n "+roll.place.name
								+ " has been rolled at " + roll.rolledAt + " by " + roll.rolledBy'
					v-bind:quote='"Are you ready to drink?\n "+roll.place.name
								+ " has been rolled at " + roll.rolledAt + " by " + roll.rolledBy'
					hashtags='drinking, pub'
					inline-template>
		<div>
			<network network='telegram'>
				<img height='40px' width='40px' :src='require("../assets/telegram.png")'/>
			</network>
			<network network='whatsapp'>
				<img height='40px' width='40px' :src='require("../assets/whatsup.jpeg")'/>
			</network>
		</div>
	</social-sharing>
</template>
<script>
	export default {
		name: 'sharing',
		props: ['roll'],
		data () {
			return {
			};
		},
		methods:{
			getUrl(){
				return window.location.protocol+'//'+window.location.hostname+'/roll/'+this.roll.id;
			}
		}
	};
</script>
