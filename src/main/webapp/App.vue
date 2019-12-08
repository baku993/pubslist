<template>
  <div id="app">
    <v-app>
      <v-content>
        <navbar :is-user-logged="isUserLogged" @logout="logout"></navbar>
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
import Navbar from "./components/Navbar";

export default {
  components: { Navbar },
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
    cursor: pointer;
  }
}

#nav {
  padding: 20px;
  flex: 1;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
