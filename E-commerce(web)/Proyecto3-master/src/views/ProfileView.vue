<template>
  <div class="container">
    <img src="@/assets/img/top-circle.svg" alt="top circle" class="bg-image bg-image--top" />
    <img src="@/assets/img/bottom-circle.svg" alt="bottom circle" class="bg-image bg-image--bottom" />
    <div class="card">
      <div class="card__header">
        <img src="@/assets/img/card-pattern.svg" alt="pattern card" class="card__header-image" />
        <img :src="profile.img" :alt="profile.img" class="card__header-profile" />
      </div>
      <div class="card__body">
        <div class="card__text">
          <br>
          <br>
          <p class="text-muted"><i class="fa-solid fa-id-badge"></i> ID: <strong>{{ profile.perfil_id }}</strong></p>
          <p class="text-muted"><i class="fa-solid fa-user"></i> Nombre: <strong>{{ profile.nombre }}</strong></p>
          <p class="text-muted"><i class="fa-solid fa-envelope"></i> Correo: <strong>{{ profile.correo }}</strong></p>
        </div>
      </div>
      <div class="card__footer">
        <div class="card__stats">
          <a id="img-home" href="/">
            <i class="fa-solid fa-house fa-2x"></i>
          </a>
          <button @click="mostrarModalEditar = true" class="buttons">Editar</button>
        </div>
      </div>
    </div>

    <!-- Modal para editar perfil -->
    <div v-if="mostrarModalEditar" class="modal">
      <div class="modal-content">
        <span class="close" @click="cerrarModalEditar">&times;</span>
        <h2>Editar Perfil</h2>
        <form @submit.prevent="updateProfile">
          <div class="form-group">
            <label for="nombre">Nombre</label>
            <input v-model="profile.nombre" class="controls" type="text" name="nombre" required />
          </div>
          <div class="form-group">
            <label for="correo">Correo</label>
            <input v-model="profile.correo" class="controls" type="email" name="correo" required />
          </div>
          <div class="form-group">
            <label for="imagen">Imagen</label>
            <input type="file" @change="onFileChange" />
          </div>
          <button class="buttons" type="submit">Guardar cambios</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script src="@/assets/js/Profile.js"></script>

<style>
@import url("@/assets/css/ProfileStyle.css");
</style>