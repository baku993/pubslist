<template>
  <div class="login">
    <v-app>
      <v-content>
        <v-container class="fill-height" fluid>
          <v-row align="center" justify="center">
            <v-col cols="12" sm="8" md="4">
              <v-card class="elevation-12">
                <v-toolbar color="primary" dark flat>
                  <v-toolbar-title class="headline mb-1">Login</v-toolbar-title>
                  <v-spacer />
                </v-toolbar>
                <v-card-text>
                  <v-form v-model="valid" ref="form">
                    <v-text-field
                      label="Login"
                      name="login"
                      prepend-icon="person"
                      type="text"
                      :rules="[v => !!v || 'Password is required']"
                      v-model="username"
                    />

                    <v-text-field
                      label="Password"
                      name="password"
                      prepend-icon="lock"
                      type="password"
                      :rules="[v => !!v || 'Password is required']"
                      v-model="password"
                    />
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-btn text color="green darken-2" @click="goToSignUp">
                    New? Sign up
                  </v-btn>
                  <v-spacer />
                  <v-btn :class="{ grey: !valid, blue: valid }" @click="login">
                    Login
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "login",
  data: function() {
    return {
      username: "",
      password: "",
      valid: false
    };
  },
  methods: {
    login: function() {
      this.$refs.form.validate();
      const self = this;

      if (this.valid) {
        axios
          .post("api/login", {
            username: this.username,
            password: this.password
          })
          .then(
            response => {
              self.$store.dispatch("setUserToken", response.data.token);
              self.$router.replace("home");
            },
            err => {
              console.error("Oops. " + err.message);
            }
          );
      }
    },
    goToSignUp() {
      this.$router.replace("signup");
    }
  }
};
</script>

<style scoped>
.login {
  display: block;
}

input {
  margin: 10px 0;
  padding: 15px;
  border-radius: 4px;
}

p {
  margin-top: 40px;
  font-size: 13px;
}

p a {
  text-decoration: underline;
  cursor: pointer;
}
</style>
