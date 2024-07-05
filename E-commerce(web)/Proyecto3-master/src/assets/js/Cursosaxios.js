import axios from "axios";
import toastr from "toastr";


export default {
  name: "App",
  data() {
    return {
      cursos: [],
      carrito: [],
      wishList: [], 
      wishListString: "Add to wishlist",
      cursoSeleccionado: null,
      currentPage: 1,
      mostrarCarrito: false,
      itemsPerPage: 6,
      searchTerm: "",
      token: localStorage.getItem("token"),
      totalCost: 0,
    };
  },
  computed: {
    total() {
      return this.totalCost;
    },
    totalPages() {
      return Math.ceil(this.cursos.length / this.itemsPerPage);
    },
    paginatedCursos() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.cursos
        .filter(curso => curso.nombre_curso.toLowerCase().includes(this.searchTerm.toLowerCase()))
        .slice(startIndex, endIndex);
    },
  },
  methods: {
    addToWishList(curso) {
      if (!this.token) {
        toastr.error("Por favor inicia sesión para añadir un objeto a la lista de deseos");
        return;
      }

     
      const cursoEnWishlist = this.wishList.find(item => item.id_curso === curso.id_curso);
      if (cursoEnWishlist) {
        toastr.info("El curso ya está en la lista de deseos.");
        return;
      }

     
      this.wishList.push(curso);
      toastr.success("Curso agregado a la lista de deseos.");

     
      axios.post(`http://localhost:9999/ListaDeseos/agregar?Token=${this.token}`, {
        id_curso: curso.id_curso,
        descripcion: curso.descripcion,
        duracion: curso.duracion,
        imagen_curso: curso.imagen_curso,
        nivel: curso.nivel,
        nombre_curso: curso.nombre_curso,
        descuento: curso.descuento,
        precio: curso.precio
      }, {
        headers: {
          'Content-Type': 'application/json',
        }
      })
      .catch(() => {
        toastr.error("Hubo un error al agregar el curso a la lista de deseos.");
      });
    },
    toggleCarrito(curso) {
      if (!this.token) {
        toastr.error("Por favor inicia sesión para añadir objetos al carrito");
        return;
      }

      const cursoEnCarrito = this.carrito.find(item => item.id_curso === curso.id_curso);
      if (!cursoEnCarrito) {
       
        const carritoItem = {
          iditem: Date.now(), 
          id_curso: curso.id_curso,
          nombre_curso: curso.nombre_curso,
          descripcion: curso.descripcion,
          duracion: curso.duracion,
          imagen_curso: curso.imagen_curso,
          nivel: curso.nivel,
          descuento: curso.descuento,
          precio: curso.precio,
          cantidad: 1,
        };
        this.carrito.push(carritoItem);
        this.totalCost += curso.precio; 
        toastr.success("Curso agregado al carrito.");

        axios.post(`http://localhost:9999/api/v1/carrito/agregar?token=${this.token}`,
          {
            cursoId: curso.id_curso,
          },
          {
            headers: {
              'Content-Type': 'application/json',
            }
          }
        )
          .then((response) => {
            carritoItem.iditem = response.data.items[0].iditem;
            this.totalCost = response.data.totalcost;
          })
          .catch(() => {
            console.log("Hubo un error al agregar el curso al carrito.");
          });
      } else {
        const index = this.carrito.findIndex(item => item.id_curso === curso.id_curso);
        if (index !== -1) {
          const cursoPrecio = this.carrito[index].precio;
          this.carrito.splice(index, 1);
          this.totalCost -= cursoPrecio; 
          toastr.success("Curso eliminado del carrito.");

          axios.delete(`http://localhost:9999/api/v1/carrito/Eliminar/${cursoEnCarrito.iditem}?token=${this.token}`)
            .catch((error) => {
              console.error("Error al eliminar el curso del carrito:", error);
              let errorMessage = "Ocurrió un error al eliminar el curso del carrito.";

              if (error.response && error.response.status === 404) {
                errorMessage = "No se encontró el curso en el carrito.";
              } else if (error.response && error.response.data && error.response.data.message) {
                errorMessage = error.response.data.message;
              }

              console.log(errorMessage);
            });
        }
      }
    },
    removeFromCart(index) {
      if (!this.token) {
        toastr.error("Por favor inicia sesión para eliminar objetos del carrito");
        return;
      }

      const idItem = this.carrito[index].iditem;
      const cursoPrecio = this.carrito[index].precio; 

      this.carrito.splice(index, 1);
      this.totalCost -= cursoPrecio; 
      toastr.success("Curso eliminado del carrito.");

      axios.delete(`http://localhost:9999/api/v1/carrito/Eliminar/${idItem}?token=${this.token}`)
        .catch((error) => {
          console.error("Error al eliminar el curso del carrito:", error);
          let errorMessage = "Ocurrió un error al eliminar el curso del carrito.";

          if (error.response && error.response.status === 404) {
            errorMessage = "No se encontró el curso en el carrito.";
          } else if (error.response && error.response.data && error.response.data.message) {
            errorMessage = error.response.data.message;
          }

          console.log(errorMessage);
        });
    },
    changePage(page) {
      this.currentPage = page;
    },
    mostrarDetalles(curso) {
      this.cursoSeleccionado = curso;
    },
    toggleMostrarCarrito() {
      this.mostrarCarrito = !this.mostrarCarrito;
    },
    cerrarModal() {
      this.cursoSeleccionado = null;
    },
    fetch() {
      axios
        .get("http://localhost:9999/api/v1/cursos")
        .then((res) => {
          this.cursos = res.data;
        })
        .catch((err) => {
          console.error("Error al obtener los datos:", err);
        });
    },
    searchData() {
      if (!this.searchTerm.trim()) {
        this.currentPage = 1;
        return;
      }

      const searchTermLower = this.searchTerm.trim().toLowerCase();
      const filteredCursos = this.cursos.filter(curso => {
        return curso.nombre_curso.toLowerCase().includes(searchTermLower);
      });

      if (filteredCursos.length === 0) {
        console.log("No se encontraron cursos que coincidan con la búsqueda.");
      } else {
        this.cursos = filteredCursos;
        this.currentPage = 1;
      }
    },
    cursoEnCarrito(curso) {
      return this.carrito.some(item => item.id_curso === curso.id_curso);
    },
    listCartItems() {
      axios.get(`http://localhost:9999/api/v1/carrito/?token=${this.token}`).then((response) => {
        if (response.status === 200) {
          const carritoItem = response.data.carritoItem;
          this.totalCost = response.data.costoTotal;
          this.carrito = carritoItem.map(item => ({
            iditem: item.id,
            id_curso: item.cursos.id_curso,
            nombre_curso: item.cursos.nombre_curso,
            descripcion: item.cursos.descripcion,
            duracion: item.cursos.duracion,
            imagen_curso: item.cursos.imagen_curso,
            nivel: item.cursos.nivel,
            descuento: item.cursos.descuento,
            precio: item.cursos.precio,
          }));
        } else {
          console.error("Error al obtener los ítems del carrito. Estado de respuesta no válido:", response.status);
        }
      })
        .catch((error) => {
          console.error("Error al obtener los ítems del carrito:", error);
        });
    },
    listWishListItems() {
      axios.get(`http://localhost:9999/ListaDeseos/${this.token}`).then((response) => {
        if (response.status === 200) {
          this.wishList = response.data;
        } else {
          console.error("Error al obtener los ítems de la lista de deseos. Estado de respuesta no válido:", response.status);
        }
      })
        .catch((error) => {
          console.error("Error al obtener los ítems de la lista de deseos:", error);
        });
    },
  },
  created() {
    this.fetch();

    if (this.token) {
      this.listCartItems();
      this.listWishListItems(); 
    } else {
      console.warn("Token no disponible. El usuario debe iniciar sesión.");
    }
    this.token = localStorage.getItem("token");
  },
};
