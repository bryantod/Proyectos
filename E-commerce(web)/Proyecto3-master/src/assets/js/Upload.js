import axios from "axios";
import Swal from "sweetalert2";

const API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRuYnpqYmNla2xwb2p6aWt5dGNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ1MDc4MjMsImV4cCI6MjAzMDA4MzgyM30.j63f7-fy2z5WRSKwHHooQVJz-xM2j1XPqKek6oxhYow";

export default {
  data() {
    return {
      descripcion: "",
      descuento: "",
      duracion: "",
      imagen_curso: "",
      nivel: "",
      nombre_curso: "",
      precio: "",
      cursos: [],
      mostrarModalEditar: false,
      cursoEditado: null,
    };
  },
  methods: {
    fetchCursos() {
      axios
        .get("http://localhost:9999/api/v1/cursos")
        .then((res) => {
          this.cursos = res.data;
        })
        .catch((err) => {
          console.error("Error al obtener los datos:", err);
        });
    },
    agregarCurso() {
      const nuevoCurso = {
        descripcion: this.descripcion,
        descuento: this.descuento,
        duracion: this.duracion,
        imagen_curso: this.imagen_curso,
        nivel: this.nivel,
        nombre_curso: this.nombre_curso,
        precio: this.precio,
      };

      // Agregar el curso localmente
      this.cursos.push(nuevoCurso);

      const config = {
        headers: {
          apikey: API_KEY,
          "Content-type": "application/json",
        },
      };

      axios
        .post("https://tnbzjbceklpojzikytcj.supabase.co/rest/v1/cursos", nuevoCurso, config)
        .then((response) => {
          Swal.fire({
            title: "Guardado Correctamente",
            text: "El curso ha sido guardado exitosamente",
            icon: "success",
            confirmButtonText: "OK",
            allowOutsideClick: false,
          }).then(() => {
            this.fetchCursos();
          });
        })
        .catch((error) => {
          console.error(error);
          Swal.fire({
            title: "Error",
            text: "Ocurrió un error al guardar el curso",
            icon: "error",
            confirmButtonText: "OK",
            allowOutsideClick: false,
          });
        });
    },
    editarCurso(curso) {
      this.cursoEditado = { ...curso };
      this.mostrarModalEditar = true;
    },
    cerrarModalEditar() {
      this.mostrarModalEditar = false;
      this.cursoEditado = null;
    },
    guardarCambiosCurso() {
      const cursoEditado = {
        id_curso: this.cursoEditado.id_curso,
        descripcion: this.cursoEditado.descripcion,
        duracion: this.cursoEditado.duracion,
        imagen_curso: this.cursoEditado.imagen_curso,
        nivel: this.cursoEditado.nivel,
        nombre_curso: this.cursoEditado.nombre_curso,
        descuento: this.cursoEditado.descuento,
        precio: this.cursoEditado.precio,
      };

      // Actualizar curso localmente
      const index = this.cursos.findIndex(c => c.id_curso === cursoEditado.id_curso);
      if (index !== -1) {
        this.cursos[index] = { ...cursoEditado };
      }

      const config = {
        headers: {
          apikey: API_KEY,
          "Content-type": "application/json",
        },
      };

      axios
        .put(`http://localhost:9999/api/v1/Curso/${this.cursoEditado.id_curso}`, cursoEditado, config)
        .then((response) => {
          Swal.fire({
            title: "Actualizado Correctamente",
            text: "El curso ha sido actualizado exitosamente",
            icon: "success",
            confirmButtonText: "OK",
            allowOutsideClick: false,
          }).then(() => {
            this.fetchCursos();
            this.cerrarModalEditar();
          });
        })
        .catch((error) => {
          console.error(error);
          Swal.fire({
            title: "Error",
            text: "Ocurrió un error al actualizar el curso",
            icon: "error",
            confirmButtonText: "OK",
            allowOutsideClick: false,
          });
        });
    },
    eliminarCurso(curso) {
      this.cursoEditado = { ...curso };
      this.mostrarModalEditar = true;
    },
    eliminarCursoConfirmado() {
      if (this.cursoEditado && this.cursoEditado.id_curso) {
        const cursoId = this.cursoEditado.id_curso;

        // Eliminar curso localmente
        this.cursos = this.cursos.filter(c => c.id_curso !== cursoId);

        const config = {
          headers: {
            apikey: API_KEY,
          },
        };

        axios
          .delete(`https://tnbzjbceklpojzikytcj.supabase.co/rest/v1/cursos?id_curso=eq.${cursoId}`, config)
          .then((response) => {
            Swal.fire({
              title: "Eliminado Correctamente",
              text: "El curso ha sido eliminado exitosamente",
              icon: "success",
              confirmButtonText: "OK",
              allowOutsideClick: false,
            }).then(() => {
              this.fetchCursos();
              this.cerrarModalEditar();
            });
          })
          .catch(() => {
            Swal.fire({
              title: "Cuidado",
              text: "Necesitas eliminarlo del carrito o de la lista de deseos",
              icon: "warning", // Icono de advertencia
              confirmButtonText: "OK",
              allowOutsideClick: false,
            });
          });
      } else {
        console.error("No se ha seleccionado ningún curso para eliminar");
      }
    },
  },
  created() {
    this.fetchCursos();
  },
};
