<template>
  <div id="app">
    <v-app>
      <v-content>
        <v-row class="toolbar" v-if="isUserLogged">
          <div id="nav">
            <router-link to="/">Home</router-link>
            |
            <router-link to="/about">About</router-link>
          </div>

          <v-menu bottom offset-y>
            <template v-slot:activator="{ on }">
              <v-avatar id="account" color="orange" size="62" v-on="on">
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
        <router-view />
      </v-content>
    </v-app>
  </div>
</template>

<script>
import "material-design-icons-iconfont/dist/material-design-icons.css";
import "@mdi/font/css/materialdesignicons.css";
import { mapActions, mapGetters } from "vuex";
import { IS_LOGGED, SET_LOGGED_ACTION } from "./constants";

export default {
  data() {
    return {
      items: ["Logout"]
    };
  },
  computed: mapGetters([IS_LOGGED]),
  methods: {
    ...mapActions([SET_LOGGED_ACTION]),
    logout() {
      this.setUserLogged(false);
      this.$router.push("/");
    }
  }
};
</script>

<style lang="scss">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  .toolbar {
    justify-content: space-between;
    margin: 10px 10px;
  }

  .account {
    background-color: #1976d2;
    color: #ffffff;
  }
}

#nav {
  padding: 20px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
