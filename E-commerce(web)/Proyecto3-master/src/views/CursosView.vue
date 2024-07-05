<template>

  <body>
    <Navbar></Navbar>
    <br>
    <br>
    <div class="container-relative">
      <div class="context">
        <div class="contenedor_boton-carro">
          <button id="boton_carrito" @click="toggleMostrarCarrito">Mostrar/Ocultar Carrito</button>
        </div>
        <div class="carriitto">
          <div class="container-cart-products" v-if="mostrarCarrito">
            <div class="cart-total">
              <h3>Total:</h3>
              <span class="total-pagar">$ {{ total }}</span>
            </div>
            <div class="row-product">
              <div v-if="carrito.length === 0" class="cart-empty">El carrito está vacío</div>
              <div v-else class="cart-product" v-for="(item, index) in carrito" :key="index">
                <div class="info-cart-product">
                  <p class="titulo-producto-carrito">{{ item.nombre_curso }}</p>
                  <img id="imagen_en_carrito" :src="item.imagen_curso" :alt="item.nombre_curso" />
                  <span class="precio-producto-carrito">$ {{ item.precio }}</span>
                </div>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                  stroke="currentColor" class="icon-close" @click="removeFromCart(index)">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </div>
              <div>
                <button id="cerrar_carrito" @click="toggleMostrarCarrito">Cerrar carrito</button>
              </div>
            </div>
          </div>
        </div>
        <div id="lista_cursos">
          <div class="container"></div>
          <h1 id="texto_centrado">
            <input style="text-align: left" id="placeholderbuscar" type="text" v-model="searchTerm"
              placeholder="Buscar cursos..." />
          </h1>
          <div id="carta_productos" class="container">
            <div id="div_cartas">
              <div id="division_cursos" v-for="curso in paginatedCursos" :key="curso.id_curso">
                <div id="contenido_carta">
                  <a :href="curso.imagen_curso" class="botonimagen" :download="`imagen-curso-${curso.id_curso}.jpg`"
                    target="_blank"></a>
                  <div>
                    <figure id="card-image">
                      <img :src="curso.imagen_curso" :alt="curso.nombre_curso" />
                    </figure>
                  </div>
                  <div>
                    <div>
                      <div>
                        <h1 id="nombre_curso_carta">{{ curso.nombre_curso }}</h1>
                      </div>
                    </div>
                    <div class="content">
                      <button id="boton_card" @click="mostrarDetalles(curso)">Mostrar detalles del curso</button>
                      <button id="boton_card" @click="toggleCarrito(curso)">
                        {{ cursoEnCarrito(curso) ? "Quitar del carrito" : "Agregar al carrito" }}
                      </button>
                      <button class="botonwishlist_nolisted" @click="addToWishList(curso)"></button>
                      <button class="botonwishlist_listed" @click="removeFromWishlist(curso)"></button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="modal" :class="{ 'is-active': cursoSeleccionado }">

          <div class="modal-background"></div>
          <div class="modal-content">
            <button id="cerrar_modal" class="modal-close is-large" aria-label="close" @click="cerrarModal"></button>
            <div id="contenedor_curso" v-if="cursoSeleccionado">
              <figure class="image is-4by3">
                <img id="imagen_curso" :src="cursoSeleccionado.imagen_curso" :alt="cursoSeleccionado.nombre_curso" />
              </figure>
              <h2 id="titulo_curso">{{ cursoSeleccionado.nombre_curso }}</h2>
              <p id="descripcion_curso">{{ cursoSeleccionado.descripcion }}</p>
              <p id="precio_curso">${{ cursoSeleccionado.precio }}</p>
              <button id="boton_card_modal" @click="toggleCarrito(cursoSeleccionado)">{{
                cursoEnCarrito(cursoSeleccionado) ? "Quitar del carrito" : "Agregar al carrito" }}</button>
            </div>
          </div>
          
        </div>
        <nav id="paginacion_centrada" class="pagination is-centered" role="navigation" aria-label="pagination">
          <ul class="pagination-list">
            <li v-for="page in totalPages" :key="page">
              <a style="text-decoration: none; color: white" class="pagination-link" @click="changePage(page)"
                :class="{ 'is-current': currentPage === page }">{{ page }}</a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="area">
        <ul class="circles">
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
    </div>
  </body>
</template>

<script src="@/assets/js/Cursosaxios.js"></script>
<style scoped>
@import url("@/assets/css/CursosStyle.css");
@import url("@/assets/css/CarritoStyle.css");
</style>
