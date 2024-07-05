<template>
  <Navbar></Navbar>
  <div class="container-fluid">
    <div class="bg"></div>
    <div class="col-lg-7 col-md-8" id="TodoParaBack">
      <div class="row pb-2 justify-content-center">
        <div class="col-12 pb-3"></div>
        <div
          v-for="listadedeseos in wishlist"
          :key="listadedeseos.id"
          class="col-lg-4 col-md-6 col-sm-6 pb-1 d-flex justify-content-center"
        >
          <div class="product-item bg-light mb-4" id="carta_wish">
            <div class="product-img">
              <img
                id="imagen_curso"
                :src="listadedeseos.imagen_curso"
                :alt="listadedeseos.imagen_curso"
              >
              <div class="accion_producto">
                <a class="btn btn-square" @click="removeFromWishList(listadedeseos)">
                  <img src="@/assets/img/close.png" width="50px">
                </a>
              </div>
            </div>
            <div class="text-center py-2">
              <a id="nombre_curso" href="">
                {{ listadedeseos.nombre_curso }}
              </a>
              <hr />
              <div class="d-flex align-items-center justify-content-center mt-2">
                <h6 class="text ml-2">{{ listadedeseos.descripcion }}</h6>
              </div>
            </div>
          </div>
        </div>
        <div class="col-12"></div>
      </div>
    </div>
  </div>
</template>
<script src="@/assets/js/WishList.js"></script>
<style>
@import url("@/assets/css/WishListStyle.css");
</style>
