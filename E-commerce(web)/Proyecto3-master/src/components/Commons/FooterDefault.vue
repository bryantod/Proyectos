<template>
  <footer>
    <div class="container">
      <div class="row">
        <div id="Arriba" class="col-lg-4">
          <h3>Enlaces</h3>

          <ul>
            <li><a href="./">Inicio</a></li>
            <li><a href="/about">Acerca de</a></li>
            <li><a href="#">Contacto</a></li>
          </ul>
        </div>
        <div class="col-lg-4">
          <h3>Contacto</h3>
          <p class="Correo">info.elearningemporium@gmail.com</p>
          <p>Teléfono: 320 861 4800</p>
        </div>
        <div id="Arriba" class="col-lg-4">
          <h3>Derechos de autor</h3>
          <p>3°er Trimestre Sena / E-learningEmporium</p>
        </div>
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  name: "FooterDefault",
  props: {
    msg: String,
  },
};
</script>
<style>
@import "@/assets/css/FooterStyle.css";
</style>
