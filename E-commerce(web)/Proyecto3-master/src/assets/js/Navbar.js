import Swal from "sweetalert2";

export default {
  name: "Navbar",
  props: {
    msg: String,
  },
  data() {
    return {
      token: null,
    };
  },
  methods: {
    reloadRoute(routeName) {
      const currentRoute = this.$route.name;
      if (currentRoute === routeName) {
        this.$router.go(0); // Recargar la página actual si ya estamos en la misma ruta
      } else {
        this.$router.push({ name: routeName }).catch(() => {}); // Navegar a la nueva ruta
      }
    },
    signout() {
      localStorage.removeItem("token");
      this.token = null;
      this.$emit("resetCartCount");
      this.$router.push({ name: "home" });
      Swal.fire({
        text: "Has cerrado tu sesión. ¡Vuelve pronto!",
        icon: "success",
        closeOnClickOutside: false,
      });
    },
  },
  mounted() {
    this.token = localStorage.getItem("token");
  },
};
