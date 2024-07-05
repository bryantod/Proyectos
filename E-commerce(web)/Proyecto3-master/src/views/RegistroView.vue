<template>
  <section class="form-register">
    <a href="/#">
      <img src="@/assets/img/logo.png" class="logo">
    </a>
    <h5>Crear Cuenta</h5>
    <form @submit.prevent="registrarUsuario" id="RegistrationForm">
      <label for="nombre" class="visually-hidden">Nombre completo</label>
      <input v-model="nombre" class="controls" id="nombre" type="text" name="nombre" placeholder="Nombre completo"
        required>

      <label for="apellido" class="visually-hidden">Apellido</label>
      <input v-model="apellido" class="controls" id="apellido" type="text" name="apellido" placeholder="Apellido"
        required>

      <label for="correo" class="visually-hidden">Correo Electrónico</label>
      <input v-model="correo" class="controls" id="correo" type="email" name="correo" placeholder="Correo Electrónico"
        required>

      <label for="contrasena" class="visually-hidden">Contraseña</label>
      <input v-model="contrasena" class="controls" id="contrasena" type="password" name="contrasena"
        placeholder="Contraseña" required>

      <label for="confirmContrasena" class="visually-hidden">Confirma tu Contraseña</label>
      <input v-model="confirmContrasena" class="controls" id="confirmContrasena" type="password"
        name="confirmContrasena" placeholder="Confirma tu Contraseña" required>

      <label for="imagen" class="visually-hidden">Imagen de perfil</label>
      <input type="file" id="imagen" name="imagen" accept="image/*" @change="onFileChange">

      <input class="buttons" type="submit" value="Crear cuenta">
    </form>
  </section>
</template>

<script>
import axios from 'axios';
import Swal from 'sweetalert2';
import { createClient } from '@supabase/supabase-js';

export default {
  data() {
    return {
      imagen: null,
      supabaseUrl: 'https://tnbzjbceklpojzikytcj.supabase.co',
      supabaseKey: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRuYnpqYmNla2xwb2p6aWt5dGNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ1MDc4MjMsImV4cCI6MjAzMDA4MzgyM30.j63f7-fy2z5WRSKwHHooQVJz-xM2j1XPqKek6oxhYow',
      supabase: null
    };
  },

  created() {
    this.supabase = createClient(this.supabaseUrl, this.supabaseKey);
  },

  methods: {
    registrarUsuario() {
      if (this.contrasena !== this.confirmContrasena) {
        Swal.fire({
          title: 'Error',
          text: 'Las contraseñas no coinciden',
          icon: 'error',
          confirmButtonText: 'OK',
          allowOutsideClick: false
        });
        return;
      }

      const nuevoUsuario = {
        nombre: this.nombre,
        apellido: this.apellido,
        correo: this.correo,
        contrasena: this.contrasena,
        imagen_url: '' 
      };

      console.log(nuevoUsuario);

      this.uploadImage(nuevoUsuario);
    },

    async uploadImage(nuevoUsuario) {
      try {
        if (this.imagen) {
          console.log('Nombre del archivo:', this.imagen.name);

          const { data, error } = await this.supabase.storage
            .from('usuarios') 
            .upload(`public/img/${encodeURIComponent(this.imagen.name)}`, this.imagen);

          if (error) {
            throw error;
          }

          nuevoUsuario.imagen_url = `https://tnbzjbceklpojzikytcj.supabase.co/storage/v1/object/public/usuarios/public/img/${encodeURIComponent(this.imagen.name)}`;

          this.registrarUsuarioEnBackend(nuevoUsuario);
        } else {
          this.registrarUsuarioEnBackend(nuevoUsuario);
        }
      } catch (error) {
        console.error('Error al subir imagen:', error.message);
        Swal.fire({
          title: 'Error',
          text: 'Ocurrió un error al subir la imagen',
          icon: 'error',
          confirmButtonText: 'OK',
          allowOutsideClick: false
        });
      }
    },

    registrarUsuarioEnBackend(nuevoUsuario) {
      axios.post('http://localhost:9999/usuario/inscribirse', nuevoUsuario, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          Swal.fire({
            title: 'Registro Exitoso',
            text: 'El usuario ha sido registrado correctamente',
            icon: 'success',
            confirmButtonText: 'OK',
            allowOutsideClick: false
          }).then(() => {
            location.reload(); 
          });
        })
        .catch(error => {
          console.error('Error al registrar usuario:', error);
          Swal.fire({
            title: 'Error',
            text: 'Ocurrió un error al registrar el usuario',
            icon: 'error',
            confirmButtonText: 'OK',
            allowOutsideClick: false
          });
        });
    },

    onFileChange(event) {
      this.imagen = event.target.files[0];
    }
  }
};
</script>

<style scoped>
@import url("@/assets/css/RegistroStyle.css");
</style>
