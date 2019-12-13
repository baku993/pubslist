<template>
  <div class='places-list'>
    <v-card
      hover
      class='place'
      max-width='344'
      outlined
      v-for='p in places'
      :key='p.id'
      @click='openPlace(p.id)'
    >
      <v-list-item three-line>
        <v-list-item-avatar tile size='80' color='grey'></v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title class='headline mb-1'>{{
            p.name
          }}</v-list-item-title>
          <div class='overline mb-4'>
            Created by: <span>{{ p.audit.createdBy }}</span>
          </div>
          <v-list-item-subtitle>{{ p.description }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-btn text @click='vote(p.id)'>Vote</v-btn>
        <v-btn icon @click='addToFavorites(p.id)'>
          <v-icon>mdi-heart</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
	export default {
		name: 'placesList',
		props: ['places'],
		methods: {
			addToFavorites(id) {
				this.$emit('liked', id);
			},
			vote(id) {
				this.$emit('vote', id);
			},
			openPlace(id) {
				// should open a place
				console.log('Open place', id);
			}
		}
	};
</script>

<style lang="scss">
.places-list {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.place {
  margin: 10px;

  & .v-list-item {
    height: 125px;
  }
}
</style>
