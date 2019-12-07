<template>
  <div class="login">
    <h3>Login</h3>
    <input type="text" v-model="email" placeholder="Email" /><br />
    <input type="password" v-model="password" placeholder="Password" /><br />
    <button class="btn" :click="login"><span>Login</span></button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "login",
  data: function() {
    return {
      email: "",
      password: ""
    };
  },
  methods: {
    login: function() {
      axios
        .post("/login", { username: this.email, password: this.password })
        .then(
          () => {
            this.$router.replace("home");
          },
          err => {
            alert("Oops. " + err.message);
          }
        );
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
.btn {
  margin-top: 20px;
  cursor: pointer;
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
