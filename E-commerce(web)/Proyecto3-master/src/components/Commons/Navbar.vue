<template>
  <header>
    <div id="navbar">
      <nav class="nav-container">
        <a id="nav-logo" class="nav-item" href="/#">
          <img src="@/assets/img/logo.png" alt="Logo" class="logo" />
        </a>
        <input type="checkbox" id="nav-toggle" />
        <label for="nav-toggle" class="nav-toggle-btn">
          <img src="@/assets/img/menu.png" alt="Menu" class="menu-icon" />
        </label>
        <ul class="nav-list">
          <li class="nav-item">
            <router-link to="/" exact class="nav-link" @click.prevent="reloadRoute('home')">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/cursos" exact class="nav-link" @click.prevent="reloadRoute('cursos')">Cursos</router-link>
          </li>
          <li class="nav-item" v-if="token">
            <router-link to="/upload" exact class="nav-link">Configuracion de Cursos</router-link>
          </li>
          <li class="nav-item dropdown" v-if="!token">
            <div class="dropdown-container">
              <button class="dropdown-btn">
                <img class="dropdown-img" src="@/assets/img/user.png" alt="User"> ▾
              </button>
              <div class="dropdown-content">
                <a href="/Sign-in">Iniciar Sesion</a>
                <a href="/Sign-up">Registarse</a>
              </div>
            </div>
          </li>
          <li class="nav-item dropdown" v-if="token">
            <div class="dropdown-container">
              <button class="dropdown-btn">
                <img class="dropdown-img" src="@/assets/img/user.png" alt="User"> ▾
              </button>
              <div class="dropdown-content">
                <router-link to="/profile" exact>Perfil</router-link>
                <router-link to="/WishList" exact>Lista de Deseos</router-link>
                <router-link to="/about" exact class="nav-link-logout" @click="signout">Cerrar Sesion</router-link>
              </div>
            </div>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script src="@/assets/js/Navbar.js"></script>

<style scoped>
@import "@/assets/css/NavbarStyle.css";
</style>
