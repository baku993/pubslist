<template>
  <v-row class="toolbar" v-if="isUserLogged">
    <div id="nav">
      <router-link to="/">Home</router-link>
      |
      <router-link to="/about">About</router-link>
    </div>

    <v-menu bottom offset-y>
      <template v-slot:activator="{ on }">
        <v-avatar class="account" color="orange" size="62" v-on="on">
          <span class="white--text">Account</span>
        </v-avatar>
      </template>
      <v-list>
        <v-list-item v-for="(item, i) in items" :key="i" @click="logout">
          <v-list-item-title>{{ item }}</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-row>
</template>

<script>
export default {
  name: "navbar",
  props: ["isUserLogged"],
  data() {
    return {
      items: ["Logout"]
    };
  },
  methods: {
    logout() {
      this.$emit("logout");
    }
  }
};
</script>

<style scoped></style>
