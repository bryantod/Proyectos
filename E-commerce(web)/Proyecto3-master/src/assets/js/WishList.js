import axios from 'axios';

export default {
  name: 'WishList',
  data() {
    return {
      wishlist: [],
      token: localStorage.getItem('token'),
    };
  },
  methods: {
    fetch() {
      axios
        .get(`http://localhost:9999/ListaDeseos/${this.token}`)
        .then((res) => {
          this.wishlist = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log('Error al buscar lista de deseos', err);
        });
    },
    removeFromWishList(curso) {
      axios
        .delete(`https://tnbzjbceklpojzikytcj.supabase.co/rest/v1/lista_deseos?id_curso=eq.${curso.id_curso}`, {
          headers: {
            apikey: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRuYnpqYmNla2xwb2p6aWt5dGNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ1MDc4MjMsImV4cCI6MjAzMDA4MzgyM30.j63f7-fy2z5WRSKwHHooQVJz-xM2j1XPqKek6oxhYow"
          }
        })
        .then((res) => {
          console.log('Curso eliminado de la lista de deseos:', res.data);
          this.wishlist = this.wishlist.filter((w) => w.id_curso !== curso.id_curso);
        })
        .catch((err) => {
          console.error('Error al eliminar curso de la lista de deseos:', err);
        });
    },
  },
  created() {
    this.fetch();
  },
};