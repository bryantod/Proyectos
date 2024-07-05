<template>
  <section class="form-login">
    <a href="/#">
      <img src="@/assets/img/logo.png" class="logo">
    </a>
    <h5>Iniciar Sesión</h5>
    <form @submit.prevent="signin">
      <label for="email" class="visually-hidden">Correo electrónico</label>
      <input class="controls" id="email" type="email" v-model="email" placeholder="Correo electrónico" required>
      
      <label for="password" class="visually-hidden">Contraseña</label>
      <input class="controls" id="password" type="password" v-model="password" placeholder="Contraseña" required>
      
      <button class="buttons" type="submit">Ingresar</button>
    </form>
    <p><a href="#">¿Olvidaste tu contraseña?</a></p>
  </section>
</template> 

<script>
import axios from "axios";
import Swal from "sweetalert2";

export default {
  name: "Signin",
  data() {
    return {
      email: null,
      password: null,
      loading: false,
      baseURL: "http://localhost:9999/",
    };
  },
  methods: {
    async signin() {
      try {
        this.loading = true;

        const user = {
          correo: this.email,
          contrasena: this.password,
        };

        const response = await axios.post(`${this.baseURL}usuario/ingresar`, user, {
          headers: {
            "Content-Type": "application/json"
          }
        });

        console.log("Response:", response.data);
        Swal.fire({
          icon: "success",
          title: "Inicio de sesión exitoso",
          text: "¡Bienvenido!",
          showConfirmButton: false,
          timer: 1500,
        });
        localStorage.setItem("token", response.data.token);
        window.location.href = '/#';
        
      } catch (error) {
        console.error("Error al iniciar sesión:", error);
        Swal.fire({
          icon: "error",
          title: "Error",
          text: "Ocurrió un error al intentar iniciar sesión. Por favor, inténtalo más tarde.",
        });
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style>
@import url("@/assets/css/LoginStyle.css");
</style>
