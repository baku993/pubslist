<template>
  <div class='places-list'>
    <v-card
      hover
      class='place'
      max-width='400'
      outlined
      v-for='p in places'
      :key='p.id'
      @click='openPlace(p.id)'>
      <v-list-item three-line>
        <v-list-item-avatar tile size='110'>
          <v-img :src='getUserAvatar(p)' class='white--text place-image align-end flex-fill'/>
        </v-list-item-avatar>
        <v-list-item-content>
          <div>
            <v-list-item-title class='headline mb-1 text-in-item'>{{p.name }}</v-list-item-title>
          </div>
          <div class='overline mb-4'>
            Created by: <span>{{ p.createdBy }}</span>
          </div>
          <v-list-item-subtitle>{{ p.description }}</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-card-actions>
        <v-btn text v-show='false' @click='vote(p.id)'>Vote</v-btn>
        <v-btn icon v-show='false' @click='addToFavorites(p.id)'>
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
			getUserAvatar(user) {
				let avatar;
				if (user.image) {
					avatar = (user.image);
				} else {
					avatar = require('../assets/dummy.jpg');
				}
				return avatar;
			},
			addToFavorites(id) {
				this.$emit('liked', id);
			},
			vote(id) {
				this.$emit('vote', id);
			},
			openPlace(id) {
				// should open a place
				this.$emit('open', id);
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
    width: 400px;
  }
}

  .text-in-item{
    text-overflow: ellipsis;
    width: 250px;
  }
</style>
