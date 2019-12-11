<template>
  <v-row class="toolbar">
    <div id="nav">
      <div class="nav__logo">
        <img alt="Vue logo" src="../assets/logo.png" />
      </div>
      <div class="nav__menu">
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
    </div>
  </v-row>
</template>

<script>
export default {
  name: "navbar",
  props: [],
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

<style lang="scss" scoped>
.toolbar {
  justify-content: space-between;
  margin: 10px 10px;
}

.account {
  cursor: pointer;
  position: absolute;
  right: 20px;
  top: 10px;
}

#nav {
  padding: 20px;
  flex: 1;
  display: flex;
  flex-direction: row;
  justify-content: center;

  &__logo img {
    left: 10px;
    width: 60px;
    top: 10px;
    height: 60px;
    position: absolute;
  }

  &__menu {
    flex: 1;
  }

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
