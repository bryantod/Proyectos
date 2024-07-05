<template>
  <div>
    <navbar></navbar>
    <div class="main-container">
      <div class="background-effect">
        <ul class="circles">
          <li></li>
        </ul>
      </div>

      <div class="content">
        <div class="container">
          <section class="form-upload">
            <h2>Agregar Curso</h2>
            <form @submit.prevent="agregarCurso" id="uploadform">
              <div class="form-group">
                <label for="descripcion">Descripción del curso</label>
                <input v-model="descripcion" class="controls" type="text" name="descripcion" required />
              </div>
              <div class="form-group">
                <label for="descuento">Descuento</label>
                <input v-model="descuento" class="controls" type="number" name="descuento" required />
              </div>
              <div class="form-group">
                <label for="duracion">Duración</label>
                <input v-model="duracion" class="controls" type="number" name="duracion" required />
              </div>
              <div class="form-group">
                <label for="imagen_curso">Imagen del curso</label>
                <input v-model="imagen_curso" class="controls" type="text" name="imagen_curso" required />
              </div>
              <div class="form-group">
                <label for="nivel">Nivel</label>
                <input v-model="nivel" class="controls" type="number" name="nivel" required />
              </div>
              <div class="form-group">
                <label for="nombre_curso">Nombre del curso</label>
                <input v-model="nombre_curso" class="controls" type="text" name="nombre_curso" required />
              </div>
              <div class="form-group">
                <label for="precio">Precio</label>
                <input v-model="precio" class="controls" type="number" name="precio" required />
              </div>
              <button class="buttons" type="submit">Añadir curso</button>
            </form>
          </section>

          <section class="course-list">
            <h2>Lista de Cursos</h2>
            <div v-for="(curso, index) in cursos" :key="index" class="course-item">
              <p>{{ curso.nombre_curso }}</p>
              <button @click="editarCurso(curso)" class="edit-button"><i class="fas fa-edit"></i></button>
            </div>
          </section>
        </div>
      </div>
    </div>

    <!-- Modal para editar curso -->
    <div v-if="mostrarModalEditar" class="modal">
      <div class="modal-content">
        <span class="close" @click="cerrarModalEditar">&times;</span>
        <h2>Editar Curso</h2>
        <form @submit.prevent="guardarCambiosCurso">
          <div class="form-group">
            <label for="descripcion">Descripción del curso</label>
            <input v-model="cursoEditado.descripcion" class="controls" type="text" name="descripcion" required />
          </div>
          <div class="form-group">
            <label for="descuento">Descuento</label>
            <input v-model="cursoEditado.descuento" class="controls" type="number" name="descuento" required />
          </div>
          <div class="form-group">
            <label for="duracion">Duración</label>
            <input v-model="cursoEditado.duracion" class="controls" type="number" name="duracion" required />
          </div>
          <div class="form-group">
            <label for="imagen_curso">Imagen del curso</label>
            <input v-model="cursoEditado.imagen_curso" class="controls" type="text" name="imagen_curso" required />
          </div>
          <div class="form-group">
            <label for="nivel">Nivel</label>
            <input v-model="cursoEditado.nivel" class="controls" type="number" name="nivel" required />
          </div>
          <div class="form-group">
            <label for="nombre_curso">Nombre del curso</label>
            <input v-model="cursoEditado.nombre_curso" class="controls" type="text" name="nombre_curso" required />
          </div>
          <div class="form-group">
            <label for="precio">Precio</label>
            <input v-model="cursoEditado.precio" class="controls" type="number" name="precio" required />
          </div>
          <!-- Botón para eliminar curso -->
          <button @click="eliminarCursoConfirmado" class="buttons btn-danger" type="button">Eliminar Curso</button>
          <!-- Botón para guardar cambios -->
          <button class="buttons" type="submit">Guardar cambios</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script src="@/assets/js/Upload.js">
</script>

<style scoped>
@import url("@/assets/css/Upload.css");

.modal {
  display: block;
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  overflow: auto;
}

.modal-content {
  background-color: #fefefe;
  margin: 10% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  max-width: 600px;
  border-radius: 10px;
  position: relative;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.buttons {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.buttons:hover {
  background-color: #45a049;
}

.form-group {
  margin-bottom: 15px;
}

.controls {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.course-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  margin-bottom: 10px;
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.edit-button,
.delete-button {
  background: none;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

.edit-button {
  color: #007bff;
}

.delete-button {
  color: #dc3545;
}

.edit-button:hover,
.delete-button:hover {
  text-decoration: underline;
}
</style>
