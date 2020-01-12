<template>
	<div class='wheel'>
		<canvas id='arrow' width='450' height='30'>
			<p style='{color: white}' align='center'>Sorry, your browser doesn't support canvas. Please try another.</p>
		</canvas>
		<canvas id='canvas' width='450' height='500'>
			<p style='{color: white}' align='center'>Sorry, your browser doesn't support canvas. Please try another.</p>
		</canvas>
		<v-btn color='primary' id='spin' @click.prevent='spinWheel()'><span>Крутите барабан</span></v-btn>
		<v-btn color='primary' id='showResult' @click.prevent='showResult()'>
			<span>Посмотреть результат</span></v-btn>

		<modal v-if='showModal' @close='showModal = false' >
			<h3 slot='header'>И так...</h3>
			<div slot='body'>
				<p v-html='winMessage'/>
				<a v-bind:href='roll.place.url' target='_blank'>Я низнаю гидэ ито?</a>
				<sharing :roll='roll'/>
			</div>
		</modal>
	</div>
</template>

<script>
	import WinWheel from 'winwheel';
	import 'gsap/TweenMax';
	import Modal from './Modal.vue';
	import messages from './../messages.json';
	import authApi from '../auth/authApi';
	import Sharing from '../components/Sharing';

	function clickButton(selector) {
		document.querySelector(selector).click();
	}

	window.clickButton = clickButton;

	export default {
		name: 'wheel',
		segments: [],
		wheel: undefined,
		components: {
			modal: Modal,
			Sharing
		},
		props: {
			'places': {
				type: Array,
				required: true
			}
		},
		data: function() {
			return {
				showModal: false,
				roll: undefined,
				winMessage: 'Крутите барабан'
			};
		},
		created: function() {
			this.wheelProps = {
				'numSegments': 1,         // Number of segments
				'outerRadius': 225,       // The size of the wheel.
				'centerX': 225,       // Used to position on the background correctly.
				'centerY': 225,
				'textFontSize': 22,
				'textAlignment': 'center',
				'textOrientation': 'horizontal',
				'segments': [],
				'animation': // Definition of the animation
					{
						'type': 'spinToStop',
						'duration': 3,
						'spins': 8,
						'callbackFinished': 'window.clickButton(\'#showResult\')'
					}
			};
			if (this.places && !this.wheel) {
				this.$nextTick(() => this.renderWheel());
			}
		},
		methods: {
			renderWheel() {
				this.draw();
				if (this.wheel) {
					this.wheel.clearCanvas();
				}
				const segments = this.places.map(this.toSegment);
				const extraOpts = {
					numSegments: segments.length,
					segments: segments
				};

				const opts = Object.assign({}, this.wheelProps, extraOpts);
				this.wheel = new WinWheel(opts);
			},
			spinWheel() {
				if (this.isSpinning) {
					return;
				}
				this.wheel.stopAnimation(false);
				this.wheel.rotationAngle = 0;
				this.wheel.draw();
				this.getPlace();
			},
			showResult() {
				this.isSpinning = false;
				this.winMessage = this.randomMessage(this.roll.place.name);
				this.showModal = true;
			},
			spin(segmentNumber) {
				this.wheel.animation.stopAngle = this.wheel.getRandomForSegment(segmentNumber);
				this.isSpinning = true;
				this.$nextTick(() => this.wheel.startAnimation());
				this.$emit('updateRolls');
			},
			getPlace(){
				authApi.get('/api/rolls/manual').then(resp => {
					this.roll = resp.data;
					this.roll.rolledAt = new Date(resp.data.rolledAt).toLocaleString();
					this.spin(this.places.findIndex(x => x.id === this.roll.place.id)+1);
				}).catch(() => {
					this.$toastr.e('Ups... Something went wrong');
				});
			},
			toSegment(place, index) {
				return {
					text: place.name,
					id: place['.key'],
					fillStyle: colors[index % colors.length]
				};
			},
			draw() {
				const canvas = document.getElementById('arrow');
				if (canvas.getContext) {
					const ctx = canvas.getContext('2d');

					ctx.beginPath();
					ctx.moveTo(205, 0);
					ctx.lineTo(245, 0);
					ctx.lineTo(225, 30);
					ctx.fill();
				}
			},
			format(str, args) {
				return str.replace(/{(\d+)}/g, function(match, number) {
					return typeof args[number] !== 'undefined' ? args[number] : match;
				});
			},
			randomMessage(placeName) {
				return this.format(messages[this.getRandomInt(1, 100000) % messages.length], [placeName]);
			},
			getRandomInt(min, max) {
				return Math.floor(Math.random() * (max - min)) + min;
			}
		},
		watch: {
			places: function() {
				this.$nextTick(() => {
					this.renderWheel();
				});
			}
		}
	};

	const colors = ['#eae56f', '#89f26e', '#7de6ef', '#e7706f', '#ff66ff'];

</script>

<style lang="scss" scoped>
	.wheel {
		width: 450px;
		height: 100%;
		display: block;
		margin: 0 auto;
	}

	#spin {
		margin: 0 auto;
	}

	#showResult {
		display: none;
	}

	a {
		text-decoration: none;
		color: darkcyan;
		padding: 10px 10px 20px 0;
		display: block;
		width: 100%;
	}

	a:hover {
		font-weight: bold;
	}
</style>
